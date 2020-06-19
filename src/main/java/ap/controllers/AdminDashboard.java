package ap.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
    private Text lastModifiedComponent;


    @FXML
    private Text totalQA;

    @FXML
    private VBox recentFilesVbox;
    @FXML
    private Pane wrapper;

    public StringBuffer response = new StringBuffer();
    private int recordLimit = 3;

    public HomeController() throws IOException, JSONException {
        if (ProductsLists.getComponentsAmount() == 0) {
            response = APIHandler.getRecords("components", "");
            JSONArray jsonArray = new JSONArray(response.toString());

            int recordAmount = jsonArray.length();
            for (int i = 0; i < recordAmount; i++) {
                JSONObject record = jsonArray.getJSONObject(i);
                ProductsLists.push(new Component(
                        record.getInt("id"),
                        record.getInt("categoryId"),
                        record.getString("categoryName"),
                        record.getString("name"),
                        record.getInt("amount"),
                        record.getInt("price"),
                        record.getBoolean("paid"),
                        record.getString("date"),
                        record.getString("image"),
                        record.getString("provider"),
                        record.getBoolean("delivered"),
                        record.getString("comments"),
                        record.getInt("complaints")));
            }
        }
        if (ProductsLists.getSystemsAmount() == 0) {
            response = APIHandler.getRecords("systems", "");
            JSONArray jsonArray = new JSONArray(response.toString());
            int recordAmount = jsonArray.length();
            for (int i = 0; i < recordAmount; i++) {
                JSONObject record = jsonArray.getJSONObject(i);
                ProductsLists.push(new Systems(
                        record.getInt("id"),
                        record.getInt("categoryId"),
                        record.getString("categoryName"),
                        record.getString("name"),
                        record.getInt("amount"),
                        record.getInt("price"),
                        record.getBoolean("paid"),
                        record.getString("date"),
                        record.getString("image"),
                        record.getInt("orders"),
                        record.getInt("delivers"),
                        record.getInt("warranty"),
                        record.getString("categoryParent"),
                        record.getString("components")));
            }
        }
        if (ProductsLists.getPromotionsAmount() == 0) {
            response = APIHandler.getRecords("promotions", "");
            JSONArray jsonArray = new JSONArray(response.toString());

            int recordAmount = jsonArray.length();
            for (int i = 0; i < recordAmount; i++) {
                JSONObject record = jsonArray.getJSONObject(i);
                ProductsLists.push(new Promotion(
                        record.getInt("id"),
                        record.getInt("system_id"),
                        record.getString("category"),
                        record.getString("name"),
                        record.getInt("amount"),
                        record.getString("provider"),
                        record.getString("image")));
            }
        }
    }

    @FXML
    public void initialize() throws IOException {
        recentFilesVbox.setSpacing(20);
        RecentItemsController recentItems;

        int componentsAmount = ProductsLists.getComponentsAmount();
        int systemsAmount = ProductsLists.getSystemsAmount();
        int latest = componentsAmount - (recordLimit + 1);

        for (int i = 0; i < componentsAmount; i++) {

            if (i > latest) {
                recentItems = new RecentItemsController(
                        ProductsLists.getSystems(systemsAmount - 1),
                        ProductsLists.getComponent(i)
                );
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/home/recentItems/RecentItems.fxml"));
                loader.setController(recentItems);

                Pane recentPane = loader.load();
                recentFilesVbox.getChildren().addAll(recentPane);
            }
        }
        totalQA.setText(String.valueOf(componentsAmount));
        lastModifiedComponent.setText("Ultima modificare: \n" + ProductsLists.getComponent(componentsAmount - 1).date);

        totalArticles.setText(String.valueOf(systemsAmount));
        lastModifiedSystem.setText("Ultima modificare: \n" + ProductsLists.getSystems(systemsAmount - 1).date);
    }
}
