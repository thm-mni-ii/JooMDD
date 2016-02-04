package de.thm.icampus.joomdd.ejsl.ui.wizard;


import java.io.File;
import java.io.IOException;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;

public class TemplateSelectionPage extends WizardPage {

	private Composite container;
	private Button[] buttons;
	private File[] templateFiles;
	
	protected TemplateSelectionPage(String pageName) {
		super(pageName);
	}

	@Override
	public void createControl(Composite parent) {
		container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(1, false));
		try {
			String templateDirectory = FileLocator.resolve(FileLocator.find(Platform.getBundle("de.thm.icampus.joomdd.ejsl.ui"), new Path("templates"), null)).getFile();
			templateFiles = new File(templateDirectory).listFiles();
			buttons = new Button[templateFiles.length];
			for (int i = 0; i < buttons.length; i++) {
				buttons[i] = new Button(container, SWT.RADIO);
				buttons[i].setText(templateFiles[i].getName().split("\\.")[0]);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setControl(container);
		if(buttons.length > 0)
			buttons[0].setSelection(true);
		setPageComplete(true);
	}

	public File getSelectedTemplate(){
		for (int i = 0; i < buttons.length; i++) {
			if(buttons[i].getSelection()){
				return templateFiles[i];
			}
		}
		return null;
	}
	
}
