package org.atlas.debug.gdbatlas.core.pref;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;


public class MainPreferencePage extends FieldEditorPreferencePage implements
    IWorkbenchPreferencePage {

  public MainPreferencePage() {
    super(GRID);

  }

  public void createFieldEditors() {
      }

  @Override
  public void init(IWorkbench workbench) {
    }
} 