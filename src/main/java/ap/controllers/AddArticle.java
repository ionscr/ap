package ap.controllers;

import ap.controllers.Services.API_Handler;
import ap.controllers.Services.Articol;
import ap.controllers.Services.Liste;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;

public class AddArticle {
    @FXML
    private RadioButton isPlan;
    @FXML
    private RadioButton isAntrenament;
    @FXML
    private TextField nameInput;
    @FXML
    private TextArea descriptionInput;
    @FXML
    private Button addBtn;

    @FXML
    void addNewArticle(ActionEvent event) throws IOException, JSONException {
            final String POST_PARAMS = "{\n" +
                    "    \"nume\": \"" + nameInput.getText() + "\",\r\n" +
                    "    \"descriere\": \"" + descriptionInput.getText() + "\",\r\n" +
                    "    \"tip\": \"" + isAntrenament.isSelected() + "\"\n}";

            String response =
                    API_Handler.makeRequest("PUT", "articles", POST_PARAMS).toString();
            JSONObject record = new JSONObject(response);
            Liste.push(new Articol(
                    record.getInt("id"),
                    record.getString("nume"),
                    record.getString("poza"),
                    record.getString("descriere"),
                    record.getBoolean("tip")
            ));
    }
}