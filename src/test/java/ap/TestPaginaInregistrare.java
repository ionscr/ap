package ap;
import ap.controllers.Inregistrare;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class TestPaginaInregistrare extends ApplicationTest{
    Stage stage1;
    @Override
    public void start (Stage stage) throws Exception {
        Parent mainNode = FXMLLoader.load(Main.class.getResource("/inregistrare.fxml"));
        stage1= stage;
        stage.setScene(new Scene(mainNode));
        stage.show();
        stage.toFront();
    }

    @Before
    public void setUp () throws Exception {
    }

    @After
    public void tearDown () throws Exception {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }
    @Test
    public void NumeleNuPoateContineNumere () {
        TextField nume = (TextField) GuiTest.find("#nume");
        TextField nr = (TextField) GuiTest.find("#nr_tel");
        nr.setText("124134312");
        nume.setText("Andrei2");
        clickOn("#b_inregistrare");
        Label labelnume = (Label) stage1.getScene().lookup("#hidden_nume");
        assertTrue(labelnume.isVisible());
    }
    @Test
    public void NumeleNuPoateAveaMaiPutinDe3Caractere () {
        TextField nume = (TextField) GuiTest.find("#nume");
        TextField nr = (TextField) GuiTest.find("#nr_tel");
        nr.setText("124134312");
        nume.setText("as");
        clickOn("#b_inregistrare");
        Label labelnume = (Label) stage1.getScene().lookup("#hidden_nume");
        assertTrue(labelnume.isVisible());
    }
    @Test
    public void NumelePoateContineCaractereSiSpatii () {
        TextField nume = (TextField) GuiTest.find("#nume");
        TextField nr = (TextField) GuiTest.find("#nr_tel");
        nr.setText("124134312");
        nume.setText("Andrei Andrei");
        clickOn("#b_inregistrare");
        Label labelnume = (Label) stage1.getScene().lookup("#hidden_nume");
        assertFalse(labelnume.isVisible());
    }
}