package ap;
import ap.controllers.Inregistrare;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class TestPaginaInregistrare{
    protected int value1, value2;

    // assigning the values
    @Before
    public void setUp(){
        value1 = 3;
        value2 = 3;
    }
    // test method to add two values
    @Test
    public void testAdd(){
        double result = value1 + value2;
        assertTrue(result == 6);
    }
//    Inregistrare controller;
//
//    @Before
//    public void setUp() throws Exception {
//        controller = new Inregistrare();
//        controller.nume = new TextField();
//        controller.hidden_nume = new Label();
//    }

//    @Test
//    public void Test_Nume_Cu_Cifre() throws IOException{
//        controller.nume.setText("asd123");
//        assertFalse(controller.checkNume());
//    }
//    @Test
//    public void Test_Nume_Fara_Cifre() throws IOException{
//        controller.nume.setText("asd");
//        assertTrue(controller.checkNume());
//    }
//    @Test
//    public void Test_Nume_Cu_Spatii() throws IOException{
//        controller.nume.setText("asd fgh");
//        assertTrue(controller.checkNume());
//    }
}