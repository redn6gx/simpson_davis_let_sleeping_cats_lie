package com.revature;

import com.revature.annotations.URI;
import com.revature.controllers.CatController;
import com.revature.controllers.CatControllerImpl;
import com.revature.controllers.FurnitureController;
import com.revature.controllers.FurnitureControllerImpl;
import com.revature.models.Furniture;
import com.revature.servlets.CatServlet;
import com.revature.servlets.FurnitureServlet;
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
        initializeCatServlet(context);
        initializeFurnitureServlet(context);

        logger.info(context.getServletRegistration("CatServlet").getMappings());
        logger.info(context.getServletRegistration("FurnitureServlet").getMappings());
    }

    private void initializeCatServlet(ServletContext context) {
        CatController catController = new CatControllerImpl(null);
        ServletRegistration.Dynamic registration = context.addServlet("CatServlet", new CatServlet(catController));
        URI paths = CatServlet.class.getAnnotation(URI.class);
        Arrays.stream(paths.urlPatterns()).forEach(registration::addMapping);
    }

    private void initializeFurnitureServlet(ServletContext context) {
        FurnitureController furnitureController = new FurnitureControllerImpl(null);
        ServletRegistration.Dynamic registration = context.addServlet("FurnitureServlet", new FurnitureServlet(furnitureController));
        URI paths = FurnitureServlet.class.getAnnotation(URI.class);
        Arrays.stream(paths.urlPatterns()).forEach(registration::addMapping);
    }
}
