package ap.controllers;

import ap.controllers.Services.API_Handler;
import ap.controllers.Services.Articol;
import ap.controllers.Services.Intrebare;
import ap.controllers.Services.Liste;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;

public class AdminDashboard {
    public Text lastModifiedSystem;
    @FXML
    private AnchorPane anchorRoot;
    @FXML
    private Text totalArticles;
    @FXML
    private Text totalNA;
    @FXML
    private Text lastModifiedComponent;
    @FXML
    private Text totalQA;
    @FXML
    private Text totalUtilizatori;
    @FXML
    private VBox recentFilesVbox;
    @FXML
    private Pane wrapper;
    public StringBuffer response = new StringBuffer();
    public AdminDashboard() throws IOException, JSONException {
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
                        record.getString("raspuns")));
            }
        }
    }

    @FXML
    public void initialize() throws IOException {
        int articoleAmount = Liste.getArticlesAmount();
        int intrebariAmount = Liste.getintrebareAmount();
        int nintrebariAmount = Liste.getNIntrebariAmount();
        StringBuffer userAmount = API_Handler.getRecords("people/count.php","");
        totalQA.setText(String.valueOf(intrebariAmount));
        totalArticles.setText(String.valueOf(articoleAmount));
        totalNA.setText(String.valueOf(nintrebariAmount));
        totalUtilizatori.setText(userAmount.toString());
        System.out.println(userAmount.toString());
    }
}
