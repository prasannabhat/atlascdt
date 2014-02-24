package org.atlas.debug.gdbatlas.core;

import org.atlas.debug.gdbjtag.core.dsf.gdb.service.AtlasGdbJtagDebugServicesFactory;
import org.eclipse.cdt.debug.gdbjtag.core.GDBJtagDSFLaunchConfigurationDelegate;
import org.eclipse.cdt.dsf.debug.service.IDsfDebugServicesFactory;
import org.eclipse.debug.core.ILaunchConfiguration;

public class AtlasLaunchConfigurationDelegate extends GDBJtagDSFLaunchConfigurationDelegate{

	public AtlasLaunchConfigurationDelegate() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected IDsfDebugServicesFactory newServiceFactory(
			ILaunchConfiguration config, String version) {
		return new AtlasGdbJtagDebugServicesFactory(version);
	}



}
