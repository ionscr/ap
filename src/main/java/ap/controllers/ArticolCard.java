package ap.controllers;

import ap.controllers.Services.Articol;
import ap.controllers.Services.DialogHelper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import java.util.Set;
public class ArticolCard extends Articol{
    @FXML
    private Pane paneContainer;

    @FXML
    private Pane imagePane;

    @FXML
    private Pane textPane;

    @FXML
    private Text productName;
    
    Articol articol = null;
    Articles articlesController = null;
    public ArticolCard(Articol articol, Articles articlesController) {
        super(articol);
        this.articol = articol;
        this.articlesController = articlesController;
    }

    @FXML
    public Pane initialize() {
        productName.setText(nume);
        imagePane.setStyle(String.format("-fx-background-image: url(%s);", poza));
        paneContainer.setOnMouseClicked(mouseEvent -> {
            ArticolDialog articolDialogController =
                    new ArticolDialog(articol, articlesController);
            FXMLLoader loader =
                    new FXMLLoader(getClass().getResource("/articol_dialog.fxml"));
            loader.setController(articolDialogController);
            DialogHelper.loadDialog(mouseEvent, loader);
        });
        return paneContainer;
    }
}
