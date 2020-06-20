package ap;
import ap.controllers.Autentificare;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class TestPaginaAutentificare{
    protected String nume;
    protected String pass;
    protected Autentificare autentificare;
    // assigning the values
    @Before
    public void setUp() throws IOException {
        autentificare = new Autentificare();
    }
    // test method to add two values
    @Test
    public void testUserNul(){
        nume = "";
        assertFalse(autentificare.checkNume(nume));
    }
    @Test
    public void testUserNeNul(){
        nume = "sadfsdf";
        assertTrue(autentificare.checkNume(nume));
    }
    @Test
    public void testPassNul(){
         pass = "";
        assertFalse(autentificare.checkPassword(pass));
    }
    @Test
    public void testPassNeNul(){
        pass = "sadfsdf";
        assertTrue(autentificare.checkPassword(pass));
    }
}