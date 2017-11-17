package de.thm.icampus.joomdd.ejsl.web.listener

import javax.servlet.ServletContextListener
import javax.servlet.ServletContextEvent
import javax.servlet.ServletContext
import de.thm.icampus.joomdd.ejsl.util.Config

class ConfigurationListener implements ServletContextListener {
    override void contextInitialized(ServletContextEvent sce) {
        var ServletContext context = sce.getServletContext();
        var Config config = Config.getInstance(context.getRealPath("/") + "/conf/config.properties");
        Config.instance.properties.setProperty("serverPath", context.getRealPath("/"));
    }

    override void contextDestroyed(ServletContextEvent sce) {}
}