package org.atlas.debug.gdbjtag.core.dsf.gdb.service;

import java.util.Map;

import org.eclipse.cdt.debug.gdbjtag.core.GDBJtagDSFFinalLaunchSequence_7_2;
import org.eclipse.cdt.dsf.concurrent.RequestMonitorWithProgress;
import org.eclipse.cdt.dsf.concurrent.Sequence;
import org.eclipse.cdt.dsf.gdb.service.command.GDBControl_7_4;
import org.eclipse.cdt.dsf.mi.service.command.CommandFactory;
import org.eclipse.cdt.dsf.service.DsfSession;
import org.eclipse.debug.core.ILaunchConfiguration;


//atlas : update : Check for updates in GDBJtagControl_7_4 , as this code is copied from there
public class AtlasGDBJtagControl_7_4 extends GDBControl_7_4 {

	public AtlasGDBJtagControl_7_4(DsfSession session,
			ILaunchConfiguration config, CommandFactory factory) {
		super(session, config, factory);
	}
	
	@Override
	protected Sequence getCompleteInitializationSequence(Map<String,Object> attributes, RequestMonitorWithProgress rm) {
		return new GDBJtagDSFFinalLaunchSequence_7_2(getSession(), attributes, rm);
	}

}
