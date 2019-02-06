package classes;

import com.intellij.ide.util.projectWizard.ModuleBuilder;
import com.intellij.ide.util.projectWizard.ModuleWizardStep;
import com.intellij.ide.util.projectWizard.WizardContext;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.roots.ModifiableRootModel;

/**
 * Created by Leon on 21.01.16.
 */


public class eJSLModuleBuilder extends ModuleBuilder {
    @Override
    public void setupRootModel(ModifiableRootModel modifiableRootModel) throws ConfigurationException {

    }

    @Override
    public ModuleType getModuleType() {
        return eJSLModuleType.getInstance();
    }

    @Override
    public ModuleWizardStep getCustomOptionsStep(final WizardContext context, final Disposable parentDisposable) {
        return new eJSLWizardStep();
    }

}

