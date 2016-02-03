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
                FileReader fr = new FileReader(PathUtil.getJarPathForClass(getClass()) + "/resources/previews/"+files[i].getName());
                BufferedReader br = new BufferedReader(fr);
                String buffer = "";
                while ((buffer = br.readLine()) != null) {
                    preview.append((buffer + "\n"));
                }

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

        return panel ;
    }

    @Override
    public void updateDataModel() {
        wizardstatus = true ;
    }
}
