import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.project.Project;
import com.intellij.util.PathUtil;
import org.jetbrains.annotations.NotNull;

import java.io.*;

/**
 * Created by Leon on 21.01.16.
 */
public class InitalSettings implements ProjectComponent {

    private Project project;

    public InitalSettings(Project project) {
        this.project = project;
    }

    @Override
    public void projectOpened() {

    }


    @Override
    public void projectClosed() {
        File projectfile = new File(project.getBasePath() + "/" + project.getName() + ".iml");


        StringBuilder projectconfig = new StringBuilder();

        if (projectfile.length() < 300) {
            try {
                FileReader fr = new FileReader(PathUtil.getJarPathForClass(getClass()) + "/resources/projectfile.xml");
                BufferedReader br = new BufferedReader(fr);
                String zeile = "";
                while ((zeile = br.readLine()) != null) {
                    projectconfig.append((zeile + "\n"));
                }
                br.close();




                projectfile.createNewFile();
                FileWriter fw = new FileWriter(project.getBasePath() + "/" + project.getName() + ".iml");
                BufferedWriter bw = new BufferedWriter(fw);

                bw.write(projectconfig.toString());
                bw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void initComponent() {

        File src = new File(project.getBasePath() + "/src");
        File src_gen = new File(project.getBasePath() + "/src-gen");
        File model = new File(project.getBasePath() + "/src/model.eJSL");

        StringBuilder example = new StringBuilder();
        try {
            src.mkdir();
            src_gen.mkdir();

            if (!model.exists()) {
                try {

                    FileWriter fw = new FileWriter(project.getBasePath() + "/src/model.eJSL");

                    FileReader fr = new FileReader(PathUtil.getJarPathForClass(getClass()) + "/resources/" + eJSLWizardStep.getOption() + ".eJSL");
                    BufferedReader br = new BufferedReader(fr);
                    String zeile = "";
                    while ((zeile = br.readLine()) != null) {
                        example.append((zeile + "\n"));
                    }
                    br.close();


                    model.createNewFile();
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write(example.toString());
                    bw.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void disposeComponent() {

    }

    @NotNull
    @Override
    public String getComponentName() {
        return "InitalSettings";
    }
}