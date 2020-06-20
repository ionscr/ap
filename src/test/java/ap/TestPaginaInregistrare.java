package ap;
import ap.controllers.Inregistrare;
import javafx.embed.swing.JFXPanel;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class TestPaginaInregistrare{
    Inregistrare controller;

    @Before
    public void setUp() throws Exception {
        controller = new Inregistrare();
        JFXPanel panel = new JFXPanel();
        controller.nume = new TextField();
        controller.hidden_nume = new Label();
    }

    @Test
    public void Test_Nume_Cu_Cifre() throws IOException{
        controller.nume.setText("asd123");
        assertFalse(controller.checkNume());
    }
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