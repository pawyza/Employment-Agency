package managers.loggedEmployersManager.offerAdd.languageChoice;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import managers.loggedEmployersManager.offerAdd.ChoiceBoxContainer;
import managers.loggedEmployersManager.offerAdd.NewOfferObject;
import managers.loggedEmployersManager.offerAdd.UniversalTableController;
import managers.loggedEmployersManager.offerAdd.detailsChoice.DetailsChoiceController;
import managers.loggedEmployersManager.offerAdd.permissionChoice.PermissionChoiceController;
import managers.loggedInformationManager.Permission.PermissionController;
import startPack.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LanguagesChoiceController implements Initializable {

    NewOfferObject newOfferObject;
    UniversalTableController languagesTable;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        new ChoiceBoxContainer().choiceLanguages(choiceLanguage);
        languagesTable = new UniversalTableController(tabLanguage,colLanguage);
        choiceLanguage.getSelectionModel().selectedItemProperty().addListener((observableValue, number, t1) -> {
            languagesTable.add(choiceLanguage.getValue());
        });
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
    void onDelete(ActionEvent event) {
        try {
            languagesTable.delete(tabLanguage.getSelectionModel().getSelectedItem());
        }catch (RuntimeException e){
            System.out.println("Brak wybranego elementu");
        }
    }

    @FXML
    void next(ActionEvent event) {
        try {
            newOfferObject.setLanguages(languagesTable.getObservable());
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/managers/loggedEmployersManager/offerAdd/permissionChoice/permissionChoicePane.fxml"));
            Parent root = fxmlLoader.load();
            PermissionChoiceController permissionChoiceController = fxmlLoader.getController();
            permissionChoiceController.prepare(newOfferObject);
            Main.stage.setScene(new Scene(root));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}