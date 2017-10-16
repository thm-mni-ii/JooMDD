package classes;

import com.intellij.ide.util.projectWizard.SettingsStep;
import com.intellij.openapi.ui.ValidationInfo;
import com.intellij.openapi.util.IconLoader;
import com.intellij.platform.WebProjectGenerator;
import com.intellij.ui.AncestorListenerAdapter;
import com.intellij.ui.components.JBCheckBox;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.components.JBTextField;
import com.intellij.util.PathUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Created by Max on 09.03.2016.
 */
public class eJSL_PHP_Wizard_Step implements WebProjectGenerator.GeneratorPeer {
    private static String option = "";

    public static String getOption(){
        return option;
    }
    public static void setOption(String newoption) {option = newoption;}

    public static String gereratorProperties ;
    public static String outputPath;

    public static String getOutputPath() {return outputPath;}

    public static void setOutputPath(String path) {
        outputPath = path;
    }

    public static String getGereratorProperties() {return gereratorProperties;}

    public String getPreviewFromFile(String filename){

        StringBuilder preview = new StringBuilder();

        try{
            FileReader fr = new FileReader(PathUtil.getJarPathForClass(getClass()) + "/templates/"+filename);
            BufferedReader br = new BufferedReader(fr);

            String buffer = "";
            String text = "";
            int maxLines = 12;
            int lines = 0;

            while ((buffer = br.readLine()) != null) {

                text += buffer + "\n";
                lines+=1;
                if(lines>maxLines){
                    break;
                }
            }
            preview.append(text);
            br.close();
            fr.close();
        }  catch (Exception e1) {
            e1.printStackTrace();
        }
        return preview.toString();
    }


    public static void createGeneratorProperties(boolean page, boolean enteties, boolean joomla, boolean wordpress , Boolean PHPUnit ,Boolean UpdateFolder,
                                          Boolean Codeception, String Host, String Port, String AdminName, String AdminPasswort, Boolean Chrome,
                                          Boolean IE, Boolean Firefox, String Path, String DBUSerName, String DBUserPwd, String Serverpath){
        StringBuilder properties = new StringBuilder();

        String browser = "";
        if(Firefox) {
            browser = "firefox";
        }else if (IE){
            browser = "ie";
        }else if(Chrome){
            browser = "chrome";
        }




        properties.append("#---JooMDD---\n");
        properties.append("serverUrl="+Host+"\n");
        properties.append("browser="+browser+"\n");
        properties.append("entities="+page+"\n");
        properties.append("wordpress="+wordpress+"\n");
        properties.append("updateFolder="+page+"\n");
        properties.append("rootpath="+page+"\n");
        properties.append("page="+page+"\n");
        properties.append("test="+page+"\n");
        properties.append("serverpath="+Serverpath+"\n");
        properties.append("portconfig="+Port+"\n");
        properties.append("adminpass="+AdminPasswort+"\n");
        properties.append("joomla="+joomla+"\n");
       // outputFolder will append at project creation
        properties.append("adminname="+AdminName+"\n");

/*      old structure
        properties.append("enteties="+enteties+"\n");
        properties.append("joomla="+joomla+"\n");
        properties.append("wordpress="+wordpress+"\n");
        properties.append("PHPUnit="+PHPUnit+"\n");
        properties.append("outputFolder="+UpdateFolder+"\n");
        properties.append("Codeceptio="+Codeception+"\n");
        properties.append("Host="+Host+"\n");
        properties.append("Port="+Port+"\n");
        properties.append("AdminName="+AdminName+"\n");
        properties.append("AdminPasswort="+AdminPasswort+"\n");
        properties.append("Chrome="+Chrome+"\n");
        properties.append("IE="+IE+"\n");
        properties.append("Firefox="+Firefox+"\n");
        properties.append("Path="+Path+"\n");
        properties.append("DBUSerName="+DBUSerName+"\n");
        properties.append("DBUserPwd="+DBUserPwd+"\n");
        properties.append("Serverpath="+Serverpath+"\n");


        //properties.append("")+;
*/
        //properties.append("folderstructure="+folderstructure+"\n");
        gereratorProperties = properties.toString();
    }


    @NotNull
    @Override
    public JComponent getComponent() {
        TemplateList tempList = new TemplateList();

        try {
            tempList = TemplateXMLLoader.loadTemplates(PathUtil.getJarPathForClass(getClass()) + "/templates/TemplateList.xml", PathUtil.getJarPathForClass(getClass()) + "/templates/TemplateList.xsd");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Object[] t = tempList.getTemplates();
        Template[] temps = new Template[t.length];

        for (int i = 0; i < t.length; i++){
            temps[i] = (Template) t[i];
        }

        JPanel panel = new JPanel(new BorderLayout());

        JPanel selectPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));
        JPanel secondPanel = new JPanel(new BorderLayout());
        JPanel descriptionPanel = new JPanel(new BorderLayout());
        JPanel previewPanel = new JPanel((new BorderLayout()));

