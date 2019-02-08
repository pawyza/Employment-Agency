package managers.loggedEmployersManager.offerAdd.languageChoice;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import managers.loggedEmployersManager.offerAdd.ChoiceBoxContainer;
import managers.loggedEmployersManager.offerAdd.NewOfferObject;

import java.net.URL;
import java.util.ResourceBundle;

public class LanguagesChoiceController implements Initializable {

    NewOfferObject newOfferObject;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        new ChoiceBoxContainer().choiceLanguages(choiceLanguage);
    }

    public void prepare(NewOfferObject newOfferObject){
        this.newOfferObject = newOfferObject;
    }

    @FXML
    private ChoiceBox<String> choiceLanguage;

    @FXML
    private TableView<String> tabLanguage;

    @FXML
    private TableColumn<String, String> colLanguage;

    @FXML
    void next(ActionEvent event) {

    }

    @FXML
    void onDelete(ActionEvent event) {

    }
}