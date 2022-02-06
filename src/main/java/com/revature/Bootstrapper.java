package com.revature;

import com.revature.annotations.URI;
import com.revature.controllers.CatController;
import com.revature.controllers.CatControllerImpl;
import com.revature.controllers.FurnitureController;
import com.revature.controllers.FurnitureControllerImpl;
import com.revature.models.Cat;
import com.revature.models.Furniture;
import com.revature.services.CatService;
import com.revature.services.CatServiceImpl;
import com.revature.services.FurnitureService;
import com.revature.services.FurnitureServiceImpl;
import com.revature.servlets.CatServlet;
import com.revature.servlets.FurnitureServlet;
import exceptions.CatnapException;
import exceptions.ConnectionFailedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import persistence.SessionFactory;
import util.AnnotationStrategy;
import util.ConnectionPool;
import util.MappingStrategy;
import util.SimpleConnectionPool;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * This class is used to inject the dependencies of all servlets, controllers, and services.
 * It is called when the ServletContext is created and initialization begins.
 */
@WebListener
public class Bootstrapper implements ServletContextListener {

    private final static Logger logger = LogManager.getLogger(Bootstrapper.class);
    private static String schema = "";

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        ServletContext context = sce.getServletContext();

        SessionFactory factory = createSessionFactory();
        initializeCatServlet(context, factory);
        initializeFurnitureServlet(context, factory);

        logger.info(context.getServletRegistration("CatServlet").getMappings());
        logger.info(context.getServletRegistration("FurnitureServlet").getMappings());
    }

    private SessionFactory createSessionFactory() {
        Properties props = new Properties();
        try {
            Class.forName("org.postgresql.Driver");
            props.load(Bootstrapper.class.getClassLoader().getResourceAsStream("connection.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String url = "jdbc:postgresql://" + props.getProperty("endpoint") + "/postgres";
        String username = props.getProperty("username");
        String password = props.getProperty("password");
        ConnectionPool connectionPool = new SimpleConnectionPool(url, username, password, 2);
        MappingStrategy mappingStrategy = new AnnotationStrategy();

        return new SessionFactory(connectionPool, mappingStrategy);
    }

    private void initializeCatServlet(ServletContext context, SessionFactory factory) {
        CatService catService = new CatServiceImpl(factory);
        CatController catController = new CatControllerImpl(catService);
        ServletRegistration.Dynamic registration = context.addServlet("CatServlet", new CatServlet(catController));
        URI paths = CatServlet.class.getAnnotation(URI.class);
        Arrays.stream(paths.urlPatterns()).forEach(registration::addMapping);
    }

    private void initializeFurnitureServlet(ServletContext context, SessionFactory factory) {
        FurnitureService furnitureService = new FurnitureServiceImpl(factory);
        FurnitureController furnitureController = new FurnitureControllerImpl(furnitureService);
        ServletRegistration.Dynamic registration = context.addServlet("FurnitureServlet", new FurnitureServlet(furnitureController));
        URI paths = FurnitureServlet.class.getAnnotation(URI.class);
        Arrays.stream(paths.urlPatterns()).forEach(registration::addMapping);
    }
}
