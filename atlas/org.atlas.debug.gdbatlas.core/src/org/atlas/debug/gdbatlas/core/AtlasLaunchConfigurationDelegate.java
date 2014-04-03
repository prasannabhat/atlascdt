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
	public static final String DEFAULT_PROJECT = "NoProjectFound";

	
	@Override
	protected IDsfDebugServicesFactory newServiceFactory(
			ILaunchConfiguration config, String version) {
		return new AtlasGdbJtagDebugServicesFactory(version);
	}
	
	@Override
	public void launch(ILaunchConfiguration config, String mode,
			ILaunch launch, IProgressMonitor monitor) throws CoreException {
	
		// Proceed to regular launch
		super.launch(config, mode, launch, monitor);

	}

	
	@Override
	protected void cleanupLaunch() throws DebugException {
		super.cleanupLaunch();
	}
	

}
