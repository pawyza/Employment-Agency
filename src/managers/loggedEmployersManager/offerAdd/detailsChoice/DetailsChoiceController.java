package managers.loggedEmployersManager.offerAdd.detailsChoice;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import managers.loggedEmployersManager.offerAdd.ChoiceBoxContainer;
import managers.loggedEmployersManager.offerAdd.NewOfferObject;
import managers.loggedEmployersManager.offerAdd.UniversalTableController;
import managers.loggedEmployersManager.offerAdd.resultView.OfferAddingResultController;
import startPack.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DetailsChoiceController  implements Initializable {

    NewOfferObject newOfferObject;
    UniversalTableController detailsTable;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        new ChoiceBoxContainer().choiceDetails(choiceDetail);
        detailsTable = new UniversalTableController(tabDetails,colDetail);
        choiceDetail.getSelectionModel().selectedItemProperty().addListener((observableValue, number, t1) -> {
            detailsTable.add(choiceDetail.getValue());
        });
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
        try {
            detailsTable.delete(tabDetails.getSelectionModel().getSelectedItem());
        }catch (RuntimeException e){
            System.out.println("Brak wybranego elementu");
        }
    }

    @FXML
    void show(ActionEvent event) {
        try {
            newOfferObject.setDetails(detailsTable.getObservable());
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/managers/loggedEmployersManager/offerAdd/resultView/offerAddingResult.fxml"));
            Parent root = fxmlLoader.load();
            OfferAddingResultController offerAddingResultController = fxmlLoader.getController();
            offerAddingResultController.prepare(newOfferObject);
            Main.stage.setScene(new Scene(root));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}