package de.thm.icampus.joomdd.ejsl.ui.wizard;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;

public class TemplateSelectionPage extends WizardPage implements SelectionListener {

	private Composite container;
	private Button[] buttons;
	private Template[] templates;
	private Label template_description;
	private Label template_content;
	private String templateDirectory;
	
	protected TemplateSelectionPage(String pageName) {
		super(pageName);
	}

	@Override
	public void createControl(Composite parent) {
		container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(2, true));
		
		Group g1 = new Group(container, SWT.SHADOW_NONE); 
		g1.setLayout(new GridLayout(1, false));
		g1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		g1.setText("Templates");
		
		Group g2 = new Group(container, SWT.SHADOW_NONE); 
		g2.setLayout(new GridLayout(1, false));
		g2.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		g2.setText("Selected");
		try {
			
			templateDirectory = FileLocator.resolve(FileLocator.find(Platform.getBundle("de.thm.icampus.joomdd.ejsl.ui"), new Path("templates"), null)).getFile();
			TemplateList list = TemplateXMLLoader.loadTemplates(templateDirectory+"\\TemplateList.xml", templateDirectory+"\\TemplateList.xsd");
			templates = list.getTemplates();
			buttons = new Button[templates.length];
			for (int i = 0; i < buttons.length; i++) {
				buttons[i] = new Button(g1, SWT.RADIO);
				buttons[i].setText(templates[i].getName());
				buttons[i].addSelectionListener(this);
			}
			template_description = new Label(g2, SWT.NONE);
			template_content = new Label(g2, SWT.NONE);

			template_description.setLayoutData(new GridData(SWT.FILL, SWT.DEFAULT, true, false));
			template_content.setLayoutData(new GridData(SWT.FILL, SWT.DEFAULT, true, false));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		setControl(container);
		
		if(buttons.length > 0){
			buttons[0].setSelection(true);
			template_description.setText(templates[0].getDescription());
			setContentLabel(0);
		}
		setPageComplete(true);
	}

	public File getSelectedTemplate(){
		for (int i = 0; i < buttons.length; i++) {
			if(buttons[i].getSelection()){
				return new File(templateDirectory + "\\" + templates[i].getSrc().toString());
			}
		}
		return null;
	}

	private void setContentLabel(int index){
		try {
			int c;
			String s = "";
			FileReader fr = new FileReader(new File(templateDirectory + "\\" + templates[index].getSrc()));
			while((c=fr.read())!=-1){
				s += (char)c;
			}
			fr.close();
			template_content.setText(s);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void widgetSelected(SelectionEvent e) {
		for (int i = 0; i < buttons.length; i++) {
			if(e.getSource() == buttons[i]){
				if(templates[i].getDescription() == null){
					template_description.setText("");
				}else{
					template_description.setText(templates[i].getDescription());
				}
				setContentLabel(i);
				template_description.pack();
				template_content.pack();
				return;
			}
		}
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {}
	
}
