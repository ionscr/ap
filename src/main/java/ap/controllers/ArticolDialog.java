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

public class ArticolDialog extends Articol {
    @FXML
    private Button updateBtn;
    @FXML
    private Pane deleteBtn;
    @FXML
    private Pane saveBtn;
    @FXML
    private Pane cancelBtn;
    @FXML
    private ComboBox<?> categoryCombo;
    @FXML
    private TextField providerInput;
    @FXML
    private TextField amountInput;
    @FXML
    private TextField nameInput;
    @FXML
    private Pane imageHolder;
    @FXML
    private ComboBox<?> componentsCombo;
    @FXML
    private Pane closeBtn;
    @FXML
    private CheckBox deliveredCheckbox;
    @FXML
    private CheckBox paidCheckbox;
    @FXML
    private TextField priceInput;
    @FXML
    private TextArea commentsInput;
    Articol updatedArticol = null;
    Articles articlesController = null;
    public ArticolDialog(Articol articol, Articles articlesController) {
        super(articol);
        this.articlesController = articlesController;
        this.updatedArticol = articol;
    }

    private void setDisabled(Boolean isDisabled) {
        nameInput.setDisable(isDisabled);
        commentsInput.setDisable(isDisabled);
        saveBtn.setVisible(!isDisabled);
        cancelBtn.setVisible(!isDisabled);
    }

    @FXML
    public void initialize() {
        nameInput.setText(nume);
        commentsInput.setText(descriere);
        imageHolder.setStyle(String.format("-fx-background-image: url(%s);", poza));
        setDisabled(true);

        //modifica
        updateBtn.setOnMouseClicked(mouseEvent -> {
            setDisabled(false);
        });

        //save changes
        saveBtn.setOnMouseClicked(mouseEvent -> {
            setDisabled(true);
            updatedArticol.nume = nameInput.getText();
            updatedArticol.descriere = commentsInput.getText();
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("id", id);
                jsonObject.put("nume", updatedArticol.nume);
                jsonObject.put("descriere", updatedArticol.descriere);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String payload = jsonObject.toString();
            System.out.println(payload);
            try {
                API_Handler.makeRequest("UPDATE", "articles", jsonObject.toString());
                if (Liste.updateArticol(updatedArticol) != 0) {
                    articlesController.renderAll();
                } else {
                    System.out.println("Was unable to update");
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        });
        //cancel btn
        cancelBtn.setOnMouseClicked(mouseEvent -> {
            setDisabled(true);
        });
        //delete btn
        deleteBtn.setOnMouseClicked(mouseEvent -> {
            try {
                String DELETEPARAM = "{\n" +
                        "\"id\": " + id + ",\r\n" +
                        "\"nume\": \"" + nume + "\"\n}";
                API_Handler.makeRequest("DELETE", "articles", DELETEPARAM);

                try {
                    if (Liste.deleteArticol(id) != 0) {
                        articlesController.renderAll();
                    } else {
                        System.out.println("Was unable to delete");
                    }
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            // do what you have to do
            stage.close();
        });
        closeBtn.setOnMouseClicked(mouseEvent -> {
            Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            // do what you have to do
            stage.close();
        });

    }
}
