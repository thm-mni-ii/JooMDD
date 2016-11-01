package classes;

import com.intellij.ide.util.projectWizard.WebProjectTemplate;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.IconLoader;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.PathUtil;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import classes.eJSL_PHP_Wizard_Step;

import javax.swing.*;
import java.io.*;

/**
 * Created by Max on 09.03.2016.
 */
public class eJSL_PHP_ProjectGenerator extends WebProjectTemplate {

    public Icon getIcon()
    {
        return IconLoader.getIcon("/resources/icons/eJSL.PNG");
    }

    public Icon getLogo() {
        return IconLoader.getIcon("/resources/icons/eJSL.PNG");
    }

    @Nls
    @NotNull
    @Override
    public String getName() {
        return "eJSL";
    }

    @Override
    public String getDescription() {
        return "Description eJSL";
    }

    @Override
    public void generateProject(@NotNull Project project, @NotNull VirtualFile virtualFile, @NotNull Object o, @NotNull Module module) {

        try {
            File projectFile = new File(project.getBasePath() + "/.idea/" + project.getName() + ".iml");
            StringBuilder projectConfig = new StringBuilder();
            FileReader fr = new FileReader(PathUtil.getJarPathForClass(getClass()) + "/resources/settings/php_projectfile.xml");
            BufferedReader br = new BufferedReader(fr);
            String buffer = "";
            while ((buffer = br.readLine()) != null) {
                projectConfig.append((buffer + "\n"));
            }


            projectFile.createNewFile();
            FileWriter fw = new FileWriter(project.getBasePath() + "/.idea/" + project.getName() + ".iml");
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(projectConfig.toString());

            br.close();
            bw.close();
            fw.close();
            fr.close();

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
            FileWriter fwproperties = new FileWriter(project.getBasePath() + "/src/generator.properties");

            FileReader fr = new FileReader(PathUtil.getJarPathForClass(getClass()) + "/templates/"+eJSL_PHP_Wizard_Step.getOption() );
            BufferedReader br = new BufferedReader(fr);
            String buffer = "";
            while ((buffer = br.readLine()) != null) {
                example.append((buffer + "\n"));
            }

            model.createNewFile();
            BufferedWriter bw = new BufferedWriter(fw);
            BufferedWriter bwproperties = new BufferedWriter(fwproperties);
            bwproperties.write(eJSL_PHP_Wizard_Step.getGereratorProperties());
            bw.write(example.toString());


            br.close();
            bw.close();
            bwproperties.close();
            fr.close();
            fw.close();
            fwproperties.close();

            project.getBaseDir().refresh(false,true);

            FileEditorManager fileEditorManager = FileEditorManager.getInstance(project);
            VirtualFile vf = LocalFileSystem.getInstance().findFileByPath(project.getBasePath()+"/src/Model.eJSL");
            fileEditorManager.openFile(vf, true, true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @NotNull
    @Override
    public GeneratorPeer createPeer() {
        return new eJSL_PHP_Wizard_Step();
    }


}
