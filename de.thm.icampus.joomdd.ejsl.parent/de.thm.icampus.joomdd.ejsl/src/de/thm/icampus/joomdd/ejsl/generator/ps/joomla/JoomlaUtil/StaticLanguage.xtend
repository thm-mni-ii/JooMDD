package de.thm.icampus.joomdd.ejsl.generator.ps.joomla.JoomlaUtil

import org.eclipse.xtend.lib.annotations.Accessors

/**
 * This static class contains all constant language statements
 * that will be used during the generation process.
 */
class StaticLanguage {
    @Accessors(PUBLIC_GETTER)
    static StaticLanguageValue HOME = new StaticLanguageValue("HOME", "Home")
    
    @Accessors(PUBLIC_GETTER)
    static StaticLanguageValue STATE = new StaticLanguageValue("STATE", "state")
    
    @Accessors(PUBLIC_GETTER)
    static StaticLanguageValue STATE_DESC = new StaticLanguageValue("STATE_DESC", "State Description")
    
    @Accessors(PUBLIC_GETTER)
    static StaticLanguageValue CHECKED_OUT = new StaticLanguageValue("CHECKED_OUT", "Checked out")
    
    @Accessors(PUBLIC_GETTER)
    static StaticLanguageValue CHECKED_OUT_TIME = new StaticLanguageValue("CHECKED_OUT_TIME", "Checked out Time")
    
    @Accessors(PUBLIC_GETTER)
    static StaticLanguageValue ORDERING_LABEL = new StaticLanguageValue("ORDERING_LABEL", "Ordering")
    
    @Accessors(PUBLIC_GETTER)
    static StaticLanguageValue ORDERING_DESC = new StaticLanguageValue("ORDERING_DESC", "Ordering description")
    
    @Accessors(PUBLIC_GETTER)
    static StaticLanguageValue ITEM_SAVED_SUCCESSFULLY = new StaticLanguageValue("ITEM_SAVED_SUCCESSFULLY", "The data are successfully saved.")
    
    @Accessors(PUBLIC_GETTER)
    static StaticLanguageValue ITEM_DELETED_SUCCESSFULLY = new StaticLanguageValue("ITEM_DELETED_SUCCESSFULLY", "The data are successfully deleted.")
    
    @Accessors(PUBLIC_GETTER)
    static StaticLanguageValue DELETE_MESSAGE = new StaticLanguageValue("DELETE_MESSAGE", "Do you want to delete the data?")
    
    @Accessors(PUBLIC_GETTER)
    static StaticLanguageValue ITEMS_PUBLISHED = new StaticLanguageValue("ITEMS_PUBLISHED", "The data are successfully published.")
    
    @Accessors(PUBLIC_GETTER)
    static StaticLanguageValue TEMPLATE_LAYOUT_DESC = new StaticLanguageValue("TEMPLATE_LAYOUT_DESC", "Choice a layout for the indexpage.")
    
    @Accessors(PUBLIC_GETTER)
    static StaticLanguageValue DIRECTION = new StaticLanguageValue("DIRECTION", "Direction")
    
    @Accessors(PUBLIC_GETTER)
    static StaticLanguageValue DIRECTION_DESC = new StaticLanguageValue("DIRECTION_DESC", "Direction")
    
    @Accessors(PUBLIC_GETTER)
    static StaticLanguageValue DIRECTION_ASC = new StaticLanguageValue("DIRECTION_ASC", "ASC")
    
    @Accessors(PUBLIC_GETTER)
    static StaticLanguageValue DIRECTION_DESCENDING = new StaticLanguageValue("DIRECTION_DESCENDING", "DESC")
    
    @Accessors(PUBLIC_GETTER)
    static StaticLanguageValue START_LABEL = new StaticLanguageValue("START_LABEL", "Start")
    
    @Accessors(PUBLIC_GETTER)
    static StaticLanguageValue START_DESC = new StaticLanguageValue("START_DESC", "Begin index for data")
    
    @Accessors(PUBLIC_GETTER)
    static StaticLanguageValue LIMIT_LABEL = new StaticLanguageValue("LIMIT_LABEL", "Limit")
    
    @Accessors(PUBLIC_GETTER)
    static StaticLanguageValue LIMIT_DESC = new StaticLanguageValue("LIMIT_DESC", "The number of Dataitem in the view")
    
    @Accessors(PUBLIC_GETTER)
    static StaticLanguageValue SEARCH_LABEL = new StaticLanguageValue("SEARCH_LABEL", "Search")
    
    @Accessors(PUBLIC_GETTER)
    static StaticLanguageValue SEARCH_DESC = new StaticLanguageValue("SEARCH_DESC", "Search data")
    
    @Accessors(PUBLIC_GETTER)
    static StaticLanguageValue TEMPLATE_LAYOUT = new StaticLanguageValue("TEMPLATE_LAYOUT", "Template Layout")
    
    @Accessors(PUBLIC_GETTER)
    static StaticLanguageValue LAYOUT_LIST = new StaticLanguageValue("LAYOUT_LIST", "List Layout")
    
    @Accessors(PUBLIC_GETTER)
    static StaticLanguageValue LAYOUT_TABLE = new StaticLanguageValue("LAYOUT_TABLE", "Table layout")
    
    @Accessors(PUBLIC_GETTER)
    static StaticLanguageValue PARAMS_LOCAL_LABEL = new StaticLanguageValue("PARAMS_LOCAL_LABEL", "Local Parameter")
    
    @Accessors(PUBLIC_GETTER)
    static StaticLanguageValue PARAMS_GLOBAL_LABEL = new StaticLanguageValue("PARAMS_GLOBAL_LABEL", "Global Parameter")
    
    @Accessors(PUBLIC_GETTER)
    static StaticLanguageValue FILTER_LABEL = new StaticLanguageValue("FILTER_LABEL", "Filter")
    
    @Accessors(PUBLIC_GETTER)
    static StaticLanguageValue ACTIONS = new StaticLanguageValue("ACTIONS", "Actions")
    
    @Accessors(PUBLIC_GETTER)
    static StaticLanguageValue ITEM_NOT_LOADED = new StaticLanguageValue("ITEM_NOT_LOADED", "Items could not be loaded.")
    
    @Accessors(PUBLIC_GETTER)
    static StaticLanguageValue UPDATE_TEXT = new StaticLanguageValue("UPDATE_TEXT", "Successfully updated.")
    
    @Accessors(PUBLIC_GETTER)
    static StaticLanguageValue UNINSTALL_TEXT = new StaticLanguageValue("UNINSTALL_TEXT", "Successfully uninstalled.")
    
    @Accessors(PUBLIC_GETTER)
    static StaticLanguageValue INSTALL_TEXT = new StaticLanguageValue("INSTALL_TEXT", "Successfully installed.")
    
    /**
     * This method adds "Description for" to the given attribute.
     * 
     * @param String attribute
     * 
     * @return String
     */
    def static String getCommonDescriptionFor(String attribute) {
        return '''Description for «attribute»'''
    }
}