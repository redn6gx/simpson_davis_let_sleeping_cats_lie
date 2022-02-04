package com.revature.services;

import com.revature.exceptions.PersistenceException;
import com.revature.models.Cat;
import exceptions.CatnapException;
import exceptions.ConnectionFailedException;
import exceptions.RollbackException;
import persistence.EntityManager;
import persistence.SessionFactory;

import javax.naming.ServiceUnavailableException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CatServiceImpl implements CatService {


    @Override
    public void deleteCat(Cat cat, String sessionId) throws PersistenceException, ServiceUnavailableException {
        try {
            EntityManager session = SessionFactory.getInstance().getSessionContext(sessionId);
            session.delete(cat);

        } catch (ConnectionFailedException e) {
            throw new ServiceUnavailableException();
        } catch (CatnapException e) {
            throw new PersistenceException();
        }
    }

    @Override
    public void createCat(Cat cat, String sessionId) throws PersistenceException, ServiceUnavailableException {
        try {
            EntityManager session = SessionFactory.getInstance().getSessionContext(sessionId);
            session.persist(cat);

        } catch (ConnectionFailedException e) {
            throw new ServiceUnavailableException();
        } catch (CatnapException e) {
            throw new PersistenceException();
        }
    }

    @Override
    public void createMany(List<Cat> cat, String sessionId) throws PersistenceException, ServiceUnavailableException {
        EntityManager session;
        try {
            session = SessionFactory.getInstance().getSessionContext(sessionId);
            session.beginTransaction();

        } catch (ConnectionFailedException e) {
            throw new ServiceUnavailableException();
        } catch (CatnapException e) {
            throw new PersistenceException();
        }

        for (Cat c: cat) {
            createCat(c, sessionId);
        }

        try {
            session.commit();
        } catch (RollbackException e) {
            try {
                session.rollback();
            } catch (CatnapException ex) {
                throw new PersistenceException();
            }
        }
    }

    @Override
    public Optional<Cat> getCatById(int id, String sessionId) throws PersistenceException, ServiceUnavailableException {
        try {
            EntityManager session = SessionFactory.getInstance().getSessionContext(sessionId);
            Optional<Object> cat = session.get(Cat.class, id);

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
            EntityManager session = SessionFactory.getInstance().getSessionContext(sessionId);
            List<Object> cats = session.getAll(Cat.class);

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
            EntityManager session = SessionFactory.getInstance().getSessionContext(sessionId);
            session.update(cat);

        } catch (ConnectionFailedException e) {
            throw new ServiceUnavailableException();
        } catch (CatnapException e) {
            throw new PersistenceException();
        }
    }
}
