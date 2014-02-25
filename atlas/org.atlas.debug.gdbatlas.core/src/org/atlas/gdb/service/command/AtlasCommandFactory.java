package org.atlas.gdb.service.command;

import org.atlas.debug.gdbatlas.core.Activator;
import org.atlas.debug.gdbatlas.core.constants.IAtlasDebugPreferenceKeys;
import org.eclipse.cdt.dsf.debug.service.IBreakpoints.IBreakpointsTargetDMContext;
import org.eclipse.cdt.dsf.debug.service.command.ICommand;
import org.eclipse.cdt.dsf.gdb.service.command.CommandFactory_6_8;
import org.eclipse.cdt.dsf.mi.service.command.output.MIBreakInsertInfo;

public class AtlasCommandFactory extends CommandFactory_6_8 {
	
	private boolean isHardWare;
	
	public AtlasCommandFactory() {
		isHardWare = false;
	}
	
	@Override
	public ICommand<MIBreakInsertInfo> createMIBreakInsert(
			IBreakpointsTargetDMContext ctx, boolean isTemporary,
			boolean isHardware, String condition, int ignoreCount, String line,
			int tid) {
		
		// Call the parent method, but create hardware breakpoint
		return super.createMIBreakInsert(ctx, isTemporary, getHardwareBreakPoint(), condition,
				ignoreCount, line, tid);
	}
	
	@Override
	public ICommand<MIBreakInsertInfo> createMIBreakInsert(
			IBreakpointsTargetDMContext ctx, boolean isTemporary,
			boolean isHardware, String condition, int ignoreCount,
			String location, int tid, boolean disabled, boolean isTracepoint) {
		// Call the parent method, but create hardware breakpoint
		return super.createMIBreakInsert(ctx, isTemporary, getHardwareBreakPoint(), condition,
				ignoreCount, location, tid, disabled, isTracepoint);
	}
	
	private boolean getHardwareBreakPoint(){
		return Activator.getDefault().getPreferenceStore().getBoolean(IAtlasDebugPreferenceKeys.HARDWARE_BREAKPOINT);
	}

}
