package de.thm.icampus.joomdd.ejsl.ui;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class eJSLPerspectiveFactory implements IPerspectiveFactory {

	@Override
	public void createInitialLayout(IPageLayout layout) {
		defineActions(layout);
		defineLayout(layout);
	}

	public void defineActions(IPageLayout layout) {
        // Add "new wizards".
        layout.addNewWizardShortcut("de.thm.icampus.joomdd.ejsl.ui.wizard.EJSLNewProjectWizard");
        layout.addNewWizardShortcut("de.thm.icampus.joomdd.ejsl.ui.wizard.newEJSLFile");
        
        // Add "show views".
        layout.addShowViewShortcut(IPageLayout.ID_PROJECT_EXPLORER);
        layout.addShowViewShortcut(IPageLayout.ID_BOOKMARKS);
        layout.addShowViewShortcut(IPageLayout.ID_OUTLINE);
        layout.addShowViewShortcut(IPageLayout.ID_PROP_SHEET);
        layout.addShowViewShortcut(IPageLayout.ID_TASK_LIST);
	}
	
	public void defineLayout(IPageLayout layout) {
        // Editors are placed for free.
        String editorArea = layout.getEditorArea();

        // Place navigator and outline to left of
        // editor area.
        IFolderLayout left =
                layout.createFolder("left", IPageLayout.LEFT, (float) 0.26, editorArea);
        left.addView(IPageLayout.ID_PROJECT_EXPLORER);
        left.addView(IPageLayout.ID_OUTLINE);
	}
	
}
