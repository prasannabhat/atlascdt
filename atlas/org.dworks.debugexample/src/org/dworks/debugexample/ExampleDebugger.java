package org.dworks.debugexample;

import java.io.File;

import org.eclipse.cdt.core.IBinaryParser.IBinaryObject;
import org.eclipse.cdt.debug.core.ICDIDebugger2; 
import org.eclipse.cdt.debug.core.cdi.ICDISession;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.ILaunch;

public class ExampleDebugger implements ICDIDebugger2 {

	// This class does very little because there's no debugger executable to interface
	public ICDISession createSession(ILaunch launch, File executable,
			IProgressMonitor monitor) throws CoreException {
		return null;
	}

	public ICDISession createDebuggerSession(ILaunch launch,
			IBinaryObject exe, IProgressMonitor monitor)
			throws CoreException {
		return null;
	}
}
