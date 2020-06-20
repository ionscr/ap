package ap.controllers;

import ap.controllers.Services.Articol;
import ap.controllers.Services.Liste;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ap.controllers.Services.API_Handler;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

public class ArticolDialogUser extends Articol {
    @FXML
    private Label nameInput;
    @FXML
    private Pane imageHolder;
    @FXML
    private Pane closeBtn;
    @FXML
    private TextArea commentsInput;
    Articol updatedArticol = null;
    ArticlesUser articlesController = null;
    public ArticolDialogUser(Articol articol, ArticlesUser articlesController) {
        super(articol);
        this.articlesController = articlesController;
        this.updatedArticol = articol;
    }


    @FXML
    public void initialize() {
        nameInput.setText(nume);
        commentsInput.setText(descriere);
        imageHolder.setStyle(String.format("-fx-background-image: url(%s);", poza));
        closeBtn.setOnMouseClicked(mouseEvent -> {
            Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            // do what you have to do
            stage.close();
        });

    }
}
