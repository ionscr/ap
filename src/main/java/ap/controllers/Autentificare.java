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

import javax.swing.*;
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
    private String makeRequest() throws IOException {
        final String POST_PARAMS = "{\n" +
                "    \"user\": \"" + username.getText() + "\",\r\n" +
                "    \"password\": \"" + password.getText() + "\"\n}";
        //System.out.println(POST_PARAMS);
        URL url = new URL(API+"/login/");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);
        OutputStream os = conn.getOutputStream();
        os.write(POST_PARAMS.getBytes());
        os.flush();
        os.close();
        int responseCode = conn.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) { //success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    conn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        } else {
            return "";
        }
    }
    public int verifica() throws IOException, JSONException {
        hidden_autentificare.setVisible(false);
        String rs = makeRequest();
        System.out.printf(rs);
        int rol;
        Boolean k;
        try {
            JSONObject jsonObject = new JSONObject(rs);
            if(jsonObject.getBoolean("ok")){
                if(jsonObject.getString("rol").equals("1")) return 2;
                else return 1;
            }
            else{
                hidden_autentificare.setVisible(true);
                return 0;
            }
        }catch (JSONException err){
            hidden_autentificare.setVisible(true);
            System.out.printf("Error", err.toString());
        }
        return 0;
    }
    public void buttonclick(ActionEvent event) throws JSONException, IOException {
        int auth = verifica();
        if(auth == 1){
            System.out.println("client");
        }
        if(auth == 2) {
            Parent home_page_parent = FXMLLoader.load(getClass().getResource("/admin.fxml"));
            Scene home_page_scene = new Scene(home_page_parent);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.hide(); //optional
            app_stage.setScene(home_page_scene);
            app_stage.setTitle("Pagina de administrare");
            app_stage.show();
        }
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
