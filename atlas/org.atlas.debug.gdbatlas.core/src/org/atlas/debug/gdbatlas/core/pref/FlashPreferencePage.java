package org.atlas.debug.gdbatlas.core.pref;

import org.atlas.debug.gdbatlas.core.Activator;
import org.atlas.debug.gdbatlas.core.AtlasI18n;
import org.atlas.debug.gdbatlas.core.constants.IAtlasDebugPreferenceKeys;
import org.atlas.debug.gdbatlas.core.constants.IResouceBundleKeys;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class FlashPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {
	
	public FlashPreferencePage() {
		super(GRID);
	}
	
	 @Override
	  public void init(final IWorkbench workbench) {
	    setPreferenceStore(Activator.getDefault().getPreferenceStore());
	    setDescription(AtlasI18n.getString(IResouceBundleKeys.PREFEENCE_DESC));

	  }

	@Override
	protected void createFieldEditors() {		
		addField(new FileFieldEditor(IAtlasDebugPreferenceKeys.FLASH_COMMAND,AtlasI18n.getString(IResouceBundleKeys.PREFEENCE_FLASH_COMMAND),false,getFieldEditorParent()));
	}
}