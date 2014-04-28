package org.atlas.flashbutton.handlers;

import java.io.IOException;

import org.atlas.debug.gdbatlas.core.Activator;
import org.atlas.debug.gdbatlas.core.constants.IAtlasDebugPreferenceKeys;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.jface.dialogs.MessageDialog;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class SampleHandler extends AbstractHandler {
	/**
	 * The constructor.
	 */
	public SampleHandler() {
	}

	/**
	 * the command has been executed, so extract extract the needed information
	 * from the application context.
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		//IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		String flashCommand;
		try {
			//Runtime.getRuntime().exec("");
			flashCommand = Activator.getDefault().getPreferenceStore().getString(IAtlasDebugPreferenceKeys.FLASH_COMMAND);
			new ProcessBuilder(flashCommand).start();
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		
		return null;
	}
}
