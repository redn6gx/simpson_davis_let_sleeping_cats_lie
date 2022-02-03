package com.revature.services;

import com.revature.exceptions.PersistenceException;
import com.revature.models.Cat;

import javax.naming.ServiceUnavailableException;
import java.util.List;

public interface CatService {

    public void deleteCat(Cat cat) throws PersistenceException, ServiceUnavailableException;
    public void createCat(Cat cat) throws PersistenceException, ServiceUnavailableException;
    public Cat getCatById(int id) throws PersistenceException, ServiceUnavailableException;
    public List<Cat> getAllCats() throws PersistenceException, ServiceUnavailableException;
    public void updateCat(Cat cat) throws PersistenceException, ServiceUnavailableException;
}
