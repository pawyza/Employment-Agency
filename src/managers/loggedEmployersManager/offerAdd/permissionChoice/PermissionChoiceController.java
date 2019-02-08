package managers.loggedEmployersManager.offerAdd.permissionChoice;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import managers.loggedEmployeesManager.addEmployee.formerPositionAdding.FormerPositionsAddingController;
import managers.loggedEmployersManager.offerAdd.ChoiceBoxContainer;
import managers.loggedEmployersManager.offerAdd.NewOfferObject;
import startPack.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PermissionChoiceController implements Initializable {

    NewOfferObject newOfferObject;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    new ChoiceBoxContainer().choicePermission(choicePermission);
    }

    public void prepare(NewOfferObject newOfferObject){
        this.newOfferObject = newOfferObject;
    }

    @FXML
    private ChoiceBox<String> choicePermission;

    @FXML
    private TableView<String> tabPermissions;

    @FXML
    private TableColumn<String, String> colPermission;

    @FXML
    void onDelete(ActionEvent event) {
        try {
            permissionsTable.updateRemove(tabPermissions.getSelectionModel().getSelectedItem());
        }catch (RuntimeException e){
            System.out.println("Brak wybranego elementu");
        }
    }

    @FXML
    void next(ActionEvent event) {
        try {
            newOfferObject.setPermissions(languagesTable.getAdditionalLanguages());
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/managers/loggedEmployeesManager/addEmployee/formerPositionAdding/formerPositionsAddingPane.fxml"));
            Parent root = fxmlLoader.load();
            FormerPositionsAddingController formerPositionsAddingController = fxmlLoader.getController();
            formerPositionsAddingController .setDataContainer(dataContainer);
            formerPositionsAddingController.prepare();
            Main.stage.setScene(new Scene(root));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
