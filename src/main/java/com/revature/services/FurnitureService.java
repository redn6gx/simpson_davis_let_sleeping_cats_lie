package com.revature.services;

import com.revature.exceptions.PersistenceException;
import com.revature.exceptions.ServiceUnavailableException;
import com.revature.models.Furniture;

import java.util.List;
import java.util.Optional;

public interface FurnitureService {

    public void deleteFurniture(Furniture furniture, String sessionId) throws PersistenceException, ServiceUnavailableException;
    public void createFurniture(Furniture furniture, String sessionId) throws PersistenceException, ServiceUnavailableException;
    public void createMultipleFurniture(List<Furniture> furniture, String sessionId) throws PersistenceException, ServiceUnavailableException;
    public Optional<Furniture> getFurnitureById(int id, String sessionId) throws PersistenceException, ServiceUnavailableException;
    public List<Furniture> getAllFurniture(String sessionId) throws PersistenceException, ServiceUnavailableException;
    public void updateFurniture(Furniture furniture, String sessionId) throws PersistenceException, ServiceUnavailableException;
}
