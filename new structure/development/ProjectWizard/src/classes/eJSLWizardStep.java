package classes;

import com.intellij.ide.util.projectWizard.ModuleWizardStep;
import com.intellij.util.PathUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * Created by Max on 28.01.2016.
 */
public class eJSLWizardStep extends ModuleWizardStep {

    private static String option = "";
    private static boolean wizardstatus = false;

    public static void setwizardstatus(boolean status) {wizardstatus = status;}
    public static String getOption(){
        return option;
    }
    public static void setOption(String newoption) {option = newoption;}
    public static boolean getwizardactive() {return  wizardstatus;}

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
        JPanel logoPanel = new JPanel(new BorderLayout());
        JPanel selectPanel = new JPanel(new GridLayout(temps.length+1, 1));
        JPanel previewPanel = new JPanel(new BorderLayout());

        JLabel selectLabel = new JLabel("Load example:");
        JLabel previewLabel = new JLabel("Preview:");
        JLabel previewText  = new JLabel();

        panel.add(logoPanel, BorderLayout.NORTH);
        panel.add(selectPanel, BorderLayout.WEST);
        panel.add(previewPanel, BorderLayout.CENTER);

        JRadioButton[] radio = new JRadioButton[temps.length];

        selectPanel.add(selectLabel);
        previewPanel.add(previewLabel, BorderLayout.NORTH);
        previewPanel.add(previewText, BorderLayout.CENTER);


        for (int i = 0; i < radio.length; i++){
            radio[i] = new JRadioButton(temps[i].getName());
            selectPanel.add(radio[i]);

            final int j = i;
            radio[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(radio[j].isSelected()){
                        for(int k = 0; k < radio.length; k++){
                            if(k != j) radio[k].setSelected(false);
                        }
                        setOption(radio[j].getName());
                        if(temps[j].getPreview() == null){
                            previewText.setText("Für diese Auswahl ist leider keine Preview verfügbar.");
                        }else {
                            previewText.setText(temps[j].getPreview().toString());
                        }
                    }
                }
            });
        }
        radio[0].setSelected(true);
        if(temps[0].getPreview() == null){
            previewText.setText("Für diese Auswahl ist leider keine Preview verfügbar.");
        }else {
            previewText.setText(temps[0].getPreview().toString());
        }




        /*
        File resource_dir = new File(PathUtil.getJarPathForClass(getClass()) + "/resources/eJSLexamples/");
        File files[] = resource_dir.listFiles();

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        ButtonGroup radioGroup = new ButtonGroup();


        JRadioButton radio[] = new JRadioButton[files.length];
        JTextArea lblpreview[] = new JTextArea[files.length];

        JLabel lbl1 = new JLabel("Load example:");
        panel.add(lbl1);

        StringBuilder preview = new StringBuilder();
        int i;
        for (i=0;files.length>i;i++) {

            try {
                FileReader fr = new FileReader(PathUtil.getJarPathForClass(getClass()) + "/resources/eJSLexamples/"+files[i].getName());
                BufferedReader br = new BufferedReader(fr);
                String buffer = "";
                String text = "";
                int maxLines = 10;
                int lines = 0;
                int length = -1;
                while ((buffer = br.readLine()) != null) {
                    text += buffer + "\n";
                    if ((lines ++) <= maxLines) length += buffer.length();
                }
                if (text.isEmpty()){
                    text = "Dieses Beispiel läd ein leeres Projoekt.";
                } else if (text.length() > length) text = text.substring(0, length) + "\n...";
                preview.append(text);

                br.close();
                fr.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            lblpreview[i] = new JTextArea(preview.toString());
            lblpreview[i].setEnabled(false);

            preview.setLength(0);

            radio[i] = new JRadioButton(files[i].getName());
            radio[i].setName(files[i].getName());
            radioGroup.add(radio[i]);
            lblpreview[i].setVisible(false);
            if (i==0){
                lblpreview[i].setVisible(true);
                setOption(files[i].getName());
                radio[i].setSelected(true);
            }


            final int j = i ;
            radio[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(radio[j].isSelected()){
                        setOption(radio[j].getName());
                        for (int i= 0; i<files.length;i++){
                            lblpreview[i].setVisible(false);
                        }
                        lblpreview[j].setVisible(true);
                    }
                }
            });
            c.gridy = i+1;
            panel.add(radio[i],c);
        }


        JLabel lbl2 = new JLabel("Preview:                                                                                                              ");
        c.gridx =1;
        c.gridy = 0;
        panel.add(lbl2,c);

        c.gridy = 1;
        c.gridwidth = 5;
        c.gridheight= 10;
        for (i=0;files.length>i;i++) {
            panel.add(lblpreview[i],c);
        }
        */
        return panel ;
    }

    @Override
    public void updateDataModel() {
        wizardstatus = true ;
    }
}