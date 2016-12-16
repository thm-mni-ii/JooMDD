package de.thm.icampus.joomdd.ejsl.gui;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class ConfigGUI extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JTextField outputFolder;
	private JTextField adminName;
	private JTextField adminPass;
	private JTextField serverURL;
	private JTextField rootPath;
	private JTextField port;
	private JCheckBox pages;
	private JCheckBox entites;
	private JCheckBox test;
	private JCheckBox joomla;
	private JCheckBox workpress;
	private JRadioButton chrome, ie, firefox;
	private Properties configList;
	private JButton save, close, browse;
	private JCheckBox generateUpdate;
	public static boolean run;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConfigGUI window = new ConfigGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ConfigGUI() {
		configList = new Properties();
		initialize();
	}
	public Properties getConfigList(){
		return configList;
	}
	public JFrame getFrame(){
		return frame;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		run = true;
		frame = new JFrame();
		frame.setTitle("eJSL Generator Configuration");
		frame.setBounds(100, 100, 508, 420);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(0, 4, 0, 0));
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		
		JLabel lblArtefact = new JLabel("Artefact:");
		//lblArtefact.setFont(new Font("Tahoma", Font.PLAIN, 12));
		frame.getContentPane().add(lblArtefact);
		
		
		 pages = new JCheckBox("Pages");
		 pages.addActionListener(this);
		frame.getContentPane().add(pages);
		
		 entites = new JCheckBox("Entities");
		 entites.addActionListener(this);
		frame.getContentPane().add(entites);
		
		 test = new JCheckBox("Tests");
		frame.getContentPane().add(test);
		
		JLabel lblNewLabel = new JLabel("Update Folder:");
		//lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		frame.getContentPane().add(lblNewLabel);
		
		generateUpdate = new JCheckBox("yes");
		generateUpdate.addActionListener(this);
		frame.getContentPane().add(generateUpdate);
		
		JLabel label_1 = new JLabel("");
		frame.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("");
		frame.getContentPane().add(label_2);
		
		JLabel lblNewLabel_1 = new JLabel("Generator:");
		//lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		frame.getContentPane().add(lblNewLabel_1);
		
		 joomla = new JCheckBox("Joomla");
		 joomla.addActionListener(this);
		joomla.setSelected(true);
		frame.getContentPane().add(joomla);
		
		 workpress = new JCheckBox("Workpress");
		 workpress.addActionListener(this);
		frame.getContentPane().add(workpress);
		
		JLabel label_3 = new JLabel("");
		frame.getContentPane().add(label_3);
		
		JLabel lblNewLabel_2 = new JLabel("Destination:");
		//lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		frame.getContentPane().add(lblNewLabel_2);
		
		outputFolder = new JTextField();
		outputFolder.addActionListener(this);
		frame.getContentPane().add(outputFolder);
		outputFolder.setColumns(10);
		
		 browse = new JButton("Browse");
		 browse.addActionListener(this);
		frame.getContentPane().add(browse);
		
		JLabel label_4 = new JLabel("");
		frame.getContentPane().add(label_4);
		
		JLabel lblNewLabel_9 = new JLabel("");
		frame.getContentPane().add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("");
		frame.getContentPane().add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("");
		frame.getContentPane().add(lblNewLabel_11);
		
		JLabel lblNewLabel_12 = new JLabel("");
		frame.getContentPane().add(lblNewLabel_12);
		
		JLabel lblNewLabel_3 = new JLabel("Admin Name:");
		//lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		frame.getContentPane().add(lblNewLabel_3);
		
		adminName = new JTextField();
		adminName.addActionListener(this);
		frame.getContentPane().add(adminName);
		adminName.setColumns(10);
		
		JLabel label_5 = new JLabel("");
		frame.getContentPane().add(label_5);
		
		JLabel label_6 = new JLabel("");
		frame.getContentPane().add(label_6);
		
		JLabel lblNewLabel_13 = new JLabel("");
		frame.getContentPane().add(lblNewLabel_13);
		
		JLabel lblNewLabel_14 = new JLabel("");
		frame.getContentPane().add(lblNewLabel_14);
		
		JLabel lblNewLabel_15 = new JLabel("");
		frame.getContentPane().add(lblNewLabel_15);
		
		JLabel lblNewLabel_16 = new JLabel("");
		frame.getContentPane().add(lblNewLabel_16);
		
		JLabel adminPassLabel = new JLabel("Admin Pass:");
		//adminPassLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		frame.getContentPane().add(adminPassLabel);
		
		adminPass = new JTextField();
		adminPass.addActionListener(this);
		frame.getContentPane().add(adminPass);
		adminPass.setColumns(10);
		
		JLabel label_7 = new JLabel("");
		frame.getContentPane().add(label_7);
		
		JLabel label_8 = new JLabel("");
		frame.getContentPane().add(label_8);
		
		JLabel lblNewLabel_17 = new JLabel("");
		frame.getContentPane().add(lblNewLabel_17);
		
		JLabel lblNewLabel_18 = new JLabel("");
		frame.getContentPane().add(lblNewLabel_18);
		
		JLabel lblNewLabel_19 = new JLabel("");
		frame.getContentPane().add(lblNewLabel_19);
		
		JLabel lblNewLabel_20 = new JLabel("");
		frame.getContentPane().add(lblNewLabel_20);
		
		JLabel lblNewLabel_5 = new JLabel("Server URL:");
		//lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		frame.getContentPane().add(lblNewLabel_5);
		
		serverURL = new JTextField();
		serverURL.addActionListener(this);
		frame.getContentPane().add(serverURL);
		serverURL.setColumns(10);
		
		JLabel label_9 = new JLabel("");
		frame.getContentPane().add(label_9);
		
		JLabel label_10 = new JLabel("");
		frame.getContentPane().add(label_10);
		
		JLabel lblNewLabel_21 = new JLabel("");
		frame.getContentPane().add(lblNewLabel_21);
		
		JLabel lblNewLabel_22 = new JLabel("");
		frame.getContentPane().add(lblNewLabel_22);
		
		JLabel lblNewLabel_23 = new JLabel("");
		frame.getContentPane().add(lblNewLabel_23);
		
		JLabel lblNewLabel_24 = new JLabel("");
		frame.getContentPane().add(lblNewLabel_24);
		
		JLabel lblNewLabel_6 = new JLabel("Root Path:");
		//lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		frame.getContentPane().add(lblNewLabel_6);
		
		rootPath = new JTextField();
		rootPath.addActionListener(this);
		frame.getContentPane().add(rootPath);
		rootPath.setColumns(10);
		
		JLabel label_11 = new JLabel("");
		frame.getContentPane().add(label_11);
		
		JLabel label_12 = new JLabel("");
		frame.getContentPane().add(label_12);
		
		JLabel lblNewLabel_25 = new JLabel("");
		frame.getContentPane().add(lblNewLabel_25);
		
		JLabel lblNewLabel_26 = new JLabel("");
		frame.getContentPane().add(lblNewLabel_26);
		
		JLabel lblNewLabel_27 = new JLabel("");
		frame.getContentPane().add(lblNewLabel_27);
		
		JLabel lblNewLabel_28 = new JLabel("");
		frame.getContentPane().add(lblNewLabel_28);
		
		JLabel lblNewLabel_7 = new JLabel("Port:");
		//lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 12));
		frame.getContentPane().add(lblNewLabel_7);
		
		port = new JTextField();
		port.addActionListener(this);
		frame.getContentPane().add(port);
		port.setColumns(5);
		
		JLabel label_13 = new JLabel("");
		frame.getContentPane().add(label_13);
		
		JLabel label_14 = new JLabel("");
		frame.getContentPane().add(label_14);
		
		JLabel lblNewLabel_8 = new JLabel("Browser");
		//lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 12));
		frame.getContentPane().add(lblNewLabel_8);
		
		 chrome = new JRadioButton("Chrome");
		 chrome.addActionListener(this);
		chrome.setSelected(true);
		frame.getContentPane().add(chrome);
		
		 firefox = new JRadioButton("Firefox");
		 firefox.addActionListener(this);
		frame.getContentPane().add(firefox);
		
		 ie = new JRadioButton("Internet Explorer");
		 ie.addActionListener(this);
		frame.getContentPane().add(ie);
		
		JLabel label = new JLabel("");
		frame.getContentPane().add(label);
		
		 save = new JButton("save");
		save.addActionListener(this);
		frame.getContentPane().add(save);
		
		JLabel lblNewLabel_4 = new JLabel("");
		frame.getContentPane().add(lblNewLabel_4);
		
		 close = new JButton("close");
		close.addActionListener(this);
		frame.getContentPane().add(close);
	}
    public Boolean getRun(){
    	return run;
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == browse){
			JFileChooser chooser = new JFileChooser();
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			 int returnVal = chooser.showOpenDialog(frame);
			 String fileName = chooser.getSelectedFile().getAbsolutePath();
			 outputFolder.setVisible(false);
			 outputFolder.setText(fileName); 
			 outputFolder.setVisible(true);
		}
		
		else if(e.getSource() == close){
			if(outputFolder.getText()!=null)
				JOptionPane.showMessageDialog(frame, "Please put the Path for the Destination");
			run = false;
			saveListConfig();
			FileWriter writer;
			try {
				writer = new FileWriter( "./generator.properties");
				configList.store(writer, "Configuraion for Generator");
				frame.dispose();
				run=false;

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				run = false;
				JOptionPane.showMessageDialog(frame, "The Destination paht is invalid!");
				e1.printStackTrace();
				
			}
			frame.dispose();
		}
		
	
		else if(e.getSource() == save){
			
			if(outputFolder.getText()!=null){
				saveListConfig();
				FileWriter writer;
				try {
					writer = new FileWriter(configList.getProperty("outputFolder") + "/generator.properties");
					configList.store(writer, "Configuraion for Generator");
					frame.dispose();
					run=false;

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					run = false;
					JOptionPane.showMessageDialog(frame, "The Destination paht is invalid!");
					e1.printStackTrace();
					
				}
				
			}else{
				run = false;
				JOptionPane.showMessageDialog(null, "The data cannot to be save, Please put a path  to Destination!");
			}
		}
		
	}

	private void saveListConfig() {
		configList.setProperty("pages", pages.isSelected()+"");
	
		configList.setProperty("entities", entites.isSelected()+"");
	
		configList.setProperty("tests", test.isSelected()+"");
	
		configList.setProperty("updtadefolder", generateUpdate.isSelected()+"");
	
		configList.setProperty("joomla", joomla.isSelected()+"");
	
		configList.setProperty("workpress", workpress.isSelected()+"");
	
		configList.setProperty("adminName", adminName.getText());
	
		configList.setProperty("adminPass", adminPass.getText());
	
		configList.setProperty("serverUrl", serverURL.getText());
	    if(rootPath.getText().contains("\\")){
		configList.setProperty("rootPath", rootPath.getText().replaceAll(Pattern.quote("\\"), Matcher.quoteReplacement("\\\\")));
	    }else{
	    configList.setProperty("rootPath", rootPath.getText());	}
	    
	    if(outputFolder.getText().contains("\\")){
		configList.setProperty("outputFolder", outputFolder.getText().replaceAll(Pattern.quote("\\"), Matcher.quoteReplacement("\\\\")));
	    }else{
	    configList.setProperty("outputFolder", outputFolder.getText());	}

	
		configList.setProperty("port", port.getText());
	    if(ie.isEnabled())
		configList.setProperty("browser", "ie");
	    if(chrome.isEnabled())
		configList.setProperty("browser", "chrome");
	    if(firefox.isEnabled())
		configList.setProperty("browser", "firefox");
		
		
	}

}
