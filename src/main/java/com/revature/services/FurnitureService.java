package com.revature.services;

import com.revature.models.Furniture;

import java.util.List;

public interface FurnitureService {

    public void deleteFurniture(Furniture furniture);
    public void createFurniture(Furniture furniture);
    public Furniture getFurnitureById(int id);
    public List<Furniture> getAllFurniture();
    public void updateFurniture(Furniture furniture);
}
