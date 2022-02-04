package com.revature.services;

import com.revature.exceptions.PersistenceException;
import com.revature.models.Cat;

import javax.naming.ServiceUnavailableException;
import java.util.List;

public interface CatService {

    public void deleteCat(Cat cat, String sessionId) throws PersistenceException, ServiceUnavailableException;
    public void createCat(Cat cat, String sessionId) throws PersistenceException, ServiceUnavailableException;
    public void createMany(List<Cat> cat, String sessionId) throws PersistenceException, ServiceUnavailableException;
    public Cat getCatById(int id, String sessionId) throws PersistenceException, ServiceUnavailableException;
    public List<Cat> getAllCats(String sessionId) throws PersistenceException, ServiceUnavailableException;
    public void updateCat(Cat cat, String sessionId) throws PersistenceException, ServiceUnavailableException;
}
