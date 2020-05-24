package ap.controllers;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import javafx.event.ActionEvent;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;
public class Autentificare {
    @FXML
    public Button b_inregistrare;
    public TextField username;
    public PasswordField password;
    public Label hidden_autentificare;
    public StringBuffer jsonStr = new StringBuffer();
    public String API = "https://tonu.rocks/school/AP/api/people";

    public void getRecords(String api, String query) throws IOException {
        URL url = new URL(api+query);
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        String line;
        jsonStr = new StringBuffer();
        while ((line = reader.readLine()) != null) {
            jsonStr.append(line);
        }
        reader.close();
    }
    public boolean verifica() throws IOException, JSONException {
        hidden_autentificare.setVisible(false);
        getRecords(API, "?nume="+username.getText());
        if(jsonStr.length() < 4 ) return true;
        else {
            hidden_autentificare.setVisible(true);
            return false;
        }

    }
    public void buttonclick() throws JSONException, IOException {

    }
    public void goInregistrare(ActionEvent event) throws IOException{
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("/inregistrare.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide(); //optional
        app_stage.setScene(home_page_scene);
        app_stage.setTitle("Inregistrare");
        app_stage.show();
    }
}
