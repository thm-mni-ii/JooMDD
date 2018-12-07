package de.thm.icampus.joomdd.ejsl.web.listener

import javax.servlet.ServletContextListener
import javax.servlet.ServletContextEvent
import javax.servlet.ServletContext
import de.thm.icampus.joomdd.ejsl.util.Config

/**
 * @author Wolf Rost
 */
class ConfigurationListener implements ServletContextListener {
    override void contextInitialized(ServletContextEvent sce) {
        var ServletContext context = sce.getServletContext();
    }

    override void contextDestroyed(ServletContextEvent sce) {}
}