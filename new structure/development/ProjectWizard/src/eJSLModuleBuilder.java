import com.intellij.ide.util.projectWizard.ModuleBuilder;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.roots.ModifiableRootModel;
import com.intellij.ide.util.projectWizard.ModuleWizardStep;
import com.intellij.ide.util.projectWizard.WizardContext;
import com.intellij.openapi.roots.ui.configuration.ModulesProvider;
import com.sun.istack.internal.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Leon on 21.01.16.
 */
public class eJSLModuleBuilder extends ModuleBuilder {
    @Override
    public void setupRootModel(ModifiableRootModel modifiableRootModel) throws ConfigurationException {

    }

    @Override
    public ModuleType getModuleType() {
        return eJSLModuleType.getInstance();
    }


    @Override
    public ModuleWizardStep[] createWizardSteps(@NotNull WizardContext wizardContext, @NotNull ModulesProvider modulesProvider) {
        return new ModuleWizardStep[]{new ModuleWizardStep() {


            @Override
            public JComponent getComponent() {

                JPanel panel = new JPanel(new GridBagLayout());

                ButtonGroup radioGroup = new ButtonGroup();

                JRadioButton radio1 = new JRadioButton("easy eJSL example");
                JRadioButton radio2 = new JRadioButton("medium eJSL example");
                JRadioButton radio3 = new JRadioButton("other eJSL example");
                radioGroup.add(radio1);
                radioGroup.add(radio2);
                radioGroup.add(radio3);

                panel.add(radio1);
                panel.add(radio2);
                panel.add(radio3);

                radio1.setSelected(true);

                JLabel lbl1 = new JLabel("Preview radio1");
                panel.add(lbl1);
                JLabel lbl2 = new JLabel("Preview radio2");
                panel.add(lbl2);
                JLabel lbl3 = new JLabel("Preview radio3");
                panel.add(lbl3);

                lbl1.setVisible(true);
                lbl2.setVisible(false);
                lbl3.setVisible(false);


                radio1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(radio1.isSelected()){
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
        }};
    }
}
