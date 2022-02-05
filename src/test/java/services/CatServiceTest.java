package services;

import com.revature.exceptions.PersistenceException;
import com.revature.exceptions.ServiceUnavailableException;
import com.revature.models.Cat;
import com.revature.services.CatServiceImpl;
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
public class CatServiceTest {

    @InjectMocks
    private CatServiceImpl catService;

    @Mock
    private SessionFactory factory;

    @Mock
    private Session session;

    @Test
    public void testDeleteCat() throws PersistenceException, ServiceUnavailableException, CatnapException, ConnectionFailedException {
        Cat cat = new Cat();

        when(factory.getSessionContext("session")).thenReturn(session);

        catService.deleteCat(cat, "session");

        verify(session, times(1)).delete(cat);
    }

    @Test
    public void testDeleteCatCatnapException() throws CatnapException, ConnectionFailedException {
        Cat cat = new Cat();

        when(factory.getSessionContext("session")).thenReturn(session);
        doThrow(CatnapException.class).when(session).delete(cat);

        assertThrows(PersistenceException.class, () -> catService.deleteCat(cat, "session"));
    }

    @Test
    public void testDeleteCatConnectionFailedException() throws ConnectionFailedException {
        Cat cat = new Cat();

        when(factory.getSessionContext("session")).thenThrow(ConnectionFailedException.class);

        assertThrows(ServiceUnavailableException.class, () -> catService.deleteCat(cat, "session"));
    }

    @Test
    public void testCreateCat() throws PersistenceException, ServiceUnavailableException, CatnapException, ConnectionFailedException {
        Cat cat = new Cat();

        when(factory.getSessionContext("session")).thenReturn(session);

        catService.createCat(cat, "session");

        verify(session, times(1)).persist(cat);
    }

    @Test
    public void testCreateCatCatnapException() throws CatnapException, ConnectionFailedException {
        Cat cat = new Cat();

        when(factory.getSessionContext("session")).thenReturn(session);
        doThrow(CatnapException.class).when(session).persist(cat);

        assertThrows(PersistenceException.class, () -> catService.createCat(cat, "session"));
    }

    @Test
    public void testCreateCatConnectionFailedException() throws ConnectionFailedException {
        Cat cat = new Cat();

        when(factory.getSessionContext("session")).thenThrow(ConnectionFailedException.class);

        assertThrows(ServiceUnavailableException.class, () -> catService.createCat(cat, "session"));
    }

    @Test
    public void testCreateManyCats() throws PersistenceException, ServiceUnavailableException, CatnapException, ConnectionFailedException {
        Cat cat = new Cat();
        List<Cat> cats = new ArrayList<>();
        cats.add(cat);

        when(factory.getSessionContext("session")).thenReturn(session);

        catService.createManyCats(cats, "session");

        verify(session, times(1)).persist(Mockito.any(Cat.class));
    }

    @Test
    public void testCreateManyCatsCatnapException() throws CatnapException, ConnectionFailedException, RollbackException {
        Cat cat = new Cat();
        List<Cat> cats = new ArrayList<>();
        cats.add(cat);

        when(factory.getSessionContext("session")).thenReturn(session);
        doThrow(CatnapException.class).when(session).persist(cat);

        assertThrows(PersistenceException.class, () -> catService.createManyCats(cats, "session"));
    }

    @Test
    public void testCreateManyCatsRollbackException() throws ConnectionFailedException, RollbackException, CatnapException {
        Cat cat = new Cat();
        List<Cat> cats = new ArrayList<>();
        cats.add(cat);

        when(factory.getSessionContext("session")).thenReturn(session);
        doThrow(RollbackException.class).when(session).commit();
        doThrow(CatnapException.class).when(session).rollback();

        assertThrows(PersistenceException.class, () -> catService.createManyCats(cats, "session"));
    }

    @Test
    public void testCreateManyCatsConnectionFailedException() throws ConnectionFailedException {
        Cat cat = new Cat();
        List<Cat> cats = new ArrayList<>();
        cats.add(cat);

        when(factory.getSessionContext("session")).thenThrow(ConnectionFailedException.class);

        assertThrows(ServiceUnavailableException.class, () -> catService.createManyCats(cats, "session"));
    }

    @Test
    public void testGetCatdById() throws PersistenceException, ServiceUnavailableException, CatnapException, ConnectionFailedException {
        Cat cat = new Cat();

        when(factory.getSessionContext("session")).thenReturn(session);
        when(session.get(Cat.class, 0)).thenReturn(Optional.of(cat));

        Optional<Cat> gotCat = catService.getCatById(0, "session");

        assertTrue(gotCat.isPresent());
    }

    @Test
    public void testGetCatdByIdCatCatnapException() throws CatnapException, ConnectionFailedException {
        Cat cat = new Cat();

        when(factory.getSessionContext("session")).thenReturn(session);
        doThrow(CatnapException.class).when(session).get(Cat.class, 0);

        assertThrows(PersistenceException.class, () -> catService.getCatById(0, "session"));
    }

    @Test
    public void testGetCatdByIdCatConnectionFailedException() throws ConnectionFailedException {
        Cat cat = new Cat();

        when(factory.getSessionContext("session")).thenThrow(ConnectionFailedException.class);

        assertThrows(ServiceUnavailableException.class, () -> catService.getCatById(0, "session"));
    }

    @Test
    public void testGetAllCats() throws PersistenceException, ServiceUnavailableException, CatnapException, ConnectionFailedException {
        Cat cat = new Cat();
        List<Object> cats = new ArrayList<>();
        cats.add(cat);

        when(factory.getSessionContext("session")).thenReturn(session);
        when(session.getAll(Cat.class)).thenReturn(cats);

        List<Cat> gotCats = catService.getAllCats( "session");

        assertFalse(gotCats.isEmpty());
    }

    @Test
    public void testGetAllCatsCatCatnapException() throws CatnapException, ConnectionFailedException {
        Cat cat = new Cat();
        List<Object> cats = new ArrayList<>();
        cats.add(cat);

        when(factory.getSessionContext("session")).thenReturn(session);
        doThrow(CatnapException.class).when(session).getAll(Cat.class);

        assertThrows(PersistenceException.class, () -> catService.getAllCats( "session"));
    }

    @Test
    public void testGetAllCatsCatConnectionFailedException() throws ConnectionFailedException {
        Cat cat = new Cat();
        List<Object> cats = new ArrayList<>();
        cats.add(cat);

        when(factory.getSessionContext("session")).thenThrow(ConnectionFailedException.class);

        assertThrows(ServiceUnavailableException.class, () -> catService.getAllCats( "session"));
    }

    @Test
    public void testUpdateCat() throws PersistenceException, ServiceUnavailableException, CatnapException, ConnectionFailedException {
        Cat cat = new Cat();

        when(factory.getSessionContext("session")).thenReturn(session);

        catService.updateCat(cat, "session");

        verify(session, times(1)).update(cat);
    }

    @Test
    public void testUpdateCatCatnapException() throws CatnapException, ConnectionFailedException {
        Cat cat = new Cat();

        when(factory.getSessionContext("session")).thenReturn(session);
        doThrow(CatnapException.class).when(session).update(cat);

        assertThrows(PersistenceException.class, () -> catService.updateCat(cat, "session"));
    }

    @Test
    public void testUpdateCatConnectionFailedException() throws ConnectionFailedException {
        Cat cat = new Cat();

        when(factory.getSessionContext("session")).thenThrow(ConnectionFailedException.class);

        assertThrows(ServiceUnavailableException.class, () -> catService.updateCat(cat, "session"));
    }
}
