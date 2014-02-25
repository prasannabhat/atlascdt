package org.atlas.debug.gdbatlas.core.pref;

import org.atlas.debug.gdbatlas.core.Activator;
import org.atlas.debug.gdbatlas.core.constants.IAtlasDebugPreferenceKeys;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

public class AtlasPreferenceInitializer extends AbstractPreferenceInitializer {

	public AtlasPreferenceInitializer() {
	}

	@Override
	public void initializeDefaultPreferences() {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
	    store.setDefault(IAtlasDebugPreferenceKeys.GDB_COMMAND, "gdb");
	    store.setDefault(IAtlasDebugPreferenceKeys.HARDWARE_BREAKPOINT, false);
	    store.setDefault(IAtlasDebugPreferenceKeys.PORT_NUMBER, 3000);
	}

}
