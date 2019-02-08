package managers.loggedEmployersManager.offerAdd.detailsChoice;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import managers.loggedEmployersManager.offerAdd.ChoiceBoxContainer;
import managers.loggedEmployersManager.offerAdd.NewOfferObject;

import java.net.URL;
import java.util.ResourceBundle;

public class DetailsChoiceController  implements Initializable {

    NewOfferObject newOfferObject;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        new ChoiceBoxContainer().choiceDetails(choiceDetail);
    }

    public void prepare(NewOfferObject newOfferObject){
        this.newOfferObject = newOfferObject;
    }

    @FXML
    private ChoiceBox<String> choiceDetail;

    @FXML
    private TableView<String> tabDetails;

    @FXML
    private TableColumn<String, String> colDetail;

    @FXML
    void onDelete(ActionEvent event) {

    }

    @FXML
    void show(ActionEvent event) {

    }

}