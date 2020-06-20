package ap;
import ap.controllers.Inregistrare;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class TestPaginaInregistrare{
    protected String nume;
    protected Inregistrare inregistrare;
    // assigning the values
    @Before
    public void setUp() throws IOException {
        inregistrare = new Inregistrare();
    }
    // test method to add two values
    @Test
    public void testNumeCuCifre(){
        nume = "asd123";
        assertFalse(inregistrare.isValidName(nume));
    }
}