        JLabel joomddlogo = new JLabel(IconLoader.getIcon("/resources/icons/Logo_b_php_small.png"));





        JLabel autosaveWarning = new JLabel(IconLoader.getIcon("/resources/icons/Infomation.png"));
        autosaveWarning.setText(
                "<html><br> We suggest you to disable the Autosave-Function.<br>" +
                        "Under Settings/Apperance & Behavior/System Settings:<br>" +
                        "Check Confirm application exit, " +"Uncheck Save files on frame deactivation, " + "Uncheck Save files automatically<br>" +
                        "Under Editor/General/Editor Tabs:<br>" +
                        "Set Mark modified tabs with asterisk<html>"
        );

        JLabel selectLabel = new JLabel("Load example:");
        JLabel descriptionLabel = new JLabel("<html><br>Description:<html>");
        JLabel descriptionText = new JLabel();
        JLabel previewLabel = new JLabel("Preview:");
        JTextArea previewText  = new JTextArea("");
        previewText.setEnabled(false);


        JBScrollPane selectScroll = new JBScrollPane(buttonPanel);
        selectScroll.setPreferredSize(new Dimension(150,250));
        JBScrollPane previewScroll = new JBScrollPane(previewLabel);
        previewScroll.setPreferredSize(new Dimension(400,250));

        panel.add(joomddlogo, BorderLayout.NORTH);
        panel.add(selectPanel, BorderLayout.WEST);
        panel.add(secondPanel, BorderLayout.CENTER);
        panel.add(autosaveWarning, BorderLayout.SOUTH);

        JRadioButton[] radio = new JRadioButton[temps.length];

        selectPanel.add(selectLabel, BorderLayout.NORTH);
        selectPanel.add(selectScroll, BorderLayout.CENTER);

        secondPanel.add(descriptionPanel,BorderLayout.NORTH);
        secondPanel.add(previewPanel,BorderLayout.CENTER);


        descriptionPanel.add(descriptionLabel, BorderLayout.NORTH);
        descriptionPanel.add(previewScroll, BorderLayout.CENTER);
        descriptionPanel.add(descriptionText, BorderLayout.CENTER);

        previewPanel.add(previewLabel, BorderLayout.NORTH);
        previewPanel.add(previewText,BorderLayout.CENTER);


        // New options for gernatro.properties

        JLabel generatorInfoLableArtifacts = new JLabel("Artifacts options:");
        JCheckBox checkboxPage = new JCheckBox ("Page");
        JCheckBox checkboxEntieties = new JCheckBox ("Entities");

        //JLabel generatorInfoLableupdatefolder = new JLabel("Extesnsion Package output:");

        /*
        ButtonGroup folderstrucure = new ButtonGroup();
        JRadioButton radiobtnzip = new JRadioButton (".zip");
        JRadioButton radbtnfolder= new JRadioButton ("Folder Structure");
        folderstrucure.add(radiobtnzip);
        folderstrucure.add(radbtnfolder);
        */

        JLabel generatorInfoLablegernerator = new JLabel("Generator options:");
        JCheckBox checkboxJoomla = new JCheckBox ("Joomla!");
        JCheckBox checkboxWordpress = new JCheckBox ("Wordpress");
        JCheckBox checkBoxGenUpdateFolder = new JCheckBox("Generate Update Folder");

        JLabel lblTestConfiguration = new JLabel("Test Configuration:");
        ButtonGroup btngroupframework = new ButtonGroup();
        JRadioButton radiobtnPHPUnit = new JRadioButton ("PHPUnit");
        JRadioButton radiobtnCodeception = new JRadioButton ("Codeception");
        btngroupframework.add(radiobtnPHPUnit);
        btngroupframework.add(radiobtnCodeception);


