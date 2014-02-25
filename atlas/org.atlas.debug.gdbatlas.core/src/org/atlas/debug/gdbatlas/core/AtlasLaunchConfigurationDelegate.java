package org.atlas.debug.gdbatlas.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

import org.atlas.debug.gdbatlas.core.constants.IAtlasDebugConfigConstants;
import org.atlas.debug.gdbatlas.core.constants.IAtlasDebugPreferenceKeys;
import org.atlas.debug.gdbatlas.core.pref.AtlasPreferences;
import org.atlas.debug.gdbjtag.core.dsf.gdb.service.AtlasGdbJtagDebugServicesFactory;
import org.eclipse.cdt.debug.core.ICDTLaunchConfigurationConstants;
import org.eclipse.cdt.debug.gdbjtag.core.GDBJtagDSFLaunchConfigurationDelegate;
import org.eclipse.cdt.debug.gdbjtag.core.IGDBJtagConstants;
import org.eclipse.cdt.dsf.debug.service.IDsfDebugServicesFactory;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;

public class AtlasLaunchConfigurationDelegate extends GDBJtagDSFLaunchConfigurationDelegate{
	private static final String DEFAULT_PROJECT = "NoProjectFound";
	private Process gdbServerProcess;

	public AtlasLaunchConfigurationDelegate() {
		gdbServerProcess = null;
	}
	
	@Override
	protected IDsfDebugServicesFactory newServiceFactory(
			ILaunchConfiguration config, String version) {
		return new AtlasGdbJtagDebugServicesFactory(version);
	}
	
	@Override
	public void launch(ILaunchConfiguration config, String mode,
			ILaunch launch, IProgressMonitor monitor) throws CoreException {
		// Calculate parameters required to invoke gdb server
		String gdbServer = Activator.getDefault().getPreferenceStore().getString(IAtlasDebugPreferenceKeys.GDBSERVER_COMMAND);
		String command = gdbServer;
		String projectName = config.getAttribute(ICDTLaunchConfigurationConstants.ATTR_PROJECT_NAME, DEFAULT_PROJECT);
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
		
		// Proceed to regular launch
		super.launch(config, mode, launch, monitor);

	}
	
	
	@Override
	protected void cleanupLaunch() throws DebugException {
		if(gdbServerProcess != null){
			gdbServerProcess.destroy();
		}
		super.cleanupLaunch();
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
	
	private String getRawLocation(String programName, String projectName){
		if(projectName == DEFAULT_PROJECT){
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
