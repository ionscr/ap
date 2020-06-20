package ap.controllers;
import ap.controllers.Services.API_Handler;
import ap.controllers.Services.Articol;
import ap.controllers.Services.Intrebare;
import ap.controllers.Services.Liste;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.util.*;
public class ArticlesUser {
    @FXML
    private AnchorPane wrapper;
    @FXML
    private ScrollPane scroll;
    @FXML
    private TextField searchBar;
    @FXML
    private ComboBox categoryBox;
    @FXML
    private Button showAll;
    public StringBuffer response = new StringBuffer();
    Set<String> categories = new HashSet<String>();
    public ArticlesUser() throws IOException, JSONException {
        if (Liste.getArticlesAmount() == 0) {
            response = API_Handler.getRecords("articles", "");
            JSONArray jsonArray = new JSONArray(response.toString());
            int recordAmount = jsonArray.length();
            for (int i = 0; i < recordAmount; i++) {
                JSONObject record = jsonArray.getJSONObject(i);
                Liste.push(new Articol(
                        record.getInt("id"),
                        record.getString("nume"),
                        record.getString("poza"),
                        record.getString("descriere"),
                        record.getBoolean("tip")));
            }
        }
        if (Liste.getintrebareAmount() == 0) {
            response = API_Handler.getRecords("questions", "");
            JSONArray jsonArray = new JSONArray(response.toString());
            int recordAmount = jsonArray.length();
            for (int i = 0; i < recordAmount; i++) {
                JSONObject record = jsonArray.getJSONObject(i);
                Liste.push(new Intrebare(
                        record.getInt("id"),
                        record.getInt("id_user"),
                        record.getString("text"),
                        record.getString("raspuns"),
                        record.getString("nume")));
            }
        }
    }
    @FXML
    public void appendTemplate(ArticolCardUser articol,
                               int layoutX, int layoutY) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/articol_card_user.fxml"));
        loader.setController(articol);
        Pane articolCard = loader.load();
        articolCard.setLayoutX(layoutX);
        articolCard.setLayoutY(layoutY);

        wrapper.getChildren().add(articolCard);
        scroll.setContent(wrapper);
    }

    @FXML
    private void search(ActionEvent event) throws IOException {
        renderView(((TextField) event.getSource()).getText());
    }

    @FXML
    public void filter(ActionEvent event) throws IOException {
        renderView(categoryBox.getValue().toString());
    }

    @FXML
    void renderView(String filter) throws IOException {
        wrapper.getChildren().clear();
        int layoutY = 30;

        int articolsAmount = Liste.getArticlesAmount();
        int occurrences = 0;
        for (int i = 0; i < articolsAmount; i++) {

            Articol articol = Liste.getArticol(i);
            String typeName = articol.tip ? "Antrenament" : "Plan alimentar";
            categories.add(typeName);
            int layoutX = 50;
            if (occurrences % 3 == 1) {
                layoutX = 270;
            } else if (occurrences % 3 == 2) {
                layoutX = 480;
            }

            if (filter.equals("none")
                    || filter.equals(typeName)
                    || articol.nume.toLowerCase().contains(filter.toLowerCase())) {

                appendTemplate(new ArticolCardUser(articol, this), layoutX, layoutY);
                layoutY += (occurrences + 1) % 3 != 0 ? 0 : 270;
                occurrences++;
            }
        }
    }

    @FXML
    public void renderAll() throws IOException, JSONException {
        renderView("none");
    }

    @FXML
    public void initialize() throws IOException, JSONException {
        renderView("Plan alimentar");

        categoryBox.setItems(FXCollections.observableArrayList(categories));
        categoryBox.getSelectionModel().selectFirst();
    }
}
