package com.revature.controllers;

import com.google.gson.Gson;
import com.revature.exceptions.PersistenceException;
import com.revature.models.Cat;
import com.revature.services.CatService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.AnnotationStrategy;

import javax.naming.ServiceUnavailableException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CatControllerImpl implements CatController{
    private final static Logger logger = LogManager.getLogger(CatControllerImpl.class);
    public CatControllerImpl(Object o){}

    Gson gson = new Gson();
    CatService cs;

    public CatControllerImpl(CatService cs){
        this.cs = cs;
    }

    @Override
    public void deleteCat(HttpServletRequest request, HttpServletResponse response) {
        String input = request.getAttribute("id").toString();
        int id=0;
        if(input.matches("[0-9]+")) {
            id = Integer.parseInt(input);
        } else {
            try{
                response.sendError(400, "ID is not a number");
            }catch(IOException e){
                logger.error(e.getMessage());
            }
            return;
        }

        Cat cat = null;
        try{
            cat = gson.fromJson(request.getReader(), Cat.class);
//            cat = gson.fromJson(request.getReader().lines().collect(Collectors.joining()), Cat.class);
        }catch(IOException e){
            logger.error(e.getMessage());
        }

        try {
            cs.deleteCat(cat, request.getSession().getId());
        } catch (PersistenceException e) {
            try {
                response.sendError(400);
            } catch (IOException ex) {
                logger.error(ex.getMessage());
            }
            logger.error(e.getMessage());
        } catch (ServiceUnavailableException e) {
            try {
                response.sendError(503);
            } catch (IOException ex) {
                logger.error(ex.getMessage());
            }
            logger.error(e.getMessage());
        }
    }

    @Override
    public void createCat(HttpServletRequest request, HttpServletResponse response) {

        boolean isMany = false;
        try {
            char c = request.getReader().toString().charAt(0);
            if(c == '['){
                isMany = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(!isMany) {
            Cat cat = null;
            try {
                cat = gson.fromJson(request.getReader(), Cat.class);
            } catch (IOException e) {
                logger.error(e.getMessage());
            }

            //if multiple json object call cs.createMany, else call cs.createCat
            try {
                cs.createCat(cat, request.getSession().getId());
            } catch (PersistenceException e) {
                try {
                    response.sendError(400);
                } catch (IOException ex) {
                    logger.error(ex.getMessage());
                }
                logger.error(e.getMessage());
            } catch (ServiceUnavailableException e) {
                try {
                    response.sendError(503);
                } catch (IOException ex) {
                    logger.error(ex.getMessage());
                }
                logger.error(e.getMessage());
            }
        }else {
            //createMany
            List<Cat> cats = new ArrayList<>();

            try {
                cs.createMany(cats, request.getSession().getId());
            } catch (PersistenceException e) {
                try {
                    response.sendError(400);
                } catch (IOException ex) {
                    logger.error(ex.getMessage());
                }
                logger.error(e.getMessage());
            } catch (ServiceUnavailableException e) {
                try {
                    response.sendError(503);
                } catch (IOException ex) {
                    logger.error(ex.getMessage());
                }
                logger.error(e.getMessage());
            }
        }
    }

    @Override
    public Cat getCatById(HttpServletRequest request, HttpServletResponse response) {
        String input = request.getAttribute("id").toString();
        int id=0;
        if(input.matches("[0-9]+")) {
            id = Integer.parseInt(input);
        } else {
            try{
                response.sendError(400, "ID is not a number");
            }catch(IOException e){
                logger.error(e.getMessage());
            }
            return null;
        }

        Cat cat = null;
        try{
            cat = gson.fromJson(request.getReader(), Cat.class);
        }catch(IOException e){
            logger.error(e.getMessage());
        }

        try {
            cs.getCatById(id, request.getSession().getId());
        } catch (PersistenceException e) {
            try {
                response.sendError(400);
            } catch (IOException ex) {
                logger.error(ex.getMessage());
            }
            logger.error(e.getMessage());
        } catch (ServiceUnavailableException e) {
            try {
                response.sendError(503);
            } catch (IOException ex) {
                logger.error(ex.getMessage());
            }
            logger.error(e.getMessage());
        }

        return cat;
    }

    @Override
    public List<Cat> getAllCats(HttpServletRequest request, HttpServletResponse response) {
        List<Cat> cats = new ArrayList<>();

        try {
            cats = cs.getAllCats(request.getSession().getId());
        } catch (PersistenceException e) {
            try {
                response.sendError(400);
            } catch (IOException ex) {
                logger.error(ex.getMessage());
            }
            logger.error(e.getMessage());
        } catch (ServiceUnavailableException e) {
            try {
                response.sendError(503);
            } catch (IOException ex) {
                logger.error(ex.getMessage());
            }
            logger.error(e.getMessage());
        }

        return cats;
    }

    @Override
    public void updateCat(HttpServletRequest request, HttpServletResponse response) {
        Cat cat = null;

        try {
            cat = gson.fromJson(request.getReader(), Cat.class);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        try {
            cs.updateCat(cat, request.getSession().getId());
        } catch (PersistenceException e) {
            try {
                response.sendError(400);
            } catch (IOException ex) {
                logger.error(ex.getMessage());
            }
            logger.error(e.getMessage());
        } catch (ServiceUnavailableException e) {
            try {
                response.sendError(503);
            } catch (IOException ex) {
                logger.error(ex.getMessage());
            }
            logger.error(e.getMessage());
        }
    }
}