import com.intellij.ide.util.projectWizard.ModuleWizardStep;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Max on 28.01.2016.
 */
public class eJSLWizardStep extends ModuleWizardStep {

    private static int option = 1;

    public static int getOption(){
        return option;
    }

    @Override
    public JComponent getComponent() {
        JPanel panel = new JPanel(new GridBagLayout());

        ButtonGroup radioGroup = new ButtonGroup();

        JRadioButton radio1 = new JRadioButton("Conference eJSL example");
        JRadioButton radio2 = new JRadioButton("model eJSL example");
        JRadioButton radio3 = new JRadioButton("Weblinks eJSL example");
        radioGroup.add(radio1);
        radioGroup.add(radio2);
        radioGroup.add(radio3);

        panel.add(radio1);
        panel.add(radio2);
        panel.add(radio3);

        radio1.setSelected(true);

        JLabel lbl1 = new JLabel("Preview Conference");
        panel.add(lbl1);
        JLabel lbl2 = new JLabel("Preview model");
        panel.add(lbl2);
        JLabel lbl3 = new JLabel("Preview Weblinks");
        panel.add(lbl3);

        lbl1.setVisible(true);
        lbl2.setVisible(false);
        lbl3.setVisible(false);


        radio1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(radio1.isSelected()){
                    option = 1;
                    lbl1.setVisible(true);
                    lbl2.setVisible(false);
                    lbl3.setVisible(false);

                }
            }
        });

        radio2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(radio2.isSelected()){
                    option = 2;
                    lbl2.setVisible(true);
                    lbl1.setVisible(false);
                    lbl3.setVisible(false);
                }
            }
        });

        radio3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(radio3.isSelected()){
                    option = 3;
                    lbl3.setVisible(true);
                    lbl1.setVisible(false);
                    lbl2.setVisible(false);
                }
            }
        });
        return panel ;
    }

    @Override
    public void updateDataModel() {

    }
}
