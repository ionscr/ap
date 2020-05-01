package ap.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Inregistrare {
    @FXML
    public TextField nume;
    public TextField nr_tel;
    public Label hidden_nume;
    public Label hidden_nr;

    public Inregistrare() {

    }

    public boolean checkNume() {
        hidden_nume.setVisible(false);
        nume.getStyleClass().remove("red-border");
        if (nume.getText().matches("^[A-Za-z ]+$")) return true;
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

    public void buttonclick() {
        if(checkNr() && checkNume()){

        }
    }
}
