package classes;

import com.intellij.ide.util.projectWizard.ModuleWizardStep;
import com.intellij.openapi.util.IconLoader;
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

    public String getPreviewFromFile(String filename){

        StringBuilder preview = new StringBuilder();

        try{
            FileReader fr = new FileReader(PathUtil.getJarPathForClass(getClass()) + "/templates/"+filename);
            BufferedReader br = new BufferedReader(fr);

            String buffer = "";
            String text = "";
            int maxLines = 20;
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
        //ImagePanel logoPanel = new ImagePanel("/resources/icons/Logo_b.png");
        JPanel selectPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));
        JPanel secondPanel = new JPanel(new BorderLayout());
        JPanel descriptionPanel = new JPanel(new BorderLayout());
        JPanel previewPanel = new JPanel((new BorderLayout()));

        JLabel joomddlogo = new JLabel(IconLoader.getIcon("/resources/icons/Logo_b_small.png"));
        JLabel selectLabel = new JLabel("Load example:");
        JLabel descriptionLabel = new JLabel("<html><br>Description:<html>");
        JLabel descriptionText = new JLabel();
        JLabel previewLabel = new JLabel("Preview:");
        //JLabel previewText  = new JLabel("Preview Text");
        JTextArea previewText  = new JTextArea("");
        previewText.setEnabled(false);

        JScrollPane selectScroll = new JScrollPane(buttonPanel);
        selectScroll.setPreferredSize(new Dimension(150,250));
        JScrollPane previewScroll = new JScrollPane(previewLabel);
        previewScroll.setPreferredSize(new Dimension(400,250));

        panel.add(joomddlogo, BorderLayout.NORTH);
        panel.add(selectPanel, BorderLayout.WEST);
        panel.add(secondPanel, BorderLayout.CENTER);

        JRadioButton[] radio = new JRadioButton[temps.length];

        selectPanel.add(selectLabel, BorderLayout.NORTH);
        selectPanel.add(selectScroll, BorderLayout.CENTER);

        secondPanel.add(descriptionPanel,BorderLayout.NORTH);
        secondPanel.add(previewPanel,BorderLayout.CENTER);

        descriptionPanel.add(descriptionLabel, BorderLayout.NORTH);
        descriptionPanel.add(previewScroll, BorderLayout.CENTER);
        descriptionPanel.add(descriptionText, BorderLayout.CENTER);
        //descriptionPanel.add(previewPanel, BorderLayout.SOUTH);

        previewPanel.add(previewLabel, BorderLayout.NORTH);
        previewPanel.add(previewText,BorderLayout.CENTER);

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
                        //setOption(radio[j].getName());
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