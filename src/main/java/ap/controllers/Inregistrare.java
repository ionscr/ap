package ap.controllers;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;
public class Inregistrare {
    @FXML
    public Button b_inregistrare;
    public TextField nume;
    public TextField nr_tel;
    public TextField username;
    public PasswordField password;
    public TextField adresa;
    public RadioButton client;
    public RadioButton admin;
    public Label hidden_nume;
    public Label hidden_nr;
    public Label hidden_user;
    public Label hidden_all;
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
    public void parseRecords() throws JSONException {
        JSONArray jsonArray = new JSONArray(jsonStr.toString());
        int i;
        for (i = 0; i < jsonArray.length(); i++) {
            JSONObject objectInArray = jsonArray.getJSONObject(i);
            int id = objectInArray.getInt("id");
            String nume = objectInArray.getString("nume");
            String adresa = objectInArray.getString("adresa");
            String nr_tel = objectInArray.getString("nr_tel");
            String user = objectInArray.getString("user");
            String password = objectInArray.getString("password");
            int rol = objectInArray.getInt("rol");
            System.out.println(nume+" "+adresa+" "+nr_tel+" "+user+" "+password+" "+rol);
        }
    }
    public Inregistrare() throws IOException {
    }
    public boolean checkNume() {
        hidden_nume.setVisible(false);
        nume.getStyleClass().remove("red-border");
        if (nume.getText().matches("^[A-Za-z ]{3,30}$")) return true;
        else {
            hidden_nume.setVisible(true);
            nume.getStyleClass().add("red-border");
            return false;
        }
    }
    public boolean checkNr() {
        hidden_nr.setVisible(false);
        nr_tel.getStyleClass().remove("red-border");
        if (nr_tel.getText().matches("[0-9]+")) return true;
        else {
            hidden_nr.setVisible(true);
            nr_tel.getStyleClass().add("red-border");
            return false;
        }
    }
    public boolean checkAll() {
        hidden_all.setVisible(false);
        if (username.getLength() != 0 && adresa.getLength() != 0 && password.getLength()!=0)
            return true;
        else {
            hidden_all.setVisible(true);
            return false;
        }
    }
    public boolean verifica() throws IOException, JSONException {
        hidden_user.setVisible(false);
        username.getStyleClass().remove("red-border");
        getRecords(API, "?nume="+username.getText());
        if(jsonStr.length() < 4 ) return true;
        else {
            hidden_user.setVisible(true);
            username.getStyleClass().add("red-border");
            return false;
        }

    }
    private void makeRequest() throws IOException {
        int rl =admin.isSelected() ? 1:0;
        final String POST_PARAMS = "{\n" +
                "    \"nume\": \"" + nume.getText() + "\",\r\n" +
                "    \"adresa\": \"" + adresa.getText() + "\",\r\n" +
                "    \"nr_tel\": \"" + nr_tel.getText() + "\",\r\n" +
                "    \"user\": \"" + username.getText() + "\",\r\n" +
                "    \"password\": \"" + password.getText() + "\",\r\n" +
                "    \"rol\": " + rl + "\n}";
        System.out.println(POST_PARAMS);
        URL url = new URL(API);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("PUT");
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
            System.out.println(response.toString());
        } else {
            System.out.println(" DIDNT WORK");
        }
    }
    public void buttonclick() throws JSONException, IOException {
        if(checkNr() && checkNume() && checkAll()) {
            if(verifica()) makeRequest();
        }
    }
}
