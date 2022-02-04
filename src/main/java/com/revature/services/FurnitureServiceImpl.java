package com.revature.services;

import com.revature.exceptions.PersistenceException;
import com.revature.models.Furniture;
import exceptions.CatnapException;
import exceptions.ConnectionFailedException;
import persistence.EntityManager;
import persistence.SessionFactory;

import com.revature.exceptions.PersistenceException;
import com.revature.exceptions.ServiceUnavailableException;
import com.revature.models.Cat;
import exceptions.CatnapException;
import exceptions.ConnectionFailedException;
import exceptions.RollbackException;
import persistence.EntityManager;
import persistence.SessionFactory;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FurnitureServiceImpl implements FurnitureService {

    private final SessionFactory factory;

    public FurnitureServiceImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public void deleteFurniture(Furniture furniture, String sessionId) throws PersistenceException, ServiceUnavailableException {
        try {
            EntityManager session = factory.getSessionContext(sessionId);
            session.delete(furniture);
            session.close();

        } catch (ConnectionFailedException e) {
            throw new ServiceUnavailableException();
        } catch (CatnapException e) {
            throw new PersistenceException();
        }
    }

    @Override
    public void createFurniture(Furniture furniture, String sessionId) throws PersistenceException, ServiceUnavailableException {

    }

    @Override
    public Optional<Furniture> getFurnitureById(int id, String sessionId) throws PersistenceException, ServiceUnavailableException {
        return Optional.empty();
    }

    @Override
    public List<Furniture> getAllFurniture(String sessionId) throws PersistenceException, ServiceUnavailableException {
        return null;
    }

    @Override
    public void updateFurniture(Furniture furniture, String sessionId) throws PersistenceException, ServiceUnavailableException {

    }
}
