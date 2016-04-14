package de.thm.icampus.joomdd.ejsl.gui

import java.awt.Dimension
import java.awt.FlowLayout
import java.awt.GridLayout
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.JButton
import javax.swing.JCheckBox
import javax.swing.JFileChooser
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField

class JooMDDGUI   extends JFrame implements ActionListener{
	
	private  JCheckBox page = new JCheckBox("Pages")
	private  JCheckBox entities = new JCheckBox("Entities")
	private JCheckBox updateFolder = new JCheckBox("Update Folder")
	private JCheckBox joomla = new JCheckBox("Joomla")
	private JCheckBox workpress = new JCheckBox("Wordpress")
	private  JTextField file = new JTextField(20)
	private JButton browse = new JButton("Browse")
	private JButton save = new JButton("Save")
	private JButton cancel = new JButton("Cancel")
	public JooMDDPropertiesHandler genProperties
	
	new(JooMDDPropertiesHandler properties){
		initGui(properties)
		
	}
	private def void initGui(JooMDDPropertiesHandler properties){
		if(properties == null){
		     genProperties = new JooMDDPropertiesHandler
			 genProperties.loadConfig
		}
		 else{
		 	genProperties = properties
		 }
		val te = this
		this.addWindowListener(new java.awt.event.WindowAdapter() {
    	override windowClosing(java.awt.event.WindowEvent windowEvent) {
	       {
	       	 te.enabled = false
	        }}})
		this.title = "JooMDD"
		this.preferredSize = new  Dimension(400,500)
		addComponent
		this.pack();
        this.setVisible(true);
        this.resizable = false
	}
	private def void addComponent(){
		var JPanel containerPanel = new JPanel()
		containerPanel.layout = new GridLayout(9,0)
		
		var JLabel artefact = new JLabel("Artefacts")
		containerPanel.add(artefact)
		
		var JPanel toGeneraFolder = new JPanel(new GridLayout(1,2))
		page.selected = Boolean.parseBoolean(genProperties.listKonfig.get("page") as String)
		page.addActionListener(this)
		toGeneraFolder.add(page)
		entities.selected = Boolean.parseBoolean(genProperties.listKonfig.get("entities") as String)
		entities.addActionListener(this)
		toGeneraFolder.add(entities)
		containerPanel.add(toGeneraFolder)
		var JLabel updateLabel = new JLabel("Update Folder for Extensions")
		containerPanel.add(updateLabel)
		
		updateFolder.selected = Boolean.parseBoolean(genProperties.listKonfig.get("updateFolder") as String)
		updateFolder.addActionListener(this)
		containerPanel.add(updateFolder)
		
		var JLabel generator = new JLabel("Generator:")
		containerPanel.add(generator)
		
		var JPanel useGenerator = new JPanel(new GridLayout(1,2))
		joomla.selected = Boolean.parseBoolean(genProperties.listKonfig.get("joomla") as String)
		joomla.addActionListener(this)
		workpress.selected = Boolean.parseBoolean(genProperties.listKonfig.get("wordpress") as String)
		workpress.addActionListener(this)
		useGenerator.add(joomla)
		useGenerator.add(workpress)
		containerPanel.add(useGenerator)
		
		var JLabel destination = new JLabel("Destination")
		containerPanel.add(destination)
		
		var JPanel chooseFilePanel = new JPanel(new FlowLayout)
		
		file.text = genProperties.listKonfig.get("outputFolder") as String
		file.setPreferredSize =  new Dimension(30,25)
		chooseFilePanel.add(file)
		
		browse.addActionListener(this)
		chooseFilePanel.add(browse)
		containerPanel.add(chooseFilePanel)
		
		var JPanel footerPanel = new JPanel(new FlowLayout)
		footerPanel.maximumSize = new Dimension(12,23)
		save.addActionListener(this)
		footerPanel.add(save)
		//close.setPreferredSize = new Dimension(12,1)
		cancel.addActionListener(this)
		
		
		footerPanel.add(cancel)
		containerPanel.add(footerPanel)
		this.contentPane.add(containerPanel)

		
	}
	override actionPerformed(ActionEvent action) {
		switch (action.source){
			case browse:{
				var JFileChooser chooser = new JFileChooser
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				var int returnVal = chooser.showOpenDialog(this);
				var String fileName = chooser.selectedFile.absolutePath
				file.visible = false
				file.text = fileName
				file.visible = true
				genProperties.listKonfig.setProperty("outputFolder", fileName)
				
			}
			case save:{
				genProperties.save
			}
			case page:{
				if(page.selected)
				   genProperties.listKonfig.setProperty("page", "true")
				 else{
				 	genProperties.listKonfig.setProperty("page", "false")
				 }
			}
			case entities:{
				if(entities.selected)
				   genProperties.listKonfig.setProperty("entities", "true")
				 else{
				 	genProperties.listKonfig.setProperty("entities", "false")
				 }
			}
			case updateFolder:{
				if(updateFolder.selected)
				   genProperties.listKonfig.setProperty("updateFolder", "true")
				 else{
				 	genProperties.listKonfig.setProperty("updateFolder", "false")
				 }
			}
			case joomla:{
				if(joomla.selected)
				   genProperties.listKonfig.setProperty("joomla", "true")
				 else{
				 	genProperties.listKonfig.setProperty("joomla", "false")
				 }
			}
			case workpress:{
				if(workpress.selected)
				   genProperties.listKonfig.setProperty("workpress", "true")
				 else{
				 	genProperties.listKonfig.setProperty("workpress", "false")
				 }
			}
			case file:{
				genProperties.listKonfig.setProperty("outputFolder", file.text)
				
			}
			case cancel:{
				this.enabled = false
				this.setVisible(false) 
				this.dispose() 
				
			}
		}
	}
	
	
}