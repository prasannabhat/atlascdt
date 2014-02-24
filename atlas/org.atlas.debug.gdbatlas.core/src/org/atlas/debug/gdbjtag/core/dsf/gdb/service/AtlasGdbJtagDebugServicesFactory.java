package org.atlas.debug.gdbjtag.core.dsf.gdb.service;

import org.atlas.gdb.service.command.AtlasCommandFactory;
import org.eclipse.cdt.debug.gdbjtag.core.dsf.gdb.service.GDBJtagControl;
import org.eclipse.cdt.debug.gdbjtag.core.dsf.gdb.service.GDBJtagControl_7_0;
import org.eclipse.cdt.debug.gdbjtag.core.dsf.gdb.service.GDBJtagControl_7_2;
import org.eclipse.cdt.debug.gdbjtag.core.dsf.gdb.service.GDBJtagControl_7_4;
import org.eclipse.cdt.debug.gdbjtag.core.dsf.gdb.service.GdbJtagDebugServicesFactory;
import org.eclipse.cdt.dsf.debug.service.command.ICommandControl;
import org.eclipse.cdt.dsf.mi.service.command.CommandFactory;
import org.eclipse.cdt.dsf.service.DsfSession;
import org.eclipse.debug.core.ILaunchConfiguration;

public class AtlasGdbJtagDebugServicesFactory extends GdbJtagDebugServicesFactory{

	public AtlasGdbJtagDebugServicesFactory(String version) {
		super(version);
	}
	
	@Override
	protected ICommandControl createCommandControl(DsfSession session, ILaunchConfiguration config) {
		if (GDB_7_4_VERSION.compareTo(getVersion()) <= 0) {
			return new GDBJtagControl_7_4(session, config, new AtlasCommandFactory());
		}
		if (GDB_7_2_VERSION.compareTo(getVersion()) <= 0) {
			return new GDBJtagControl_7_2(session, config, new AtlasCommandFactory());
		}
		if (GDB_7_0_VERSION.compareTo(getVersion()) <= 0) {
			return new GDBJtagControl_7_0(session, config, new AtlasCommandFactory());
		}
		if (GDB_6_8_VERSION.compareTo(getVersion()) <= 0) {
			return new GDBJtagControl(session, config, new AtlasCommandFactory());
		}
		return new GDBJtagControl(session, config, new CommandFactory());
	}

}
