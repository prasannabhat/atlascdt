package org.atlas.debug.gdbjtag.core.dsf.gdb.service;

import java.util.Map;

import org.eclipse.cdt.debug.gdbjtag.core.GDBJtagDSFFinalLaunchSequence;
import org.eclipse.cdt.dsf.concurrent.RequestMonitorWithProgress;
import org.eclipse.cdt.dsf.concurrent.Sequence;
import org.eclipse.cdt.dsf.gdb.service.command.GDBControl;
import org.eclipse.cdt.dsf.mi.service.command.CommandFactory;
import org.eclipse.cdt.dsf.service.DsfSession;
import org.eclipse.debug.core.ILaunchConfiguration;

//atlas : update : Check for updates in GDBJtagControl , as this code is copied from there
public class AtlasGDBJtagControl extends GDBControl {

	public AtlasGDBJtagControl(DsfSession session, ILaunchConfiguration config,
			CommandFactory factory) {
		super(session, config, factory);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected Sequence getCompleteInitializationSequence(Map<String,Object> attributes, RequestMonitorWithProgress rm) {
		return new GDBJtagDSFFinalLaunchSequence(getSession(), attributes, rm);
	}

}
