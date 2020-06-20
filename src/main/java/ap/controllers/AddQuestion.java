package ap.controllers;

import ap.controllers.Services.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import org.json.JSONException;
import org.json.JSONObject;

import javax.rmi.CORBA.Util;
import java.io.*;

public class AddQuestion {
    @FXML
    private TextArea descriptionInput;
    @FXML
    private Button addBtn;

    @FXML
    void addNewQuestion(ActionEvent event) throws IOException, JSONException {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id_user", Utilizator.id);
            jsonObject.put("text", descriptionInput.getText() );
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String response =
                API_Handler.makeRequest("PUT", "questions", jsonObject.toString()).toString();
        JSONObject record = new JSONObject(response);
        Liste.push(new Intrebare(
                record.getInt("id"),
                Utilizator.id,
                descriptionInput.getText(),
                "",
                Utilizator.nume
        ));
    }
}