package ap.controllers;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
public class User {
    public Pane navBar;
    @FXML
    private BorderPane borderPane;

    @FXML
    void loadAddQuestionScene(MouseEvent event) {
        loadPage("/add_question.fxml", event);
    }

    @FXML
    void loadArticlesScene(MouseEvent event) {
        loadPage("/articles_user.fxml", event);
    }

    @FXML
    void loadQAScene(MouseEvent event) {
        loadPage("/qa_user.fxml", event);
    }

    @FXML
    void initialize() {
        Parent rootNode = null;
        try {
            rootNode = FXMLLoader.load(getClass().getResource("/articles_user.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        borderPane.setCenter(rootNode);
    }

    void loadPage(String scene, MouseEvent event) {

        for (Node btn : navBar.getChildren()) {
            btn.getStyleClass().remove("active");
        }

        ((Node) event.getSource()).getStyleClass().add("active");

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(scene));
        } catch (IOException ex) {
        }
        borderPane.setCenter(root);
    }
}
