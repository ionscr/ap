package ap.controllers;

import ap.controllers.Services.Articol;
import ap.controllers.Services.DialogHelper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class ArticolCardUser extends Articol{
    @FXML
    private Pane paneContainer;

    @FXML
    private Pane imagePane;

    @FXML
    private Pane textPane;

    @FXML
    private Text productName;

    Articol articol = null;
    ArticlesUser articlesController = null;
    public ArticolCardUser(Articol articol, ArticlesUser articlesController) {
        super(articol);
        this.articol = articol;
        this.articlesController = articlesController;
    }

    @FXML
    public Pane initialize() {
        productName.setText(nume);
        imagePane.setStyle(String.format("-fx-background-image: url(%s);", poza));
        paneContainer.setOnMouseClicked(mouseEvent -> {
            ArticolDialogUser articolDialogController =
                    new ArticolDialogUser(articol, articlesController);
            FXMLLoader loader =
                    new FXMLLoader(getClass().getResource("/articol_dialog_user.fxml"));
            loader.setController(articolDialogController);
            DialogHelper.loadDialog(mouseEvent, loader);
        });
        return paneContainer;
    }
}
