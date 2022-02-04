package com.revature.controllers;

import com.revature.models.Cat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CatControllerImpl implements CatController{

    public CatControllerImpl(Object o){}

    @Override
    public void deleteCat(HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    public void createCat(HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    public Cat getCatById(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    @Override
    public List<Cat> getAllCats(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    @Override
    public void updateCat(HttpServletRequest request, HttpServletResponse response) {

    }
}
