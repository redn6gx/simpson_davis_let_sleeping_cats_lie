package com.revature.controllers;

import com.google.gson.Gson;
import com.revature.exceptions.PersistenceException;
import com.revature.exceptions.ServiceUnavailableException;
import com.revature.models.Cat;
import com.revature.services.CatService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CatControllerImpl implements CatController{
    private final static Logger logger = LogManager.getLogger(CatControllerImpl.class);
    Gson gson = new Gson();
    private final CatService cs;

    public CatControllerImpl(CatService cs){
        this.cs = cs;
    }

    @Override
    public void deleteCat(HttpServletRequest request, HttpServletResponse response) {
        String input = request.getAttribute("id").toString();
        int id;
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

        Cat cat = new Cat();
        cat.setCatId(id);

        try {
            cs.deleteCat(cat, request.getSession().getId());
            response.setStatus(204);
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
        String json = null;

        StringBuilder jsonBuilder = new StringBuilder();
        try {
            String line;
            while((line = request.getReader().readLine()) != null) {
                jsonBuilder.append(line);
            }
        } catch (IOException e) {
            try {
                response.sendError(503);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }

        json = jsonBuilder.toString();

        try {
            // try for one
            Cat cat = gson.fromJson(json, Cat.class);

            cs.createCat(cat, request.getSession().getId());
            response.setStatus(201);
        } catch (com.google.gson.JsonSyntaxException e) {
            // have multiple
            try {
                Cat[] cats = gson.fromJson(json, Cat[].class);
                cs.createManyCats(Arrays.asList(cats), request.getSession().getId());
            } catch (PersistenceException | ServiceUnavailableException ex) {
                try {
                    response.sendError(503);
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
        } catch (PersistenceException | ServiceUnavailableException e) {
            try {
                response.sendError(503);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public Cat getCatById(HttpServletRequest request, HttpServletResponse response) {
        String input = request.getAttribute("id").toString();
        int id;
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
        try {
            Optional<Cat> result = cs.getCatById(id, request.getSession().getId());
            cat = result.get();
            response.setStatus(200);
            response.getWriter().append(gson.toJson(cat));
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
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        return cat;
    }

    @Override
    public List<Cat> getAllCats(HttpServletRequest request, HttpServletResponse response) {
        List<Cat> cats = new ArrayList<>();

        try {
            cats = cs.getAllCats(request.getSession().getId());
            response.setStatus(200);
            response.getWriter().append(gson.toJson(cats));
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
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        return cats;
    }

    @Override
    public void updateCat(HttpServletRequest request, HttpServletResponse response) {
        String input = request.getAttribute("id").toString();
        int id;
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
        try {
            cat = gson.fromJson(request.getReader(), Cat.class);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        cat.setCatId(id);

        try {
            cs.updateCat(cat, request.getSession().getId());
            response.setStatus(200);
            response.getWriter().append(gson.toJson(cat));
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
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
