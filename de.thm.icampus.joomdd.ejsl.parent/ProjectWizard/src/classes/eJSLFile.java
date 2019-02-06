package classes;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.IconLoader;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.PathUtil;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;

/**
 * Created by Leon on 27.01.16.
 */
public class eJSLFile extends AnAction{

    private static final Logger logger = Logger.getLogger(eJSLFile.class);

    public eJSLFile(){
        super("eJSLFile","Creates a new eJSL File", IconLoader.getIcon("/icons/eJSL.PNG"));
    }


    @Override
    public void actionPerformed(AnActionEvent event) {
        Project project = event.getData(PlatformDataKeys.PROJECT);

        TemplateList tempList = new TemplateList();

        try {
            tempList = TemplateXMLLoader.loadTemplates( "templates/TemplateList.xml", "templates/TemplateList.xsd");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        logger.error("eJSLFile");
        logger.error(tempList);

        Object[] t = tempList.getTemplates();
        Template[] temps = new Template[t.length];
        String[] str = new String[t.length];
        str[0] = "";

        for (int i = 0; i < t.length; i++){
            temps[i] = (Template) t[i];
            str[i] = temps[i].getName();
        }

        String[] result = JOptionPaneMultiInput.showMultiInputDialog(str, "Name:", ".eJSL", "Template:", "Create new eJSL-File", IconLoader.getIcon("/icons/eJSL.PNG"));
        if (result != null){
            StringBuilder example = new StringBuilder();

                int k = 0;
                try {
                    File src = new File(project.getBasePath() + "/src");
                    src.mkdir();
                    for (int i = 0; i < temps.length; i++){if (temps[i].getName().equalsIgnoreCase(result[1]))k = i;}
                    FileWriter fw = new FileWriter(src.getPath() + "/" + result[0] +".eJSL");

                    InputStream fileIS = this.getClass().getClassLoader().getResourceAsStream("templates/" + temps[k].getSrc());
                    BufferedReader br =  new BufferedReader(new InputStreamReader(fileIS, "UTF-8"));

                    String buffer = "";

                    while ((buffer = br.readLine()) != null) {
                        example.append((buffer + "\n"));
                    }
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write(example.toString());

                    br.close();
                    bw.close();
                    fw.close();

                    URI url = null;

                    try {
                        url = new URI("file://" + project.getBasePath() + "/src/" + result[0] + ".eJSL");
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }

                    FileEditorManager fileEditorManager = FileEditorManager.getInstance(project);

                    VirtualFile vf = LocalFileSystem.getInstance().refreshAndFindFileByPath(project.getBasePath() + "/src/" + result[0] + ".eJSL");
                    fileEditorManager.openFile(vf, true, true);

                }catch (IOException e){
                    e.printStackTrace();
            }
        }
    }
}