package org.atlas.debug.gdbatlas.core.pref;

import org.atlas.debug.gdbatlas.core.Activator;
import org.atlas.debug.gdbatlas.core.constants.IAtlasDebugPreferenceKeys;

public class AtlasPreferences {
	
	public static String getGdbCommand(){
		return Activator.getDefault().getPreferenceStore().getString(IAtlasDebugPreferenceKeys.GDB_COMMAND);
	}

}
