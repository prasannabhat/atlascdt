package org.atlas.debug.gdbatlas.core.pref;

import org.atlas.debug.gdbatlas.core.Activator;
import org.atlas.debug.gdbatlas.core.constants.IAtlasDebugPreferenceKeys;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

public class FlashPreferenceInitializer extends AbstractPreferenceInitializer {

	public FlashPreferenceInitializer() {
	}

	@Override
	public void initializeDefaultPreferences() {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
	    store.setDefault(IAtlasDebugPreferenceKeys.FLASH_COMMAND, "C:\\Program Files (x86)\\Infineon\\Memtool 4.5\\IMTMemtool.exe");
	}

}
