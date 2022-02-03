package com.revature.services;

import com.revature.exceptions.PersistenceException;
import com.revature.models.Furniture;

import javax.naming.ServiceUnavailableException;
import java.util.List;

public interface FurnitureService {

    public void deleteFurniture(Furniture furniture) throws PersistenceException, ServiceUnavailableException;
    public void createFurniture(Furniture furniture) throws PersistenceException, ServiceUnavailableException;
    public Furniture getFurnitureById(int id) throws PersistenceException, ServiceUnavailableException;
    public List<Furniture> getAllFurniture() throws PersistenceException, ServiceUnavailableException;
    public void updateFurniture(Furniture furniture) throws PersistenceException, ServiceUnavailableException;
}
