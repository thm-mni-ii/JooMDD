package de.thm.icampus.joomdd.ejsl.ui.wizard;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Properties;

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

public class TemplateConfigurationPage extends WizardPage implements SelectionListener  {
	
	private Composite container;
	private String src_path ="./src-gen";
	private Text generatorPath,hostconfig,portconfig,adminName,adminPass,serverPath,rootPath,dbUsername,dbUserpass;
	private Button checkPage, checkEntities, checkWordpress,
	checkJoomla,checkTest,checkUpdateFolder, checkFirefox,checkChrome,checkie,checkPHPUnit, checkCodeCeption;
	Properties listKonfig;

	protected TemplateConfigurationPage(String pageName, String pathSrc) {
		super(pageName);
		if(!pathSrc.isEmpty())
		src_path = pathSrc;
	}

	@Override
	public void createControl(Composite parent) {
		container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(1, false));
		
		createArtefactsOptions(container);
		createGeneratorOptions(container);
		createFilebrowser(container);
		createTestConfiguration(container);
		
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
		generatorPath.setText(src_path);
		Button browse = new Button(fileBrowse, SWT.PUSH);
		browse.setText("browse");
		browse.addSelectionListener(new SelectionAdapter() {
		      public void widgetSelected(SelectionEvent e) {
		        DirectoryDialog dialog = new DirectoryDialog(main.getShell(), SWT.OPEN);
		        String file = dialog.open();
		        if (file != null) {
		        	generatorPath.setText(file);
		        }
		      }
		    });
		
	}
	private void createArtefactsOptions(Composite main){
		
		Composite artefactsContainer = new Composite(main,SWT.NONE);
		artefactsContainer.setLayout(new GridLayout(5, false));
		Label sourcePathLabel = new Label(artefactsContainer,SWT.NONE) ;
		sourcePathLabel.setText("Artefacts Options:");
		 
		checkPage =  new Button(artefactsContainer, SWT.CHECK);
		checkPage.setText("Page");
		checkEntities = new Button(artefactsContainer, SWT.CHECK);
		checkEntities.setText("Entities");
		Button extensions = new Button(artefactsContainer, SWT.CHECK);
		extensions.setText("Extensions");
		extensions.setSelection(true);
		extensions.setEnabled(false);
		checkTest =  new Button(artefactsContainer, SWT.CHECK);
		checkTest.setText("Tests");
		
		
	}
	private void createGeneratorOptions(Composite main){
		
		Composite containerGeneratorOption = new Composite(main,SWT.NONE);
		containerGeneratorOption.setLayout(new GridLayout(3, false));
		Label sourcePathLabel = new Label(containerGeneratorOption,SWT.NONE) ;
		sourcePathLabel.setText("Generator Options:");
		checkJoomla =  new Button(containerGeneratorOption, SWT.CHECK);
		checkJoomla.setText("Joomla");
		checkJoomla.setSelection(true);
		checkWordpress = new Button(containerGeneratorOption, SWT.CHECK);
		checkWordpress.setText("Workpress");
		checkWordpress.setEnabled(false);
		Composite containerUpdate = new Composite(main,SWT.NONE);
		containerUpdate.setLayout(new GridLayout(2, false));
		Label checkUpdateFolderLabel = new Label(containerUpdate,SWT.NONE) ;
		checkUpdateFolderLabel.setText("Generator Update Folder:");
		checkUpdateFolder = new Button(containerUpdate, SWT.CHECK);
		checkUpdateFolder.setText("");
		
		
	}
	private void createTestConfiguration(Composite main){
		
		Composite framework  = new Composite(main,SWT.NONE);
		framework.setLayout(new GridLayout(3, false));
		Label frameworkLabel = new Label(framework,SWT.NONE) ;
		frameworkLabel.setText("Framework for tests:");
		checkPHPUnit  =  new Button(framework, SWT.RADIO);
		checkPHPUnit.setText("PHP-Unit");
		checkCodeCeption = new Button(framework, SWT.RADIO);
		checkCodeCeption.setText("CodeCeption");
		Composite serverContainer = new Composite(main, SWT.NONE);
		serverContainer.setLayout(new GridLayout(2, false));
		serverContainer.setLayoutData(new GridData(SWT.FILL, SWT.DEFAULT,true,false));
		Label hostConfigLabel = new Label(serverContainer,SWT.NONE) ;
		hostConfigLabel.setText("Host URL:");
		hostconfig = new Text(serverContainer, SWT.BORDER);
		hostconfig.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		hostconfig.setText("http://localhost");
		
		Label portconfigLabel = new Label(serverContainer,SWT.NONE) ;
		portconfigLabel.setText("Port Configuration:");
		portconfig = new Text(serverContainer, SWT.BORDER);
		portconfig.setText("4445");
		
		Label adminNameLabel = new Label(serverContainer,SWT.NONE) ;
		adminNameLabel.setText("Administrator User Name:");
		adminName = new Text(serverContainer, SWT.BORDER);
		adminName.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		adminName.setText("admin");
		
		Label adminPassLabel = new Label(serverContainer,SWT.NONE) ;
		adminPassLabel.setText("Administrator User Password:");
		adminPass = new Text(serverContainer, SWT.BORDER);
		adminPass.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		adminPass.setText("admin");
		
		Label serverPathLabel = new Label(serverContainer,SWT.NONE) ;
		serverPathLabel.setText("Server Path:");
		serverPath = new Text(serverContainer, SWT.BORDER);
		serverPath.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		serverPath.setText("c:/xampp/htdocs");
		
		Label rootPathLabel = new Label(serverContainer,SWT.NONE) ;
		rootPathLabel.setText("Rooth path:");
		rootPath = new Text(serverContainer, SWT.BORDER);
		rootPath.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		rootPath.setText("");
		
		Composite browser  = new Composite(main,SWT.NONE);
		browser.setLayout(new GridLayout(4, false));
		Label browserLabel = new Label(browser,SWT.NONE) ;
		browserLabel.setText("Browser for Acceptance tests:");
		checkChrome = new Button(browser, SWT.RADIO);
		checkChrome.setText("Chrome");
		checkFirefox = new Button(browser, SWT.RADIO);
		checkFirefox.setText("Firefox");
		checkFirefox.setSelection(true);
		checkie = new Button(browser, SWT.RADIO);
		checkie.setText("IE");
	    
		
		
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void saveConfig(File src, File src_gen) {
		
		 listKonfig = new Properties();
		
		listKonfig.setProperty("page",checkPage.getEnabled()+"");
		listKonfig.setProperty("entities",checkEntities.getEnabled() + "");
		listKonfig.setProperty("test", checkTest.getEnabled() + "");
		listKonfig.setProperty("updateFolder",checkUpdateFolder.getEnabled()+"");
		listKonfig.setProperty("joomla","true");
		listKonfig.setProperty("wordpress","false");
		if(src_path.equalsIgnoreCase("./src-gen")){
		listKonfig.setProperty("outputFolder", src_gen.getAbsolutePath());
		src_path = src_gen.getAbsolutePath();
		}
		else{
			listKonfig.setProperty("outputFolder", generatorPath.getText());
			src_path = generatorPath.getText();
		}
		listKonfig.setProperty("hostconfig",hostconfig.getText());
		listKonfig.setProperty("portconfig",portconfig.getText());
		listKonfig.setProperty("serverpath",serverPath.getText());
		listKonfig.setProperty("rootpath",rootPath.getText());
		listKonfig.setProperty("adminname",adminName.getText());
		listKonfig.setProperty("adminpass",adminPass.getText());
		if(checkChrome.getEnabled())
			listKonfig.setProperty("browser","chrome");
		if(checkie.getEnabled())
			listKonfig.setProperty("browser","ie");
		if(checkFirefox.getEnabled())
			listKonfig.setProperty("browser","firerfox");
		
		
		try {
			FileWriter genproperties = new FileWriter(src_path + "/generator.properties");
			listKonfig.store(genproperties, "Generator configuration");
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	
	

}
