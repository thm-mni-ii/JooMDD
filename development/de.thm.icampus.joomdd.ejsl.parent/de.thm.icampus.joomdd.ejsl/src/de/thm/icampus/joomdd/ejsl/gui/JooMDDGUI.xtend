package de.thm.icampus.joomdd.ejsl.gui

import java.awt.Dimension
import java.awt.FlowLayout
import java.awt.GridLayout
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import javax.swing.JButton
import javax.swing.JCheckBox
import javax.swing.JFileChooser
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField
import javax.swing.JRadioButton
import java.util.Properties

class JooMDDGUI   extends JFrame implements ActionListener{
	
	private  JCheckBox page = new JCheckBox("Pages")
	private  JCheckBox entities = new JCheckBox("Entities")
	private  JCheckBox test = new JCheckBox("Tests")
	private JCheckBox updateFolder = new JCheckBox("Update Folder")
	private JCheckBox joomla = new JCheckBox("Joomla")
	private JCheckBox workpress = new JCheckBox("Wordpress")
	private  JTextField file = new JTextField(20)
	private  JTextField userName = new JTextField("Admin Name",20)
	private  JTextField userPass = new JTextField("Admin Pass",20)
	private  JTextField serverPath = new JTextField("Server Path",20)
	private  JTextField localHost = new JTextField("Host Url",20)
	private  JTextField port = new JTextField("Port",10)
	private JRadioButton chrome= new JRadioButton("Chrome", true)
	private JRadioButton ie= new JRadioButton("Internet Explorer")
	private JRadioButton firefox= new JRadioButton("Firefox")
	private JButton browse = new JButton("Browse")
	private JButton save = new JButton("Save")
	private JButton cancel = new JButton("Close")
	public Properties genProperties
	
	new(Properties properties){
		genProperties = properties
		initGui(properties)
		
	}
	private def void initGui(Properties properties){
		val te = this
		this.addWindowListener(new WindowAdapter() {
    	override windowClosing(WindowEvent windowEvent) {
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
		containerPanel.layout = new GridLayout(16,0)
		
		var JLabel artefact = new JLabel("Artefacts")
		containerPanel.add(artefact)
		
		var JPanel toGeneraFolder = new JPanel(new GridLayout(1,3))
		page.selected = Boolean.parseBoolean(genProperties.get("page") as String)
		page.addActionListener(this)
		toGeneraFolder.add(page)
		entities.selected = Boolean.parseBoolean(genProperties.get("entities") as String)
		entities.addActionListener(this)
		toGeneraFolder.add(entities)
		test.addActionListener(this)
		toGeneraFolder.add(test)
		containerPanel.add(toGeneraFolder)
		var JLabel updateLabel = new JLabel("Update Folder for Extensions")
		containerPanel.add(updateLabel)
		
		updateFolder.selected = Boolean.parseBoolean(genProperties.get("updateFolder") as String)
		updateFolder.addActionListener(this)
		containerPanel.add(updateFolder)
		
		var JLabel generator = new JLabel("Generator:")
		containerPanel.add(generator)
		
		var JPanel useGenerator = new JPanel(new GridLayout(1,2))
		joomla.selected = Boolean.parseBoolean(genProperties.get("joomla") as String)
		joomla.addActionListener(this)
		workpress.selected = Boolean.parseBoolean(genProperties.get("wordpress") as String)
		workpress.addActionListener(this)
		useGenerator.add(joomla)
		useGenerator.add(workpress)
		containerPanel.add(useGenerator)
		
		var JPanel testOption = new JPanel(new GridLayout(1,2))
		var JLabel userNameLabel = new JLabel("User Name")
		testOption.add(userNameLabel)
		userName.setPreferredSize =  new Dimension(30,25)
		userName.addActionListener(this)
		testOption.add(userName,CENTER_ALIGNMENT)
		containerPanel.add(testOption)
		
		var JLabel destination = new JLabel("Destination")
		containerPanel.add(destination)
		
		var JPanel chooseFilePanel = new JPanel(new FlowLayout)
		
		file.text = genProperties.get("outputFolder") as String
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
				genProperties.setProperty("outputFolder", fileName)
				
			}
			case save:{
				genProperties.saveProperties()
			}
			case page:{
				if(page.selected)
				   genProperties.setProperty("page", "true")
				 else{
				 	genProperties.setProperty("page", "false")
				 }
			}
			case entities:{
				if(entities.selected)
				   genProperties.setProperty("entities", "true")
				 else{
				 	genProperties.setProperty("entities", "false")
				 }
			}
			case updateFolder:{
				if(updateFolder.selected)
				   genProperties.setProperty("updateFolder", "true")
				 else{
				 	genProperties.setProperty("updateFolder", "false")
				 }
			}
			case joomla:{
				if(joomla.selected)
				   genProperties.setProperty("joomla", "true")
				 else{
				 	genProperties.setProperty("joomla", "false")
				 }
			}
			case workpress:{
				if(workpress.selected)
				   genProperties.setProperty("workpress", "true")
				 else{
				 	genProperties.setProperty("workpress", "false")
				 }
			}
			case file:{
				genProperties.setProperty("outputFolder", file.text)
				
			}
			case cancel:{
				this.enabled = false
				this.setVisible(false) 
				this.dispose() 
				
			}
		}
	}
	
	def saveProperties(Properties properties) {
		
	}
	
	static def void main(String[] args){
		
		new JooMDDGUI(new Properties)
		
	}
	
	
}