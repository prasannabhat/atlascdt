package org.atlas.debug.gdbjtag.core.dsf.gdb.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.atlas.debug.gdbatlas.core.Activator;
import org.atlas.debug.gdbatlas.core.AtlasLaunchConfigurationDelegate;
import org.atlas.debug.gdbatlas.core.constants.IAtlasDebugConfigConstants;
import org.atlas.debug.gdbatlas.core.constants.IAtlasDebugPreferenceKeys;
import org.atlas.debug.gdbatlas.core.pref.AtlasPreferences;
import org.eclipse.cdt.debug.core.ICDTLaunchConfigurationConstants;
import org.eclipse.cdt.debug.gdbjtag.core.IGDBJtagConstants;
import org.eclipse.cdt.dsf.gdb.service.GDBBackend;
import org.eclipse.cdt.dsf.service.DsfSession;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;

public class AtlasGDBBackend extends GDBBackend {
	private final ILaunchConfiguration fLaunchConfiguration;
	private Process gdbServerProcess;

	public AtlasGDBBackend(DsfSession session, ILaunchConfiguration lc) {
		super(session, lc);
		fLaunchConfiguration = lc;
	}
	
	@Override
	public void destroy() {
		try {
			gdbServerProcess.exitValue();
	    } 
		catch (IllegalThreadStateException e) {
			//Process is not yet terminated
			gdbServerProcess.destroy();
	    }
		super.destroy();
	}
	
	@Override
	protected Process launchGDBProcess(String commandLine) throws CoreException {
		// Start the gdb server, depending on the preference
		boolean autostartGdbServer = Activator.getDefault().getPreferenceStore().getBoolean(IAtlasDebugPreferenceKeys.AUTOSTART_GDBSERVER);
		if(autostartGdbServer){
			autostartGdbServer(fLaunchConfiguration);
		}
		
		Process gdbProcess = super.launchGDBProcess(commandLine); 
		return gdbProcess;
	}
	
	private void autostartGdbServer(ILaunchConfiguration config) throws CoreException{
		// Calculate parameters required to invoke gdb server
		String gdbServer = Activator.getDefault().getPreferenceStore().getString(IAtlasDebugPreferenceKeys.GDBSERVER_COMMAND);
		String command = gdbServer;
		String projectName = config.getAttribute(ICDTLaunchConfigurationConstants.ATTR_PROJECT_NAME, AtlasLaunchConfigurationDelegate.DEFAULT_PROJECT);
		command += " ";
		String ipPort = config.getAttribute(IGDBJtagConstants.ATTR_IP_ADDRESS, IAtlasDebugConfigConstants.IP_ADDRESS) 
				+ ":" + config.getAttribute(IGDBJtagConstants.ATTR_PORT_NUMBER, AtlasPreferences.getPortNumber());
		command = command + ipPort;
		command += " ";
		String program = config.getAttribute(ICDTLaunchConfigurationConstants.ATTR_PROGRAM_NAME, " ");
		// Get the raw / absolute location of the program
		program = getRawLocation(program,projectName);
		command = command + program;
		
		// Start the gdb server process
//		gdbServerProcess = startGdbServer(command);
		gdbServerProcess = startGdbServer(gdbServer, ipPort, program);
		
	}
	
	private Process startGdbServer(String gdbServer,String ipPort, String program){
		Process process;
		try {
			process = new ProcessBuilder(gdbServer,ipPort,program).start();
//			BufferedReader bri = new BufferedReader(new InputStreamReader(process.getInputStream()));
//			BufferedReader bre = new BufferedReader(new InputStreamReader(process.getErrorStream()));
//			try {
//				TimeUnit.SECONDS.sleep(5);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//
//			String line;
//			 while ((line = bri.readLine()) != null) {
//			    System.out.println(line);
//			  }
//			  bri.close();
//			  while ((line = bre.readLine()) != null) {
//			    System.out.println(line);
//			  }
//			  bre.close();
			return process;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private Process startGdbServer(String command){
		try {
		  Process p = Runtime.getRuntime().exec(command);
		  BufferedReader bri = new BufferedReader(new InputStreamReader(p.getInputStream()));
		  BufferedReader bre = new BufferedReader(new InputStreamReader(p.getErrorStream()));
		  String line;
		  while ((line = bri.readLine()) != null) {
		    System.out.println(line);
		  }
		  bri.close();
		  while ((line = bre.readLine()) != null) {
		    System.out.println(line);
		  }
		  bre.close();
		p.waitFor();
		  return p;
		  
		}
		catch (Exception err) {
		  err.printStackTrace();
		}
		return null;
	}
	
	private String getRawLocation(String programName, String projectName){
		if(projectName == AtlasLaunchConfigurationDelegate.DEFAULT_PROJECT){
			return programName;
		}
		IProject project = org.eclipse.core.resources.ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
		IFile programFIle = project.getFile(programName);
		if(programFIle == null){
			return programName;
		}
		String path = programFIle.getRawLocation().toOSString();
		return path;
	}


}
