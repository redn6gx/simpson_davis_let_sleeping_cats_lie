package com.revature;

import com.revature.annotations.URI;
import com.revature.controllers.CatController;
import com.revature.controllers.CatControllerImpl;
import com.revature.servlets.CatServlet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;
import java.util.Arrays;

/**
 * This class is used to inject the dependencies of all servlets, controllers, and services.
 * It is called when the ServletContext is created and initialization begins.
 */
@WebListener
public class Bootstrapper implements ServletContextListener {

    private final static Logger logger = LogManager.getLogger(Bootstrapper.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        ServletContext context = sce.getServletContext();

        CatController catController = new CatControllerImpl(null);
        ServletRegistration.Dynamic registration = context.addServlet("CatServlet", new CatServlet(catController));
        URI paths = CatServlet.class.getAnnotation(URI.class);
        Arrays.stream(paths.urlPatterns()).forEach(registration::addMapping);

        logger.info(context.getServletRegistration("CatServlet").getMappings());
    }
}
