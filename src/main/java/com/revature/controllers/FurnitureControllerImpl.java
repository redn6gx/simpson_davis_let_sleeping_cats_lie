package com.revature.controllers;

import com.google.gson.Gson;
import com.revature.exceptions.PersistenceException;
import com.revature.exceptions.ServiceUnavailableException;
import com.revature.models.Cat;
import com.revature.models.Furniture;
import com.revature.services.CatService;

import com.revature.services.FurnitureService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FurnitureControllerImpl implements FurnitureController{
    private final static Logger logger = LogManager.getLogger(FurnitureControllerImpl.class);
    Gson gson = new Gson();
    private final FurnitureService cs;

    public FurnitureControllerImpl(FurnitureService cs){
        this.cs = cs;
    }

    @Override
    public void deleteFurniture(HttpServletRequest request, HttpServletResponse response) {
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

        Furniture furniture = new Furniture();
        furniture.setFurnitureId(id);

        try {
            cs.deleteFurniture(furniture, request.getSession().getId());
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
    public void createFurniture(HttpServletRequest request, HttpServletResponse response) {
        Furniture furniture = null;

        try {
            furniture = gson.fromJson(request.getReader(), Furniture.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            cs.createFurniture(furniture, request.getSession().getId());
            response.setStatus(201);
            response.getWriter().append(gson.toJson(furniture));
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

    @Override
    public Furniture getFurnitureById(HttpServletRequest request, HttpServletResponse response) {
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

        Furniture furniture = null;
        try {
            Optional<Furniture> result = cs.getFurnitureById(id, request.getSession().getId());
            furniture = result.get();
            response.setStatus(200);
            response.getWriter().append(gson.toJson(furniture));
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

        return furniture;
    }

    @Override
    public List<Furniture> getAllFurniture(HttpServletRequest request, HttpServletResponse response) {
        List<Furniture> furniture = new ArrayList<>();

        try {
            furniture = cs.getAllFurniture(request.getSession().getId());
            response.setStatus(200);
            response.getWriter().append(gson.toJson(furniture));
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

        return furniture;
    }

    @Override
    public void updateFurniture(HttpServletRequest request, HttpServletResponse response) {
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

        Furniture furniture = null;
        try {
            furniture = gson.fromJson(request.getReader(), Furniture.class);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        furniture.setFurnitureId(id);

        try {
            cs.updateFurniture(furniture, request.getSession().getId());
            response.setStatus(200);
            response.getWriter().append(gson.toJson(furniture));
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
