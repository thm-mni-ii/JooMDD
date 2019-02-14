package de.thm.icampus.joomdd.ejsl.ui.wizard;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;

public class TemplateConfigurationPage extends WizardPage implements SelectionListener  {
	
	private Composite container;
	public static String src_path;
	public Text generatorPath,hostconfig,portconfig,adminName,adminPass,serverPath,rootPath,dbUsername,dbUserpass;
	private Button browse;
	Properties listConfig;
	
	private boolean initialized = false;

	protected TemplateConfigurationPage(String pageName, String pathSrc) {
		super(pageName);
	}

	@Override
	public void createControl(Composite parent) {
		container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(1, false));
		
		createFilebrowser(container);
		
		setControl(container);
		setPageComplete(true);
	}
	
	private void createFilebrowser(Composite main){
		Composite fileBrowse = new Composite(main, SWT.NONE);
		
		fileBrowse.setLayout(new GridLayout(3, false));
		fileBrowse.setLayoutData(new GridData(SWT.FILL, SWT.DEFAULT,true,false));
		
		Label sourcePathLabel = new Label(fileBrowse,SWT.NONE) ;
		sourcePathLabel.setText("Generator path:");
		
		generatorPath = new Text(fileBrowse, SWT.BORDER);
		generatorPath.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
						
		browse = new Button(fileBrowse, SWT.PUSH);
		browse.setText("browse");
		browse.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				DirectoryDialog dialog = new DirectoryDialog(main.getShell(), SWT.OPEN);
				
				final String srcGenPath = dialog.open();
				setSrc_path(srcGenPath);
			}
		});
	}
	
	public void setSrc_path(final String srcGenPath){
        if (srcGenPath != null) {
        	generatorPath.setText(srcGenPath);
			TemplateConfigurationPage.src_path=srcGenPath;
        }
	}
	
	@Override
	public void widgetSelected(SelectionEvent e) {
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
	}

	public void saveConfig(File src, File src_gen) {
		listConfig = new Properties();
		
		listConfig.setProperty("outputFolder", src_path);
		try {
			FileWriter genproperties2 = new FileWriter(src_gen.getAbsolutePath() + "/generator.properties");
			listConfig.store(genproperties2, "Generator configuration");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setVisible(boolean visible) {
	    if (!initialized && visible) {
	        // Set default src-gen path
			WizardNewProjectCreationPage firstPage = (WizardNewProjectCreationPage) this.getPreviousPage().getPreviousPage();
			File workspace = firstPage.getLocationPath().toFile();
			File project = new File(workspace, firstPage.getProjectName());
			String genPath = Paths.get(project.toString(), "src-gen").toString();
			setSrc_path(genPath);
			
	        initialized = true;
	    }
	    super.setVisible(visible);
	}
}
