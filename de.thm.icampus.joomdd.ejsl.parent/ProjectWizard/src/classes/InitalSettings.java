package classes;

import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.PathUtil;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.channels.FileLock;
import java.util.HashMap;
import java.util.Properties;

/**
 * Created by Leon on 21.01.16.
 * Modified by Dennis on 16.10.17
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
    }

    @Override
    public void initComponent() {
    	String imlPath = project.getBasePath() + "/" + project.getName() + ".iml";
    	
        if (eJSLWizardStep.getwizardactive()) {
            eJSLWizardStep.setwizardstatus(false);
            try {
                File projectfile = new File(imlPath);
                StringBuilder projectconfig = new StringBuilder();

                InputStream fileIS = this.getClass().getClassLoader().getResourceAsStream("settings/projectfile.xml");
                BufferedReader br =  new BufferedReader(new InputStreamReader(fileIS, "UTF-8"));

                String buffer = "";
                while ((buffer = br.readLine()) != null) {
                    projectconfig.append((buffer + "\n"));
                }

                projectfile.createNewFile();
                FileWriter fw = new FileWriter(imlPath);
                BufferedWriter bw = new BufferedWriter(fw);

                bw.write(projectconfig.toString());

                br.close();
                bw.close();
                fw.close();
                
                FileLock lock = new RandomAccessFile(imlPath, "r").getChannel().tryLock(0L, Long.MAX_VALUE,true);
                lock.release();

            } catch (Exception e) {
                e.printStackTrace();
            }

            String projectBasePath = project.getBasePath();
            File src = new File(projectBasePath + "/src");
            File src_gen = new File(projectBasePath + "/src-gen");
            File model = new File(projectBasePath + "/src/Model.eJSL");

            StringBuilder example = new StringBuilder();

            try {
                src.mkdir();
                src_gen.mkdir();
                FileWriter fw = new FileWriter(projectBasePath + "/src/Model.eJSL");
                String option = eJSLWizardStep.getOption();

                InputStream templateIS = this.getClass().getClassLoader().getResourceAsStream("templates/" + option);
                BufferedReader br =  new BufferedReader(new InputStreamReader(templateIS, "UTF-8"));

                String buffer = "";
                while ((buffer = br.readLine()) != null) {
                    example.append((buffer + "\n"));
                }

                model.createNewFile();
                BufferedWriter bw = new BufferedWriter(fw);

                Properties config = new Properties();
                HashMap<String, String> genproperties = eJSL_PHP_Wizard_Step.getGereratorProperties();
                config.putAll(genproperties);
                config.setProperty("outputFolder", src_gen.getPath());
                FileWriter configWriter = new FileWriter(projectBasePath + "/generator.properties");
                config.store(configWriter, "Generator configuration");

                bw.write(example.toString());

                br.close();
                bw.close();
                fw.close();
                configWriter.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void disposeComponent() {

    }

    @NotNull
    @Override
    public String getComponentName() {
        return "classes.InitalSettings";
    }
}

