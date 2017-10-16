package de.thm.icampus.joomdd.ejsl.ui.wizard;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;

public class TemplateSelectionPage extends WizardPage implements SelectionListener {

	private Composite container;
	private Text filename;
	private Button[] buttons;
	private Template[] templates;
	private Label template_description;
	private Label template_content;
	private URL templateDirectory;
	private boolean newProject;
	
	protected TemplateSelectionPage(String pageName, boolean newProject) {
		super(pageName);
		this.newProject = newProject;
	}

	@Override
	public void createControl(Composite parent) {
		container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(1, false));
		
		if(newProject){
			Composite filenameGroup = new Composite(container, SWT.NONE);
			filenameGroup.setLayout(new GridLayout(2, false));
			filenameGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
			
			Label text = new Label(filenameGroup, SWT.NONE);
			text.setText("File Name:");
			
			filename = new Text(filenameGroup, SWT.BORDER);
			filename.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			filename.setText("Model");
			filename.addModifyListener(new ModifyListener() {
				
				@Override
				public void modifyText(ModifyEvent e) {
					if(filename.getText().equals("")){
						setPageComplete(false);
					}else{
						setPageComplete(true);
					}
				}
			});
		}
		
		Composite selectionGroup = new Composite(container, SWT.NONE);
		selectionGroup.setLayout(new GridLayout(2, true));
		selectionGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		
		Group g1 = new Group(selectionGroup, SWT.SHADOW_NONE); 
		g1.setLayout(new GridLayout(1, false));
		g1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		g1.setText("Templates");
		
		Group g2 = new Group(selectionGroup, SWT.SHADOW_NONE); 
		g2.setLayout(new GridLayout(1, false));
		g2.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		g2.setText("Selected");
		try {
			
			templateDirectory = FileLocator.resolve(FileLocator.find(Platform.getBundle("de.thm.icampus.joomdd.ejsl.ui"), new Path("templates"), null));
			TemplateList list = TemplateXMLLoader.loadTemplates(new URL(templateDirectory, "TemplateList.xml"), new URL(templateDirectory, "TemplateList.xsd"));
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
		
		if(buttons != null)
			if (buttons.length > 0){
				buttons[0].setSelection(true);
				template_description.setText(templates[0].getDescription());
				setContentLabel(0);
			}
		setPageComplete(true);
	}

	public String getFileName(){
		String returnString = filename.getText();
		
		if(returnString.toLowerCase().endsWith(".ejsl"))
			return returnString;
		else
			return returnString + ".eJSL";
	}
	
	public File getSelectedTemplate(){
		if(buttons != null){
			for (int i = 0; i < buttons.length; i++) {
				if(buttons[i].getSelection()){
					String t = templateDirectory.getPath()+ templates[i].getSrc().toString();
					try {
						return new File(t);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return null;
	}

	private void setContentLabel(int index){
		try {
			int c;
			String s = "";
			InputStreamReader isr = new InputStreamReader(new URL(templateDirectory, templates[index].getSrc().getName()).openStream());
			while((c=isr.read())!=-1){
				s += (char)c;
			}
			isr.close();
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
