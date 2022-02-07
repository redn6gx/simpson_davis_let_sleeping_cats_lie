package com.revature.services;

import com.revature.exceptions.PersistenceException;
import com.revature.models.Furniture;
import exceptions.CatnapException;
import exceptions.ConnectionFailedException;
import persistence.EntityManager;
import persistence.SessionFactory;

import com.revature.exceptions.ServiceUnavailableException;
import exceptions.RollbackException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FurnitureServiceImpl implements FurnitureService {

    private final SessionFactory factory;

    public FurnitureServiceImpl(SessionFactory factory) {
        this.factory = factory;
    }

    /**
     * This method deletes a Furniture entity from the database.
     *
     * @param furniture                        the Furniture to be deleted
     * @param sessionId                        the user's session id
     * @throws PersistenceException            thrown when a problem occurs with modifying the database
     * @throws ServiceUnavailableException     thrown when connecting to the database is unavailable
     */
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

    /**
     * This method adds a Furniture entity to the database.
     *
     * @param furniture                        the Furniture to be added
     * @param sessionId                        the user's session id
     * @throws PersistenceException            thrown when a problem occurs with modifying the database
     * @throws ServiceUnavailableException     thrown when connecting to the database is unavailable
     */
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

    /**
     * This method adds multiple Furniture entities to the database.
     *
     * @param furniture                        the Furniture entities to be added
     * @param sessionId                        the user's session id
     * @throws PersistenceException            thrown when a problem occurs with modifying the database
     * @throws ServiceUnavailableException     thrown when connecting to the database is unavailable
     */
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

    /**
     * This method gets a Furniture entity from the database by its id.
     *
     * @param id                               the id of the Furniture to get
     * @param sessionId                        the user's session id
     * @throws PersistenceException            thrown when a problem occurs with modifying the database
     * @throws ServiceUnavailableException     thrown when connecting to the database is unavailable
     */
    @Override
    public Optional<Furniture> getFurnitureById(int id, String sessionId) throws PersistenceException, ServiceUnavailableException {
        try {
            EntityManager session = factory.getSessionContext(sessionId);
            Optional<Object> furniture = session.get(Furniture.class, id);
            session.close();

            return furniture.map(o -> (Furniture) o);
        } catch (ConnectionFailedException e) {
            throw new ServiceUnavailableException();
        } catch (CatnapException e) {
            throw new PersistenceException();
        }
    }

    /**
     * This method gets all Furniture entities from the database.
     *
     * @param sessionId                        the user's session id
     * @throws PersistenceException            thrown when a problem occurs with modifying the database
     * @throws ServiceUnavailableException     thrown when connecting to the database is unavailable
     */
    @Override
    public List<Furniture> getAllFurniture(String sessionId) throws PersistenceException, ServiceUnavailableException {
        try {
            EntityManager session = factory.getSessionContext(sessionId);
            List<Object> furniture = session.getAll(Furniture.class);
            session.close();

            return furniture.stream().map(f -> (Furniture) f).collect(Collectors.toList());
        } catch (ConnectionFailedException e) {
            throw new ServiceUnavailableException();
        } catch (CatnapException e) {
            throw new PersistenceException();
        }
    }

    /**
     * This method updates a Furniture entity to the database.
     *
     * @param furniture                        the Furniture to be updated
     * @param sessionId                        the user's session id
     * @throws PersistenceException            thrown when a problem occurs with modifying the database
     * @throws ServiceUnavailableException     thrown when connecting to the database is unavailable
     */
    @Override
    public void updateFurniture(Furniture furniture, String sessionId) throws PersistenceException, ServiceUnavailableException {
        try {
            EntityManager session = factory.getSessionContext(sessionId);
            session.update(furniture);
            session.close();

        } catch (ConnectionFailedException e) {
            throw new ServiceUnavailableException();
        } catch (CatnapException e) {
            throw new PersistenceException();
        }
    }
}
