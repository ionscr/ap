package ap;
import ap.controllers.Services.Articol;
import ap.controllers.Services.Intrebare;
import ap.controllers.Services.Liste;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class TestServicii {
    protected Articol articol;
    protected Intrebare intrebare;
    protected Liste lista;
    // assigning the values
    @Before
    public void setUp() throws IOException {
        lista = new Liste();
        articol = new Articol(1,"articol1","asd.jpg","afdsaf",false);
        intrebare = new Intrebare(1,1,"intrebare1","raspuns1","andrei");
    }
    @Test
    public void testPushListeArticole(){
        lista.articoleList.clear();
        lista.push(articol);
        assertEquals(1,lista.getArticlesAmount());
    }
    @Test
    public void testPushListeIntrebari(){
        lista.intrebareList.clear();
        lista.push(intrebare);
        assertEquals(1,lista.getintrebareAmount());
    }
    @Test
    public void testCountListeIntrebari(){
        lista.intrebareList.clear();
        lista.push(intrebare);
        lista.push(intrebare);
        lista.push(intrebare);
        assertEquals(3,lista.getintrebareAmount());
    }
    @Test
    public void testCountListeArticole(){
        lista.articoleList.clear();
        lista.push(articol);
        lista.push(articol);
        lista.push(articol);
        assertEquals(3,lista.getArticlesAmount());
    }
    @Test
    public void testDeleteArticol(){
        lista.articoleList.clear();
        lista.push(articol);
        lista.deleteArticol(1);
        assertEquals(0,lista.getArticlesAmount());
    }
    @Test
    public void testIntrebariNeraspunse(){
        lista.intrebareList.clear();
        lista.push(intrebare);
        assertEquals(0,lista.getNIntrebariAmount());
        lista.push(new Intrebare(2,2,"asd","","asd"));
        assertEquals(1,lista.getNIntrebariAmount());
    }
}