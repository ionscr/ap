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

public class QACardUser extends Intrebare {
    @FXML
    private Text text_intrebare;
    @FXML
    private Text text_raspuns;
    @FXML
    private Text user_name;
    Intrebare intrebare = null;
    QAUser QAController = null;

    public QACardUser(Intrebare intrebare, QAUser QAController) {
        super(intrebare);
        this.intrebare = intrebare;
        this.QAController = QAController;
    }

    @FXML
    public void initialize() {
        text_intrebare.setText(text);
        text_raspuns.setText(raspuns);
        user_name.setText(nume);
    }
}