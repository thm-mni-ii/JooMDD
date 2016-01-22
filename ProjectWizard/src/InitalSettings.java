import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

import java.io.File;

/**
 * Created by Leon on 21.01.16.
 */
public class InitalSettings implements ProjectComponent{

    private Project project;

    public InitalSettings(Project project){
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

        File src = new File(project.getBasePath() + "/src");
        File src_gen = new File(project.getBasePath() + "/src-gen");
        File model = new File(project.getBasePath() + "/src/model.ejsl");

        try{
            src.mkdir();
            src_gen.mkdir();
            model.createNewFile();
        }catch (Exception e){

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
