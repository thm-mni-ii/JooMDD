package classes;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.IconLoader;
import com.intellij.openapi.vfs.VirtualFile;
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
        str[0] = "";

        for (int i = 0; i < t.length; i++){
            temps[i] = (Template) t[i];
            str[i] = temps[i].getName();
        }

        String[] result = JOptionPaneMultiInput.showMultiInputDialog(str, "Name:", ".eJSL", "Template:", "Create new eJSL-File", IconLoader.getIcon("/resources/icons/eJSL.PNG"));
        if (result != null){
            StringBuilder example = new StringBuilder();

                int k = 0;
                try {
                    File src = new File(project.getBasePath() + "/src");
                    src.mkdir();
                    for (int i = 0; i < temps.length; i++){if (temps[i].getName().equalsIgnoreCase(result[1]))k = i;}
                    FileWriter fw = new FileWriter(src.getPath() + "/" + result[0] +".eJSL");
                    FileReader fr = new FileReader(PathUtil.getJarPathForClass(getClass()) + "/templates/" + temps[k].getSrc().toString());
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
        project.getBaseDir().refresh(false,true);
    }
}