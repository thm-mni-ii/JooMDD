package classes;

import com.intellij.ide.util.projectWizard.WizardContext;
import com.intellij.platform.ProjectTemplate;
import com.intellij.platform.ProjectTemplatesFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by Max on 09.03.2016.
 */
public class eJSL_PHP_ProjectTemplatesFactory extends ProjectTemplatesFactory {
    @NotNull
    @Override
    public String[] getGroups() {
        return new String[0];
    }

    @NotNull
    @Override
    public ProjectTemplate[] createTemplates(@Nullable String s, WizardContext wizardContext) {
        return new ProjectTemplate[]{
            new eJSL_PHP_ProjectGenerator()
        };
    }
}
