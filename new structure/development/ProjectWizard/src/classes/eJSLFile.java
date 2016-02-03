package classes;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.IconLoader;

import java.io.File;
import java.io.IOException;

/**
 * Created by Leon on 27.01.16.
 */
public class eJSLFile extends AnAction{

    public eJSLFile(){
        super("eJSLFile","Creates a new eJSL File", IconLoader.getIcon("/resources/icons/eJSL.PNG"));
    }


    @Override
    public void actionPerformed(AnActionEvent event) {
        Project project = event.getData(PlatformDataKeys.PROJECT);
        String txt = Messages.showInputDialog(project, "Name:", "Create new Class", null);
        File file = new File(project.getBasePath() + "/src/" + txt + ".eJSL");
        try {
            file.createNewFile();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}