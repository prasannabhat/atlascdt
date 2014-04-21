package org.atlas.debug.gdbatlas.core.ui;



import org.eclipse.cdt.debug.gdbjtag.ui.GDBJtagImages;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTab;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

public class AtlasMonitorTab extends AbstractLaunchConfigurationTab{

	private static final SelectionListener SelectionListener = null;
	Button[] checkSTM = new Button[3];
	public AtlasMonitorTab(){
		
		
		
	}
	
	@Override
	public Image getImage() {
		return GDBJtagImages.getStartupTabImage();
	}
	
	
	@Override
	public String getName() {
		return "Monitor Tab";
	}
	

	@Override
	public void performApply(ILaunchConfigurationWorkingCopy configuration)  {
		//StringTokenizer strTkn;
		//try {

		//strTkn = new StringTokenizer(configuration.getAttribute(IGDBJtagConstants.ATTR_RUN_COMMANDS, ""),"set {int} 0xF00000E8");
	
		//configuration.setAttribute(IGDBJtagConstants.ATTR_RUN_COMMANDS,strTkn.nextToken());
		//configuration.setAttribute(IGDBJtagConstants.ATTR_RUN_COMMANDS,configuration.getAttribute(IGDBJtagConstants.ATTR_RUN_COMMANDS, "").trim()+"\n" + this.generateCommand());
		//System.out.println(configuration.getAttribute(IGDBJtagConstants.ATTR_RUN_COMMANDS, ""));
		//System.out.println(configuration.getAttribute(IGDBJtagConstants.ATTR_JTAG_DEVICE, IGDBJtagConstants.DEFAULT_JTAG_DEVICE));
		//configuration.setAttribute(IGDBJtagConstants.ATTR_RUN_COMMANDS,"-gdb-version");
		//configuration.setAttribute(IGDBJtagConstants.ATTR_RUN_COMMANDS,"-gdb-version");
		//} catch (CoreException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		//}
	}

	@Override
	public void setDefaults(ILaunchConfigurationWorkingCopy configuration) {
		// TODO Auto-generated method stub
		//checkSTM[0].setSelection(false);
		//checkSTM[0].setSelection(false);
		//checkSTM[0].setSelection(false);
		//performApply(configuration);
	}

	@Override
	public void initializeFrom(ILaunchConfiguration configuration) {
		// TODO Auto-generated method stub
	}

	@Override
	public void createControl(Composite parent) {
		// TODO Auto-generated method stub
		
		ScrolledComposite sc = new ScrolledComposite(parent, SWT.V_SCROLL | SWT.H_SCROLL);
		sc.setExpandHorizontal(true);
		sc.setExpandVertical(true);
		setControl(sc);

		Composite comp = new Composite(sc, SWT.NONE);
		sc.setContent(comp);
		GridLayout layout = new GridLayout();
		comp.setLayout(layout);
		
		checkSTM[0] = new Button(comp, SWT.CHECK);
		checkSTM[0].setText("STM0");
		
		checkSTM[1] = new Button(comp, SWT.CHECK);
		checkSTM[1].setText("STM1");
		
		checkSTM[2] = new Button(comp, SWT.CHECK);
		checkSTM[2].setText("STM2");
		
			SelectionListener sl = new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				scheduleUpdateJob();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				scheduleUpdateJob();
				
			}
			
		};
		checkSTM[0].addSelectionListener(sl);
		checkSTM[1].addSelectionListener(sl);
		checkSTM[2].addSelectionListener(sl);
		
	}
	
	public String generateCommand(){
		
		String[] val =  new String[3];
		
		for(int n=0;n<3;n++)
		if(checkSTM[n].getSelection()){
			val[n] = "0x10000000";
		}
		else
			val[n] = "0x12000000";
		
		String monitorCommand = "set {int} 0xF00000E8 = "+val[0]+"\nset {int} 0xF00001E8 = "+val[1]+"\nset {int} 0xF00002E8 = "+val[2];
		
		return monitorCommand;
		
	}
}
