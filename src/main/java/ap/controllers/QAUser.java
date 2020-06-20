package ap.controllers;
import ap.controllers.Services.Articol;
import ap.controllers.Services.Intrebare;
import ap.controllers.Services.Liste;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.json.JSONException;

import java.io.*;
import java.util.*;
public class QAUser {
    @FXML
    private VBox wrapper;
    @FXML
    private ScrollPane scroll;
    @FXML
    private Button saveBtn;
    @FXML
    public void appendTemplate(QACardUser qacard,
                               int layoutX, int layoutY) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/qa_card_user.fxml"));
        loader.setController(qacard);
        Pane qaCard = loader.load();
        qaCard.setLayoutX(layoutX);
        qaCard.setLayoutY(layoutY);

        wrapper.getChildren().add(qaCard);
        scroll.setContent(wrapper);
    }
    @FXML
    void renderView() throws IOException {
        wrapper.getChildren().clear();
        int layoutY = 30;
        int layoutX = 30;
        int qaAmount = Liste.getintrebareAmount();
        int occurrences = 0;
        for (int i = 0; i < qaAmount; i++) {
            Intrebare intrebare = Liste.getIntrebare(i);
            appendTemplate(new QACardUser(intrebare, this), layoutX, layoutY);
            layoutY += 270;
            occurrences++;
        }
    }
    @FXML
    public void renderAll() throws IOException, JSONException {
        renderView();
    }

    @FXML
    public void initialize() throws IOException, JSONException {
        renderView();
    }
}
