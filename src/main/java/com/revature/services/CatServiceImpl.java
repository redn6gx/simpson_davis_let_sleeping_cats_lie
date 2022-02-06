package com.revature.services;

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

public class CatServiceImpl implements CatService {

    private final SessionFactory factory;

    public CatServiceImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public void deleteCat(Cat cat, String sessionId) throws PersistenceException, ServiceUnavailableException {
        try {
            EntityManager session = factory.getSessionContext(sessionId);
            session.delete(cat);
            session.close();

        } catch (ConnectionFailedException e) {
            throw new ServiceUnavailableException();
        } catch (CatnapException e) {
            throw new PersistenceException();
        }
    }

    @Override
    public void createCat(Cat cat, String sessionId) throws PersistenceException, ServiceUnavailableException {
        try {
            EntityManager session = factory.getSessionContext(sessionId);
            session.persist(cat);
            session.close();

        } catch (ConnectionFailedException e) {
            throw new ServiceUnavailableException();
        } catch (CatnapException e) {
            throw new PersistenceException();
        }
    }

    @Override
    public void createManyCats(List<Cat> cats, String sessionId) throws PersistenceException, ServiceUnavailableException {
        EntityManager session;
        try {
            session = factory.getSessionContext(sessionId);
            session.beginTransaction();

            for (Cat cat: cats) {
                session = factory.getSessionContext(sessionId);
                session.persist(cat);
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
    public Optional<Cat> getCatById(int id, String sessionId) throws PersistenceException, ServiceUnavailableException {
        try {
            EntityManager session = factory.getSessionContext(sessionId);
            Optional<Object> cat = session.get(Cat.class, id);
            session.close();

            return cat.map(o -> (Cat) o);
        } catch (ConnectionFailedException e) {
            throw new ServiceUnavailableException();
        } catch (CatnapException e) {
            throw new PersistenceException();
        }
    }

    @Override
    public List<Cat> getAllCats(String sessionId) throws PersistenceException, ServiceUnavailableException {
        try {
            EntityManager session = factory.getSessionContext(sessionId);
            List<Object> cats = session.getAll(Cat.class);
            session.close();

            return cats.stream().map(cat -> (Cat) cat).collect(Collectors.toList());
        } catch (ConnectionFailedException e) {
            throw new ServiceUnavailableException();
        } catch (CatnapException e) {
            throw new PersistenceException();
        }
    }

    @Override
    public void updateCat(Cat cat, String sessionId) throws PersistenceException, ServiceUnavailableException {
        try {
            EntityManager session = factory.getSessionContext(sessionId);
            session.update(cat);
            session.close();

        } catch (ConnectionFailedException e) {
            throw new ServiceUnavailableException();
        } catch (CatnapException e) {
            throw new PersistenceException();
        }
    }
}
