package org.atlas.debug.gdbatlas.core.ui;

import org.eclipse.cdt.debug.core.ICDTLaunchConfigurationConstants;
import org.eclipse.cdt.debug.gdbjtag.internal.ui.GDBJtagDSFCMainTab;
import org.eclipse.cdt.dsf.gdb.launching.LaunchMessages;
import org.eclipse.cdt.launch.ui.CMainTab;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.ILaunchConfigurationDialog;
import org.eclipse.debug.ui.ILaunchConfigurationTab;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class AtlasMainTab extends GDBJtagDSFCMainTab {

	public AtlasMainTab() {
		
	}
	@Override
	public void setDefaults(ILaunchConfigurationWorkingCopy config) {
		super.setDefaults(config);
		// Disable auto build before launch
		config.setAttribute(ICDTLaunchConfigurationConstants.ATTR_BUILD_BEFORE_LAUNCH, ICDTLaunchConfigurationConstants.BUILD_BEFORE_LAUNCH_DISABLED);

	}
	
	
	protected void createExeFileGroup(Composite parent, int colSpan) {
		 
		Composite mainComp = new Composite(parent, SWT.NONE);
		GridLayout mainLayout = new GridLayout();
		mainLayout.marginHeight = 0;
		mainLayout.marginWidth = 0;
		mainComp.setLayout(mainLayout);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = colSpan;
		mainComp.setLayoutData(gd);
		fProgLabel = new Label(mainComp, SWT.NONE);
	    fProgLabel.setText(AtlasLaunchMessages.CMainTab_C_Application); 
		gd = new GridData();
		fProgLabel.setLayoutData(gd);
		fProgText = new Text(mainComp, SWT.SINGLE | SWT.BORDER);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		fProgText.setLayoutData(gd);
		fProgText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent evt) {
				updateLaunchConfigurationDialog();
			}
		});
		
		Composite buttonComp = new Composite(mainComp, SWT.NONE);
		GridLayout layout = new GridLayout(3, false);
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		buttonComp.setLayout(layout);
		gd = new GridData(GridData.HORIZONTAL_ALIGN_END);
		buttonComp.setLayoutData(gd);
		buttonComp.setFont(parent.getFont());

		//createVariablesButton(buttonComp, LaunchMessages.CMainTab_Variables, fProgText);
		fSearchButton = createPushButton(buttonComp, AtlasLaunchMessages.CMainTab_Search, null); 
		fSearchButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent evt) {
				handleSearchButtonSelected();
				updateLaunchConfigurationDialog();
			}
		});

		Button fBrowseForBinaryButton;
		fBrowseForBinaryButton = createPushButton(buttonComp, AtlasLaunchMessages.Launch_common_Browse_2, null); 
		fBrowseForBinaryButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent evt) {
				handleBrowseButtonSelected();
				updateLaunchConfigurationDialog();
			}
		});
	}
	
	
	protected void createProjectGroup(Composite parent, int colSpan) {
		
		final Button check = new Button(parent,SWT.CHECK);
		check.setText(AtlasLaunchMessages.CMainTab_ProjectColon);
		check.setSelection(true);
		
		Composite projComp = new Composite(parent, SWT.NONE);
		
		GridLayout projLayout = new GridLayout();
		projLayout.numColumns = 2;
		projLayout.marginHeight = 0;
		projLayout.marginWidth = 0;
		projComp.setLayout(projLayout);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = colSpan;
		projComp.setLayoutData(gd);

		fProjLabel = new Label(projComp, SWT.NONE);
		//fProjLabel.setText(AtlasLaunchMessages.CMainTab_ProjectColon); 
		gd = new GridData();
		gd.horizontalSpan = 2;
		fProjLabel.setLayoutData(gd);

		fProjText = new Text(projComp, SWT.SINGLE | SWT.BORDER);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		
		
		fProjText.setLayoutData(gd);
		fProjText.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent evt) {
				// if project changes, invalidate program name cache
				fPreviouslyCheckedProgram = null;
				updateBuildConfigCombo(""); //$NON-NLS-1$
				updateLaunchConfigurationDialog();
			}
		});

		fProjButton = createPushButton(projComp, AtlasLaunchMessages.Launch_common_Browse_1, null); 
		fProjButton.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent evt) {
				handleProjectButtonSelected();
				updateLaunchConfigurationDialog();
			}
		});
		check.addSelectionListener(new SelectionAdapter() {
		    @Override
		    public void widgetSelected(SelectionEvent e) {
		      		fProjText.setEnabled(check.getSelection());
		    		fProjButton.setEnabled(check.getSelection());
		    		fSearchButton.setEnabled(check.getSelection());
		    		//System.out.println(check.getEnabled());
		    }
		}); 
	}

}
