package org.dworks.debugexample;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.model.ILaunchConfigurationDelegate;

public class ExampleConfigurationDelegate implements ILaunchConfigurationDelegate {

	// This function receives the ILaunchConfiguration object returned by 
	// ExampleTabGroup.performApply(). In this simple example, it does nothing.
	public void launch(ILaunchConfiguration configuration, String mode,
		ILaunch launch, IProgressMonitor monitor) throws CoreException {
	}
}