package org.atlas.debug.gdbatlas.core.pref;

import org.atlas.debug.gdbatlas.core.Activator;
import org.atlas.debug.gdbatlas.core.AtlasI18n;
import org.atlas.debug.gdbatlas.core.constants.IAtlasDebugPreferenceKeys;
import org.atlas.debug.gdbatlas.core.constants.IResouceBundleKeys;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class AtlasPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {
	
	public AtlasPreferencePage() {
		super(GRID);
	}
	
	 @Override
	  public void init(final IWorkbench workbench) {
	    setPreferenceStore(Activator.getDefault().getPreferenceStore());
	    setDescription(AtlasI18n.getString(IResouceBundleKeys.PREFEENCE_DESC));

	  }

	@Override
	protected void createFieldEditors() {
		addField(new FileFieldEditor(IAtlasDebugPreferenceKeys.GDB_COMMAND, AtlasI18n.getString(IResouceBundleKeys.PREFEENCE_GDB_COMMAND), false, getFieldEditorParent()));
		addField(new FileFieldEditor(IAtlasDebugPreferenceKeys.GDBSERVER_COMMAND, AtlasI18n.getString(IResouceBundleKeys.PREFEENCE_GDBSERVER_COMMAND), false,getFieldEditorParent()));
		addField(new FileFieldEditor(IAtlasDebugPreferenceKeys.FLASH_COMMAND,AtlasI18n.getString(IResouceBundleKeys.PREFEENCE_FLASH_COMMAND),false,getFieldEditorParent()));
		addField(new BooleanFieldEditor(IAtlasDebugPreferenceKeys.AUTOSTART_GDBSERVER, AtlasI18n.getString(IResouceBundleKeys.PREFEENCE_AUTOSTART_GDB), getFieldEditorParent()));
		addField(new IntegerFieldEditor(IAtlasDebugPreferenceKeys.PORT_NUMBER, AtlasI18n.getString(IResouceBundleKeys.PREFEENCE_PORT), getFieldEditorParent(), 4));
		addField(new BooleanFieldEditor(IAtlasDebugPreferenceKeys.HARDWARE_BREAKPOINT, AtlasI18n.getString(IResouceBundleKeys.PREFEENCE_HARDWARE_BREAKPOINT), getFieldEditorParent()));
	}
}