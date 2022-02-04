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
        try {
            EntityManager session = factory.getSessionContext(sessionId);
            session.persist(furniture);
            session.close();

        } catch (ConnectionFailedException e) {
            throw new ServiceUnavailableException();
        } catch (CatnapException e) {
            throw new PersistenceException();
        }
    }

    @Override
    public void createMultipleFurniture(List<Furniture> furniture, String sessionId) throws PersistenceException, ServiceUnavailableException {
        EntityManager session;
        try {
            session = factory.getSessionContext(sessionId);
            session.beginTransaction();

            for (Furniture f: furniture) {
                session = factory.getSessionContext(sessionId);
                session.persist(f);
            }

        } catch (ConnectionFailedException e) {
            throw new ServiceUnavailableException();
        } catch (CatnapException e) {
            throw new PersistenceException();
        }

        try {
            session.commit();
            session.close();
        } catch (RollbackException e) {
            try {
                session.rollback();
            } catch (CatnapException ex) {
                throw new PersistenceException();
            }
        } catch (CatnapException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Furniture> getFurnitureById(int id, String sessionId) throws PersistenceException, ServiceUnavailableException {
        try {
            EntityManager session = factory.getSessionContext(sessionId);
            Optional<Object> furniture = session.get(Furniture.class, id);

            return furniture.map(o -> (Furniture) o);
        } catch (ConnectionFailedException e) {
            throw new ServiceUnavailableException();
        } catch (CatnapException e) {
            throw new PersistenceException();
        }
    }

    @Override
    public List<Furniture> getAllFurniture(String sessionId) throws PersistenceException, ServiceUnavailableException {
        return null;
    }

    @Override
    public void updateFurniture(Furniture furniture, String sessionId) throws PersistenceException, ServiceUnavailableException {

    }
}
