package com.revature.services;

import com.revature.models.Cat;

import java.util.List;

public interface CatService {

    public void deleteCat(Cat cat);
    public void createCat(Cat cat);
    public Cat getCatById(int id);
    public List<Cat> getAllCats();
    public void updateCat(Cat cat);
}
