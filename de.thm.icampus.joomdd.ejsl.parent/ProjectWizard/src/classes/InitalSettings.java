package classes;

import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.project.Project;
import com.intellij.util.PathUtil;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.channels.FileLock;

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
    /*    File settings = new File(project.getBasePath() + "/.idea/settings.txt");

        if(settings.exists()){
            try {
                FileReader fr = new FileReader(project.getBasePath() + "/.idea/settings.txt");
                BufferedReader br = new BufferedReader(fr);
                StringBuilder setBuilder = new StringBuilder();
                String buffer = "";
                while ((buffer = br.readLine()) != null) {
                    if (buffer.equals("ignore = false")) {
                        int n = this.showWarning();
                        if (n == 1) {
                            setBuilder.append("ignore = true\n");
                        } else {
                            setBuilder.append((buffer + "\n"));
                        }
                    }else{
                        setBuilder.append((buffer + "\n"));
                    }
                }
                FileWriter fw = new FileWriter(project.getBasePath() + "/.idea/settings.txt");
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(setBuilder.toString());

                bw.close();
                fw.close();
                fr.close();
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }else{
            int n = this.showWarning();
            StringBuilder setBuilder = new StringBuilder();

            try {
                FileWriter fw = new FileWriter(project.getBasePath() + "/.idea/settings.txt");
                if(n == 1){
                    setBuilder.append(("ignore = true"));
                }else{
                    setBuilder.append(("ignore = false"));
                }

                settings.createNewFile();
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(setBuilder.toString());

                bw.close();
                fw.close();


            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    private int showWarning(){
        //TODO: Add instruction here
        Object[] options = {"OK", "Dont show again"};
        int n = JOptionPane.showOptionDialog(null, "We suggest you disable the Autosave-Function.\n\n" +
                "Under Settings/Apperance & Behavior/System Settings:\n" +
                "Check \"Confirm application exit\"\n" +
                "Uncheck \"Save files on frame deactivation\"\n" +
                "Uncheck \"Save files automatically\"\n\n" +
                "Under Editor/General/Editor Tabs:\n" +
                "Set \"Mark modified tabs with asterisk\""
                , "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, IconLoader.getIcon("/resources/icons/warning.png"), options, options[0]);
        return n;
        */
    }


    @Override
    public void projectClosed() {

    }

    @Override
    public void initComponent() {
        if (eJSLWizardStep.getwizardactive()) {
            eJSLWizardStep.setwizardstatus(false);
            try {
                File projectfile = new File(project.getBasePath() + "/" + project.getName() + ".iml");
                StringBuilder projectconfig = new StringBuilder();

                InputStream fileIS = this.getClass().getClassLoader().getResourceAsStream("settings/projectfile.xml");
                BufferedReader br =  new BufferedReader(new InputStreamReader(fileIS, "UTF-8"));

                String buffer = "";
                while ((buffer = br.readLine()) != null) {
                    projectconfig.append((buffer + "\n"));
                }

                projectfile.createNewFile();
                FileWriter fw = new FileWriter(project.getBasePath() + "/" + project.getName() + ".iml");
                BufferedWriter bw = new BufferedWriter(fw);

                bw.write(projectconfig.toString());

                br.close();
                bw.close();
                fw.close();

                FileLock lock = new RandomAccessFile(project.getBasePath() + "/" + project.getName() + ".iml", "r").getChannel().tryLock(0L, Long.MAX_VALUE,true);
                lock.release();

            } catch (Exception e) {
                e.printStackTrace();
            }

            File src = new File(project.getBasePath() + "/src");
            File src_gen = new File(project.getBasePath() + "/src-gen");
            File model = new File(project.getBasePath() + "/src/Model.eJSL");

            StringBuilder example = new StringBuilder();

            try {
                src.mkdir();
                src_gen.mkdir();
                FileWriter fw = new FileWriter(project.getBasePath() + "/src/Model.eJSL");
                FileWriter fwproperties = new FileWriter(project.getBasePath() + "/src-gen/generator.properties");

                InputStream templateIS = this.getClass().getClassLoader().getResourceAsStream("templates/" + eJSLWizardStep.getOption());
                BufferedReader br =  new BufferedReader(new InputStreamReader(templateIS, "UTF-8"));

                String buffer = "";
                while ((buffer = br.readLine()) != null) {
                    example.append((buffer + "\n"));
                }

                model.createNewFile();
                BufferedWriter bw = new BufferedWriter(fw);

                BufferedWriter bwproperties = new BufferedWriter(fwproperties);


                StringBuilder genproperties = new StringBuilder(eJSL_PHP_Wizard_Step.getGereratorProperties());

                if (eJSLWizardStep.getOutputPath().equals("/src-gen/")){
                    genproperties.append("outputFolder="+project.getBasePath()+eJSLWizardStep.getOutputPath());
                }else{
                    genproperties.append("outputFolder="+eJSLWizardStep.getOutputPath());
                }

                //genproperties.append("\nProject_Path="+project.getBasePath());


                bwproperties.write(genproperties.toString());

                bw.write(example.toString());

                br.close();
                bw.close();
                bwproperties.close();
                fw.close();
                fwproperties.close();


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
