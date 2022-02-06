package com.revature.controllers;

import com.revature.models.Furniture;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface FurnitureController {

    public void deleteFurniture(HttpServletRequest request, HttpServletResponse response);
    public void createFurniture(HttpServletRequest request, HttpServletResponse response);
    public Furniture getFurnitureById(HttpServletRequest request, HttpServletResponse response);
    public List<Furniture> getAllFurniture(HttpServletRequest request, HttpServletResponse response);
    public void updateFurniture(HttpServletRequest request, HttpServletResponse response);
}
