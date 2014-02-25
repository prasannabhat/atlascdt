package org.atlas.debug.gdbjtag.core.dsf.gdb.service;

import org.atlas.gdb.service.command.AtlasCommandFactory;
import org.eclipse.cdt.dsf.debug.service.command.ICommandControl;
import org.eclipse.cdt.dsf.gdb.service.GdbDebugServicesFactory;
import org.eclipse.cdt.dsf.mi.service.command.CommandFactory;
import org.eclipse.cdt.dsf.service.DsfSession;
import org.eclipse.debug.core.ILaunchConfiguration;

//atlas : update : Check for updates in GdbJtagDebugServicesFactory , as this code is copied from there
public class AtlasGdbJtagDebugServicesFactory extends GdbDebugServicesFactory{

	public AtlasGdbJtagDebugServicesFactory(String version) {
		super(version);
	}
	
	@Override
	protected ICommandControl createCommandControl(DsfSession session, ILaunchConfiguration config) {
		if (GDB_7_4_VERSION.compareTo(getVersion()) <= 0) {
			return new AtlasGDBJtagControl_7_4(session, config, new AtlasCommandFactory());
		}
		if (GDB_7_2_VERSION.compareTo(getVersion()) <= 0) {
			return new AtlasGDBJtagControl_7_2(session, config, new AtlasCommandFactory());
		}
		if (GDB_7_0_VERSION.compareTo(getVersion()) <= 0) {
			return new AtlasGDBJtagControl_7_0(session, config, new AtlasCommandFactory());
		}
		if (GDB_6_8_VERSION.compareTo(getVersion()) <= 0) {
			return new AtlasGDBJtagControl(session, config, new AtlasCommandFactory());
		}
		return new AtlasGDBJtagControl(session, config, new CommandFactory());
	}

}
