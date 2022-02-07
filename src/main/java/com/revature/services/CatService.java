package com.revature.services;

import com.revature.exceptions.ServiceUnavailableException;
import com.revature.exceptions.PersistenceException;
import com.revature.models.Cat;

import java.util.List;
import java.util.Optional;

public interface CatService {

    /**
     * This method deletes a Cat entity from the database.
     *
     * @param cat                              the Cat to be deleted
     * @param sessionId                        the user's session id
     * @throws PersistenceException            thrown when a problem occurs with modifying the database
     * @throws ServiceUnavailableException     thrown when connecting to the database is unavailable
     */
    public void deleteCat(Cat cat, String sessionId) throws PersistenceException, ServiceUnavailableException;

    /**
     * This method adas a Cat entity to the database.
     *
     * @param cat                              the Cat to be added
     * @param sessionId                        the user's session id
     * @throws PersistenceException            thrown when a problem occurs with modifying the database
     * @throws ServiceUnavailableException     thrown when connecting to the database is unavailable
     */
    public void createCat(Cat cat, String sessionId) throws PersistenceException, ServiceUnavailableException;

    /**
     * This method adds multiple Cat entities to the database.
     * @param cats                             the Cat entities to be added
     * @param sessionId                        the user's session id
     * @throws PersistenceException            thrown when a problem occurs with modifying the database
     * @throws ServiceUnavailableException     thrown when connecting to the database is unavailable
     */
    public void createManyCats(List<Cat> cats, String sessionId) throws PersistenceException, ServiceUnavailableException;

    /**
     * This method gets a Cat entity by its id.
     *
     * @param id                               the ud of the Cat to be found
     * @param sessionId                        the user's session id
     * @throws PersistenceException            thrown when a problem occurs with modifying the database
     * @throws ServiceUnavailableException     thrown when connecting to the database is unavailable
     */
    public Optional<Cat> getCatById(int id, String sessionId) throws PersistenceException, ServiceUnavailableException;

    /**
     * This method gets all Cat entities from the database.
     *
     * @param sessionId                        the user's session id
     * @throws PersistenceException            thrown when a problem occurs with modifying the database
     * @throws ServiceUnavailableException     thrown when connecting to the database is unavailable
     */
    public List<Cat> getAllCats(String sessionId) throws PersistenceException, ServiceUnavailableException;

    /**
     * This method updates a Cat entity to the database.
     *
     * @param cat                              the Cat to be updated
     * @param sessionId                        the user's session id
     * @throws PersistenceException            thrown when a problem occurs with modifying the database
     * @throws ServiceUnavailableException     thrown when connecting to the database is unavailable
     */
    public void updateCat(Cat cat, String sessionId) throws PersistenceException, ServiceUnavailableException;
}
