package ap;
import ap.controllers.Inregistrare;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class TestPaginaInregistrare{
    protected String nume;
    protected String nr;
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
    @Test
    public void testNumeFaraCifre(){
        nume = "asd";
        assertTrue(inregistrare.isValidName(nume));
    }
    @Test
    public void testNumeCuSpatiu(){
        nume = "asd asd";
        assertTrue(inregistrare.isValidName(nume));
    }
    @Test
    public void testNrCuLitere(){
        nr = "123123asd";
        assertFalse(inregistrare.isValidNr(nr));
    }
    @Test
    public void testNrFaraLitere(){
        nr = "123123";
        assertTrue(inregistrare.isValidNr(nr));
    }
    @Test
    public void testNrCuSpatii(){
        nr = "1231 23";
        assertFalse(inregistrare.isValidNr(nr));
    }
}