package com.revature.controllers;

import com.revature.models.Furniture;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class FurnitureControllerImpl implements FurnitureController{

    public FurnitureControllerImpl(Object o){}

    @Override
    public void deleteFurniture(HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    public void createFurniture(HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    public Furniture getFurnitureById(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    @Override
    public List<Furniture> getAllFurniture(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    @Override
    public void updateFurniture(HttpServletRequest request, HttpServletResponse response) {

    }
}
