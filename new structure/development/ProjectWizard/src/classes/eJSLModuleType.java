package classes;

import com.intellij.ide.util.projectWizard.ModuleWizardStep;
import com.intellij.ide.util.projectWizard.WizardContext;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.roots.ui.configuration.ModulesProvider;
import com.intellij.util.PathUtil;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * Created by Leon on 21.01.16.
 */
public class eJSLModuleType extends ModuleType<eJSLModuleBuilder> {

    private static final eJSLModuleType INSTANCE = new eJSLModuleType();

    public eJSLModuleType() {
        super("classes.eJSLModuleType");
    }

    public static eJSLModuleType getInstance() {
        return INSTANCE;
    }

    @NotNull
    @Override
    public eJSLModuleBuilder createModuleBuilder() {
        return new eJSLModuleBuilder();
    }

    @NotNull
    @Override
    public String getName() {
        return "eJSL";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "MDD of CMS Repository for eJSL and cJSL and the according generators";
    }

    @Override
    public Icon getBigIcon() {
        ImageIcon icon = new ImageIcon(PathUtil.getJarPathForClass(getClass())+ "/resources/icons/eJSL.png", "hi");
        return icon;
        //AllIcons.General.Information;
    }

    @Override
    public Icon getNodeIcon(@Deprecated boolean b) {
        ImageIcon icon =  new ImageIcon(PathUtil.getJarPathForClass(getClass())+ "/resources/icons/eJSL.png", "hi");
        return icon;
         // AllIcons.General.Information;
    }


    @NotNull
    @Override
    public ModuleWizardStep[] createWizardSteps(@NotNull WizardContext wizardContext, @NotNull eJSLModuleBuilder moduleBuilder, @NotNull ModulesProvider modulesProvider) {


        return super.createWizardSteps(wizardContext, moduleBuilder, modulesProvider);


    }

}
