package com.revature.controllers;

import com.revature.models.Cat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface CatController {

    public void deleteCat(HttpServletRequest request, HttpServletResponse response);
    public void createCat(HttpServletRequest request, HttpServletResponse response);
    public Cat getCatById(HttpServletRequest request, HttpServletResponse response);
    public List<Cat> getAllCats(HttpServletRequest request, HttpServletResponse response);
    public void updateCat(HttpServletRequest request, HttpServletResponse response);
}
