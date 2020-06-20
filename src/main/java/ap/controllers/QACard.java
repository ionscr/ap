package ap.controllers;

import ap.controllers.Services.API_Handler;
import ap.controllers.Services.Articol;
import ap.controllers.Services.Intrebare;
import ap.controllers.Services.Liste;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import org.json.JSONException;

import java.io.IOException;

public class QACard extends Intrebare {
    @FXML
    private Text text_intrebare;
    @FXML
    private TextArea text_raspuns;
    @FXML
    private Button saveBtn;
    @FXML
    private Text user_name;
    Intrebare intrebare = null;
    QA QAController = null;

    public QACard(Intrebare intrebare, QA QAController) {
        super(intrebare);
        this.intrebare = intrebare;
        this.QAController = QAController;
    }

    @FXML
    public void initialize() {
        text_intrebare.setText(text);
        text_raspuns.setText(raspuns);
        user_name.setText(nume);
        saveBtn.setOnMouseClicked(mouseEvent -> {
            Intrebare updatedIntrebare = new Intrebare(this);
            updatedIntrebare.raspuns = text_raspuns.getText();
            final String UPDATE_PARAMS = "{\n" +
                    "    \"id\": " + id + ",\r\n" +
                    "    \"raspuns\": \"" + updatedIntrebare.raspuns + "\"\n}";
            try {
                API_Handler.makeRequest("UPDATE", "questions", UPDATE_PARAMS);
                if (Liste.updateIntrebare(updatedIntrebare) != 0) {
                    QAController.renderAll();
                } else {
                    System.out.println("Was unable to update");
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        });

    }
}