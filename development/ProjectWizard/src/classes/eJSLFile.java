package classes;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.IconLoader;
import com.intellij.util.PathUtil;

import java.io.*;

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

        TemplateList tempList = new TemplateList();

        try {
            tempList = TemplateXMLLoader.loadTemplates(PathUtil.getJarPathForClass(getClass()) + "/templates/TemplateList.xml", PathUtil.getJarPathForClass(getClass()) + "/templates/TemplateList.xsd");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Object[] t = tempList.getTemplates();
        Template[] temps = new Template[t.length];
        String[] str = new String[t.length];

        for (int i = 0; i < t.length; i++){
            temps[i] = (Template) t[i];
            str[i] = temps[i].getName();
        }

        String txt = Messages.showInputDialog(project, "Name:", "Create new Class", IconLoader.getIcon("/resources/icons/eJSL.PNG"));
        String temp = Messages.showEditableChooseDialog("On which template you want your class based?", "Create new Class", IconLoader.getIcon("/resources/icons/eJSL.PNG"), str, str[0], null);
        //File file = new File(project.getBasePath() + "/src/" + txt + ".eJSL");

        StringBuilder example = new StringBuilder();

        int k = 0;
        try {
            //file.createNewFile();
           // FileWriter fw = new FileWriter(file);
            for (int i = 0; i < temps.length; i++){if (temps[i].getName().equalsIgnoreCase(temp))k = i;}


           // fw.write(temps[k].getSrc().toString());

            FileWriter fw = new FileWriter(project.getBasePath() + "/src/"+txt+".eJSL");
            FileReader fr = new FileReader(PathUtil.getJarPathForClass(getClass()) + "/templates/" + temps[k].getSrc().toString());
            //FileReader fr = new FileReader(PathUtil.getJarPathForClass(getClass()) + "/resources/eJSLexamples/" + eJSLWizardStep.getOption());
            BufferedReader br = new BufferedReader(fr);
            String buffer = "";
            while ((buffer = br.readLine()) != null) {
                example.append((buffer + "\n"));
            }
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(example.toString());

            br.close();
            bw.close();
            fr.close();
            fw.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}