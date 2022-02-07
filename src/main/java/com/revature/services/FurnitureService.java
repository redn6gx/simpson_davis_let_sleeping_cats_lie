package com.revature.services;

import com.revature.exceptions.PersistenceException;
import com.revature.exceptions.ServiceUnavailableException;
import com.revature.models.Furniture;

import java.util.List;
import java.util.Optional;

public interface FurnitureService {

    /**
     * This method deletes a Furniture entity from the database.
     *
     * @param furniture                        the Furniture to be deleted
     * @param sessionId                        the user's session id
     * @throws PersistenceException            thrown when a problem occurs with modifying the database
     * @throws ServiceUnavailableException     thrown when connecting to the database is unavailable
     */
    public void deleteFurniture(Furniture furniture, String sessionId) throws PersistenceException, ServiceUnavailableException;

    /**
     * This method adds a Furniture entity to the database.
     *
     * @param furniture                        the Furniture to be added
     * @param sessionId                        the user's session id
     * @throws PersistenceException            thrown when a problem occurs with modifying the database
     * @throws ServiceUnavailableException     thrown when connecting to the database is unavailable
     */
    public void createFurniture(Furniture furniture, String sessionId) throws PersistenceException, ServiceUnavailableException;

    /**
     * This method adds multiple Furniture entities to the database.
     *
     * @param furniture                        the Furniture entities to be added
     * @param sessionId                        the user's session id
     * @throws PersistenceException            thrown when a problem occurs with modifying the database
     * @throws ServiceUnavailableException     thrown when connecting to the database is unavailable
     */
    public void createMultipleFurniture(List<Furniture> furniture, String sessionId) throws PersistenceException, ServiceUnavailableException;

    /**
     * This method gets a Furniture entity from the database by its id.
     *
     * @param id                               the id of the Furniture to get
     * @param sessionId                        the user's session id
     * @throws PersistenceException            thrown when a problem occurs with modifying the database
     * @throws ServiceUnavailableException     thrown when connecting to the database is unavailable
     */
    public Optional<Furniture> getFurnitureById(int id, String sessionId) throws PersistenceException, ServiceUnavailableException;

    /**
     * This method gets all Furniture entities from the database.
     *
     * @param sessionId                        the user's session id
     * @throws PersistenceException            thrown when a problem occurs with modifying the database
     * @throws ServiceUnavailableException     thrown when connecting to the database is unavailable
     */
    public List<Furniture> getAllFurniture(String sessionId) throws PersistenceException, ServiceUnavailableException;

    /**
     * This method updates a Furniture entity to the database.
     *
     * @param furniture                        the Furniture to be updated
     * @param sessionId                        the user's session id
     * @throws PersistenceException            thrown when a problem occurs with modifying the database
     * @throws ServiceUnavailableException     thrown when connecting to the database is unavailable
     */
    public void updateFurniture(Furniture furniture, String sessionId) throws PersistenceException, ServiceUnavailableException;
}
