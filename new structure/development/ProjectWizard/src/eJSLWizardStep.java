import com.intellij.ide.util.projectWizard.ModuleWizardStep;
import com.intellij.util.PathUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

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
        JPanel panel = new JPanel(new GridBagLayout());

        ButtonGroup radioGroup = new ButtonGroup();

        File resource_dir = new File(PathUtil.getJarPathForClass(getClass()) + "/resources/");
        File files[] = resource_dir.listFiles();

        JRadioButton radio[] = new JRadioButton[files.length];
        JLabel lblpreview[] = new JLabel[files.length];

        JLabel lbl1 = new JLabel("Load example:");
        panel.add(lbl1);

        int i;
        for (i=0;files.length>i;i++) {
            lblpreview[i] = new JLabel(files[i].getName());
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
            panel.add(radio[i]);
        }
        for (i=0;files.length>i;i++) {
            panel.add(lblpreview[i]);
        }

        return panel ;
    }

    @Override
    public void updateDataModel() {
        wizardstatus = true ;
    }
}