        JLabel lblOutputPath = new JLabel("Output Path:                       ");
        JTextField txtOutputPath = new JBTextField();
        JLabel lbllogindaten = new JLabel("Host configuration:           ");
        JTextField txtHostConfiguration = new JBTextField();
        JLabel lbllPort = new JLabel("Port configuration:            ");
        JTextField txtPort = new JBTextField();
        JLabel lbladminname = new JLabel("Admin Name:                     ");
        JTextField txtadminname = new JBTextField();
        JLabel lbladminpwd = new JLabel("Admin password:               ");
        JTextField txtadminpwd = new JBTextField();
        JLabel lblbrowser = new JLabel("Browser:");
        JCheckBox browserChrome = new JBCheckBox("Chrome");
        JCheckBox browserInternetExplorer = new JBCheckBox("Internet Explorer");
        JCheckBox browserFirefox = new JBCheckBox("Firefox                        ");
        JTextField txtbrowser = new JBTextField();
        JLabel lblpath = new JLabel("Website Path:                      ");
        JTextField txtpath = new JBTextField();
        JLabel lblDBUsername = new JLabel("DB Username:                     ");
        JTextField txtDBUsername = new JBTextField();
        JLabel lblDBUserpwd = new JLabel("DB Userpassword:              ");
        JTextField txtDBUserpwd = new JBTextField();
        JLabel lblServerpath = new JLabel("Serverpath:                          ");
        JTextField txtServerpath = new JBTextField();


        JPanel panelsouth1 = new JPanel(new BorderLayout());
        panel.add(panelsouth1, BorderLayout.SOUTH);


        panelsouth1.add(generatorInfoLableArtifacts,BorderLayout.NORTH);
        panelsouth1.add(checkboxEntieties,BorderLayout.CENTER);
        panelsouth1.add(checkboxPage,BorderLayout.WEST);

        JPanel panelsouth2 = new JPanel(new BorderLayout());
        panelsouth1.add(panelsouth2,BorderLayout.SOUTH);
        /*
        panelsouth2.add(generatorInfoLablegernerator, BorderLayout.NORTH);
        panelsouth2.add(checkboxJoomla, BorderLayout.CENTER);
        panelsouth2.add(checkboxWordpress, BorderLayout.WEST);

        JPanel panelsouth2_1 = new JPanel(new BorderLayout());
        panelsouth2.add(panelsouth2_1,BorderLayout.SOUTH);

        panelsouth2_1.add(checkBoxGenUpdateFolder, BorderLayout.NORTH);


        JPanel panelsouth3 = new JPanel(new BorderLayout());
        panelsouth2_1.add(panelsouth3,BorderLayout.SOUTH);
        */
        /*
        panelsouth3.add(generatorInfoLableupdatefolder, BorderLayout.NORTH);
        panelsouth3.add(radbtnfolder, BorderLayout.WEST);
        panelsouth3.add(radiobtnzip, BorderLayout.CENTER);
        */
        /*
        JPanel panelsouth4 = new JPanel(new BorderLayout());
        panelsouth3.add(panelsouth4,BorderLayout.SOUTH);

        panelsouth4.add(lblTestConfiguration, BorderLayout.NORTH);
        panelsouth4.add(radiobtnCodeception, BorderLayout.CENTER);
        panelsouth4.add(radiobtnPHPUnit, BorderLayout.WEST);

        JPanel panelsouth4_1 = new JPanel(new BorderLayout());
        panelsouth4.add(panelsouth4_1,BorderLayout.SOUTH);
        panelsouth4_1.add(lblOutputPath, BorderLayout.WEST);
        panelsouth4_1.add(txtOutputPath, BorderLayout.CENTER);


        JPanel panelsouth5 = new JPanel(new BorderLayout());
        panelsouth4_1.add(panelsouth5,BorderLayout.SOUTH);
        panelsouth5.add(lbllogindaten, BorderLayout.WEST);
        panelsouth5.add(txtHostConfiguration, BorderLayout.CENTER);

        JPanel panelsouth5_2 = new JPanel(new BorderLayout());
        panelsouth5.add(panelsouth5_2,BorderLayout.SOUTH);
        panelsouth5_2.add(lbllPort,BorderLayout.WEST);
        panelsouth5_2.add(txtPort,BorderLayout.CENTER);

        JPanel panelsouth6 = new JPanel(new BorderLayout());
        panelsouth5_2.add(panelsouth6,BorderLayout.SOUTH);
        panelsouth6.add(lbladminname, BorderLayout.WEST);
        panelsouth6.add(txtadminname, BorderLayout.CENTER);

        JPanel panelsouth7 = new JPanel(new BorderLayout());
        panelsouth6.add(panelsouth7,BorderLayout.SOUTH);
        panelsouth7.add(lbladminpwd,BorderLayout.WEST);
        panelsouth7.add(txtadminpwd,BorderLayout.CENTER);

        JPanel panelsouth8 = new JPanel(new BorderLayout());
        panelsouth7.add(panelsouth8,BorderLayout.SOUTH);
        panelsouth8.add(lblbrowser,BorderLayout.NORTH);
        panelsouth8.add(browserChrome, BorderLayout.CENTER);
        panelsouth8.add(browserFirefox, BorderLayout.WEST);
        panelsouth8.add(browserInternetExplorer, BorderLayout.EAST);

        JPanel panelsouth9 = new JPanel(new BorderLayout());
        panelsouth8.add(panelsouth9,BorderLayout.SOUTH);
        panelsouth9.add(lblpath,BorderLayout.WEST);
        panelsouth9.add(txtpath, BorderLayout.CENTER);

        JPanel panelsouth10 = new JPanel(new BorderLayout());
        panelsouth9.add(panelsouth10,BorderLayout.SOUTH);
        panelsouth10.add(lblDBUsername,BorderLayout.WEST);
        panelsouth10.add(txtDBUsername,BorderLayout.CENTER);

        JPanel panelsouth11 = new JPanel(new BorderLayout());
        panelsouth10.add(panelsouth11,BorderLayout.SOUTH);
        panelsouth11.add(lblDBUserpwd,BorderLayout.WEST);
        panelsouth11.add(txtDBUserpwd,BorderLayout.CENTER);

        JPanel panelsouth12 = new JPanel(new BorderLayout());
        panelsouth11.add(panelsouth12,BorderLayout.SOUTH);
        panelsouth12.add(lblServerpath,BorderLayout.WEST);
        panelsouth12.add(txtServerpath, BorderLayout.CENTER);
        */

