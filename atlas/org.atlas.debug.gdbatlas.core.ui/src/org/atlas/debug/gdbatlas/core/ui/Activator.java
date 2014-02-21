package org.atlas.debug.gdbatlas.core.ui;

import java.util.HashSet;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchDelegate;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.atlas.debug.gdbatlas.core.ui";

	private static final String HARDWARE_LAUNCH_TYPE = "org.atlas.debug.gdbatlas.launchConfigurationType"; //$NON-NLS-1$

	private static final String PREFERRED_DEBUG_HARDWARE_LAUNCH_DELEGATE = "org.atlas.debug.gdbatlas.core.LaunchConfigurationDelegate"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;
	
	/**
	 * The constructor
	 */
	public Activator() {
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		setDefaultLaunchDelegates();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	@Override
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	/**
	 * Logs the specified status with this plug-in's log.
	 * 
	 * @param status
	 *            status to log
	 */
	static void log(IStatus status) {
		getDefault().getLog().log(status);
	}
	/**
	 * Logs an internal error with the specified message.
	 * 
	 * @param message
	 *            the error message to log
	 */
	static void logErrorMessage(String message) {
		log(new Status(IStatus.ERROR, PLUGIN_ID, IStatus.ERROR, message, null));
	}

	/**
	 * Logs an internal error with the specified throwable
	 * 
	 * @param e
	 *            the exception to be logged
	 */
	static void log(Throwable e) {
		log(new Status(IStatus.ERROR, PLUGIN_ID, IStatus.ERROR, e.getMessage(), e));
	}
	
	private void setDefaultLaunchDelegates() {
		// Set the default launch delegates as early as possible, and do it only once (Bug 312997) 
		ILaunchManager launchMgr = DebugPlugin.getDefault().getLaunchManager();

		HashSet<String> debugSet = new HashSet<String>();
		debugSet.add(ILaunchManager.DEBUG_MODE);

		ILaunchConfigurationType remoteCfg = launchMgr.getLaunchConfigurationType(HARDWARE_LAUNCH_TYPE);
		try {
			if (remoteCfg.getPreferredDelegate(debugSet) == null) {
				ILaunchDelegate[] delegates = remoteCfg.getDelegates(debugSet);
				for (ILaunchDelegate delegate : delegates) {
					if (PREFERRED_DEBUG_HARDWARE_LAUNCH_DELEGATE.equals(delegate.getId())) {
						remoteCfg.setPreferredDelegate(debugSet, delegate);
						break;
					}
				}
			}
		} catch (CoreException e) {}
	}
}
