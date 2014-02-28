package org.atlas.debug.gdbjtag.core.dsf.gdb.service;

import org.eclipse.cdt.dsf.gdb.service.GDBBackend;
import org.eclipse.cdt.dsf.service.DsfSession;
import org.eclipse.debug.core.ILaunchConfiguration;

public class AtlasGDBBackend extends GDBBackend {

	public AtlasGDBBackend(DsfSession session, ILaunchConfiguration lc) {
		super(session, lc);
	}
	
	@Override
	public void destroy() {
		super.destroy();
	}

}
