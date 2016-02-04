package de.thm.icampus.joomdd.ejsl.ui.wizard;


import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;

public class ExampleSelectionPage extends WizardPage {

	private Composite container;
	private Button button;
	private Button button2;
	private Button button3;
	
	protected ExampleSelectionPage(String pageName) {
		super(pageName);
	}

	@Override
	public void createControl(Composite parent) {
		// TODO Auto-generated method stub
		container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(1, false));
		button = new Button(container, SWT.RADIO);
		button2 = new Button(container, SWT.RADIO);
		button3 = new Button(container, SWT.RADIO);
		button.setText("Small example");
		button2.setText("Large example");
		button3.setText("Giant example");
		setControl(container);
		button.setSelection(true);
		setPageComplete(true);
	}

	public int getRadio(){
		if(button.getSelection()){
			return 0;
		}else if(button2.getSelection()){
			return 1;
		}else if(button3.getSelection()){
			return 2;
		}else{
			return -1;
		}
	}
	
}