        panelsouth1.add(autosaveWarning,BorderLayout.SOUTH);// end


        // Standard configuration
        checkboxPage.setSelected(true);
        checkboxEntieties.setSelected(true);
        checkboxJoomla.setSelected(true);
        checkboxWordpress.setSelected(true);
        //radbtnfolder.setSelected(true);
        radiobtnPHPUnit.setSelected(true);
        txtOutputPath.setText("/src-gen/");
        txtadminname.setText("admin");
        txtadminpwd.setText("admin");
        txtbrowser.setText("");
        txtDBUsername.setText("DBUser");
        txtDBUserpwd.setText("");
        txtHostConfiguration.setText("http://localhost");
        txtpath.setText("");
        txtServerpath.setText("c:/xampp/htdocs");
        txtPort.setText("4445");



        selectLabel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        selectScroll.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        previewLabel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        previewScroll.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));


        for (int i = 0; i < radio.length; i++){
            radio[i] = new JRadioButton(temps[i].getName());
            buttonPanel.add(radio[i]);

            final int j = i;
            radio[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(radio[j].isSelected()){
                        setOption(temps[j].getSrc().toString());

                        for(int k = 0; k < radio.length; k++){
                            if(k != j) radio[k].setSelected(false);
                        }
                        if(temps[j].getDescription() == null){
                            descriptionText.setText("This template has no description.");
                        }else {
                            descriptionText.setText(temps[j].getDescription().toString());
                        }
                        previewText.setText(getPreviewFromFile(temps[j].getSrc().toString()));
                    }
                }
            });
        }
        radio[0].setSelected(true);
        setOption(temps[0].getSrc().toString());
        previewText.setText(getPreviewFromFile(temps[0].getSrc().toString()));
        if(temps[0].getDescription() == null){
            descriptionText.setText("This template has no description.");
        }else {
            descriptionText.setText(temps[0].getDescription().toString());
        }

        // Read all data

        panel.addAncestorListener(new AncestorListenerAdapter() {
            @Override
            public void ancestorRemoved(AncestorEvent event) {
                super.ancestorRemoved(event);

                setOutputPath(txtOutputPath.getText());

                createGeneratorProperties(checkboxPage.isSelected(),checkboxEntieties.isSelected(),checkboxJoomla.isSelected(),checkboxWordpress.isSelected(),checkBoxGenUpdateFolder.isSelected(),
                        radiobtnPHPUnit.isSelected(),radiobtnCodeception.isSelected(),txtHostConfiguration.getText(),txtPort.getText(),txtadminname.getText(),
                        txtadminpwd.getText(),browserChrome.isSelected(),browserInternetExplorer.isSelected(),browserFirefox.isSelected(),txtpath.getText(),
                        txtDBUsername.getText(),txtDBUserpwd.getText(),txtServerpath.getText());
            }
        });



        return panel;
    }

    @Override
    public void buildUI(@NotNull SettingsStep settingsStep) {

    }

    @NotNull
    @Override
    public Object getSettings() {
        return "";
    }

    @Nullable
    @Override
    public ValidationInfo validate() {
        return null;
    }

    @Override
    public boolean isBackgroundJobRunning() {
        return false;
    }

    @Override
    public void addSettingsStateListener(@NotNull WebProjectGenerator.SettingsStateListener settingsStateListener) {

    }
}
