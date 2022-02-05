package services;

import com.revature.exceptions.PersistenceException;
import com.revature.exceptions.ServiceUnavailableException;
import com.revature.models.Furniture;
import com.revature.services.FurnitureServiceImpl;
import exceptions.CatnapException;
import exceptions.ConnectionFailedException;
import exceptions.RollbackException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import persistence.Session;
import persistence.SessionFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FurnitureServiceTest {

    @InjectMocks
    private FurnitureServiceImpl furnitureService;

    @Mock
    private SessionFactory factory;

    @Mock
    private Session session;

    @Test
    public void testDeleteFurniture() throws PersistenceException, ServiceUnavailableException, CatnapException, ConnectionFailedException {
        Furniture cat = new Furniture();

        when(factory.getSessionContext("session")).thenReturn(session);

        furnitureService.deleteFurniture(cat, "session");

        verify(session, times(1)).delete(cat);
    }

    @Test
    public void testDeleteFurnitureCatnapException() throws CatnapException, ConnectionFailedException {
        Furniture cat = new Furniture();

        when(factory.getSessionContext("session")).thenReturn(session);
        doThrow(CatnapException.class).when(session).delete(cat);

        assertThrows(PersistenceException.class, () -> furnitureService.deleteFurniture(cat, "session"));
    }

    @Test
    public void testDeleteFurnitureConnectionFailedException() throws ConnectionFailedException {
        Furniture cat = new Furniture();

        when(factory.getSessionContext("session")).thenThrow(ConnectionFailedException.class);

        assertThrows(ServiceUnavailableException.class, () -> furnitureService.deleteFurniture(cat, "session"));
    }

    @Test
    public void testCreateFurniture() throws PersistenceException, ServiceUnavailableException, CatnapException, ConnectionFailedException {
        Furniture cat = new Furniture();

        when(factory.getSessionContext("session")).thenReturn(session);

        furnitureService.createFurniture(cat, "session");

        verify(session, times(1)).persist(cat);
    }

    @Test
    public void testCreateFurnitureCatnapException() throws CatnapException, ConnectionFailedException {
        Furniture cat = new Furniture();

        when(factory.getSessionContext("session")).thenReturn(session);
        doThrow(CatnapException.class).when(session).persist(cat);

        assertThrows(PersistenceException.class, () -> furnitureService.createFurniture(cat, "session"));
    }

    @Test
    public void testCreateFurnitureConnectionFailedException() throws ConnectionFailedException {
        Furniture cat = new Furniture();

        when(factory.getSessionContext("session")).thenThrow(ConnectionFailedException.class);

        assertThrows(ServiceUnavailableException.class, () -> furnitureService.createFurniture(cat, "session"));
    }

    @Test
    public void testCreateManyFurniture() throws PersistenceException, ServiceUnavailableException, CatnapException, ConnectionFailedException {
        Furniture cat = new Furniture();
        List<Furniture> mFurniture = new ArrayList<>();
        mFurniture.add(cat);

        when(factory.getSessionContext("session")).thenReturn(session);

        furnitureService.createMultipleFurniture(mFurniture, "session");

        verify(session, times(1)).persist(Mockito.any(Furniture.class));
    }

    @Test
    public void testCreateMultipleFurnitureCatnapException() throws CatnapException, ConnectionFailedException, RollbackException {
        Furniture cat = new Furniture();
        List<Furniture> mFurniture = new ArrayList<>();
        mFurniture.add(cat);

        when(factory.getSessionContext("session")).thenReturn(session);
        doThrow(CatnapException.class).when(session).persist(cat);

        assertThrows(PersistenceException.class, () -> furnitureService.createMultipleFurniture(mFurniture, "session"));
    }

    @Test
    public void testCreateMultipleFurnitureRollbackException() throws ConnectionFailedException, RollbackException, CatnapException {
        Furniture cat = new Furniture();
        List<Furniture> mFurniture = new ArrayList<>();
        mFurniture.add(cat);

        when(factory.getSessionContext("session")).thenReturn(session);
        doThrow(RollbackException.class).when(session).commit();
        doThrow(CatnapException.class).when(session).rollback();

        assertThrows(PersistenceException.class, () -> furnitureService.createMultipleFurniture(mFurniture, "session"));
    }

    @Test
    public void testCreateMultipleFurnitureConnectionFailedException() throws ConnectionFailedException {
        Furniture cat = new Furniture();
        List<Furniture> mFurniture = new ArrayList<>();
        mFurniture.add(cat);

        when(factory.getSessionContext("session")).thenThrow(ConnectionFailedException.class);

        assertThrows(ServiceUnavailableException.class, () -> furnitureService.createMultipleFurniture(mFurniture, "session"));
    }

    @Test
    public void testGetFurnituredById() throws PersistenceException, ServiceUnavailableException, CatnapException, ConnectionFailedException {
        Furniture cat = new Furniture();

        when(factory.getSessionContext("session")).thenReturn(session);
        when(session.get(Furniture.class, 0)).thenReturn(Optional.of(cat));

        Optional<Furniture> gotFurniture = furnitureService.getFurnitureById(0, "session");

        assertTrue(gotFurniture.isPresent());
    }

    @Test
    public void testGetFurnituredByIdFurnitureCatnapException() throws CatnapException, ConnectionFailedException {
        Furniture cat = new Furniture();

        when(factory.getSessionContext("session")).thenReturn(session);
        doThrow(CatnapException.class).when(session).get(Furniture.class, 0);

        assertThrows(PersistenceException.class, () -> furnitureService.getFurnitureById(0, "session"));
    }

    @Test
    public void testGetFurnituredByIdFurnitureConnectionFailedException() throws ConnectionFailedException {
        Furniture cat = new Furniture();

        when(factory.getSessionContext("session")).thenThrow(ConnectionFailedException.class);

        assertThrows(ServiceUnavailableException.class, () -> furnitureService.getFurnitureById(0, "session"));
    }

    @Test
    public void testGetAllFurniture() throws PersistenceException, ServiceUnavailableException, CatnapException, ConnectionFailedException {
        Furniture cat = new Furniture();
        List<Object> mFurniture = new ArrayList<>();
        mFurniture.add(cat);

        when(factory.getSessionContext("session")).thenReturn(session);
        when(session.getAll(Furniture.class)).thenReturn(mFurniture);

        List<Furniture> gotFurniture = furnitureService.getAllFurniture( "session");

        assertFalse(gotFurniture.isEmpty());
    }

    @Test
    public void testGetAllFurnitureFurnitureCatnapException() throws CatnapException, ConnectionFailedException {
        Furniture cat = new Furniture();
        List<Object> mFurniture = new ArrayList<>();
        mFurniture.add(cat);

        when(factory.getSessionContext("session")).thenReturn(session);
        doThrow(CatnapException.class).when(session).getAll(Furniture.class);

        assertThrows(PersistenceException.class, () -> furnitureService.getAllFurniture( "session"));
    }

    @Test
    public void testGetAllFurnitureFurnitureConnectionFailedException() throws ConnectionFailedException {
        Furniture cat = new Furniture();
        List<Object> mFurniture = new ArrayList<>();
        mFurniture.add(cat);

        when(factory.getSessionContext("session")).thenThrow(ConnectionFailedException.class);

        assertThrows(ServiceUnavailableException.class, () -> furnitureService.getAllFurniture( "session"));
    }

    @Test
    public void testUpdateFurniture() throws PersistenceException, ServiceUnavailableException, CatnapException, ConnectionFailedException {
        Furniture cat = new Furniture();

        when(factory.getSessionContext("session")).thenReturn(session);

        furnitureService.updateFurniture(cat, "session");

        verify(session, times(1)).update(cat);
    }

    @Test
    public void testUpdateFurnitureCatnapException() throws CatnapException, ConnectionFailedException {
        Furniture cat = new Furniture();

        when(factory.getSessionContext("session")).thenReturn(session);
        doThrow(CatnapException.class).when(session).update(cat);

        assertThrows(PersistenceException.class, () -> furnitureService.updateFurniture(cat, "session"));
    }

    @Test
    public void testUpdateFurnitureConnectionFailedException() throws ConnectionFailedException {
        Furniture cat = new Furniture();

        when(factory.getSessionContext("session")).thenThrow(ConnectionFailedException.class);

        assertThrows(ServiceUnavailableException.class, () -> furnitureService.updateFurniture(cat, "session"));
    }
}
