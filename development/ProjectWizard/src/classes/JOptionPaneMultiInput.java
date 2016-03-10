package classes;

import com.intellij.openapi.project.Project;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Leon on 08.03.16.
 */
public class JOptionPaneMultiInput{

    public static String[] showMultiInputDialog(String[] fileOptions, String tagTextbox, String fileExtension, String tagDropdown, String title, Icon icon) {
        JTextField className = new JTextField();
        JComboBox template = new JComboBox(fileOptions);
        template.setSelectedIndex(0);
        String[] returnValue = null;


        JPanel multiInputPanel = new JPanel(new GridLayout(2,3));

        multiInputPanel.add(new JLabel(tagTextbox));
        multiInputPanel.add(className);
        multiInputPanel.add(new JLabel(" "+fileExtension));
        //multiInputPanel.add(Box.createHorizontalStrut(15));
        multiInputPanel.add(new JLabel(tagDropdown));
        multiInputPanel.add(template);


        int result = JOptionPane.showConfirmDialog(null, multiInputPanel, title, JOptionPane.OK_CANCEL_OPTION, 0 ,icon);

        if (result == JOptionPane.OK_OPTION){
            returnValue = new String[2];
            returnValue[0]= className.getText();
            returnValue [1] = fileOptions[template.getSelectedIndex()];
        }

        return returnValue;

    }
}
