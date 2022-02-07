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

    /**
     * This method deletes a Cat entity from the database.
     *
     * @param cat                              the Cat to be deleted
     * @param sessionId                        the user's session id
     * @throws PersistenceException            thrown when a problem occurs with modifying the database
     * @throws ServiceUnavailableException     thrown when connecting to the database is unavailable
     */
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

    /**
     * This method adas a Cat entity to the database.
     *
     * @param cat                              the Cat to be added
     * @param sessionId                        the user's session id
     * @throws PersistenceException            thrown when a problem occurs with modifying the database
     * @throws ServiceUnavailableException     thrown when connecting to the database is unavailable
     */
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

    /**
     * This method adds multiple Cat entities to the database.
     * @param cats                             the Cat entities to be added
     * @param sessionId                        the user's session id
     * @throws PersistenceException            thrown when a problem occurs with modifying the database
     * @throws ServiceUnavailableException     thrown when connecting to the database is unavailable
     */
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

    /**
     * This method gets a Cat entity by its id.
     *
     * @param id                               the ud of the Cat to be found
     * @param sessionId                        the user's session id
     * @throws PersistenceException            thrown when a problem occurs with modifying the database
     * @throws ServiceUnavailableException     thrown when connecting to the database is unavailable
     */
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

    /**
     * This method gets all Cat entities from the database.
     *
     * @param sessionId                        the user's session id
     * @throws PersistenceException            thrown when a problem occurs with modifying the database
     * @throws ServiceUnavailableException     thrown when connecting to the database is unavailable
     */
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

    /**
     * This method updates a Cat entity to the database.
     *
     * @param cat                              the Cat to be updated
     * @param sessionId                        the user's session id
     * @throws PersistenceException            thrown when a problem occurs with modifying the database
     * @throws ServiceUnavailableException     thrown when connecting to the database is unavailable
     */
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
