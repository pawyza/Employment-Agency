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
import managers.loggedEmployersManager.offerAdd.ChoiceBoxContainer;
import managers.loggedEmployersManager.offerAdd.NewOfferObject;
import managers.loggedEmployersManager.offerAdd.UniversalTableController;
import managers.loggedEmployersManager.offerAdd.detailsChoice.DetailsChoiceController;
import startPack.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PermissionChoiceController implements Initializable {

    private NewOfferObject newOfferObject;
    private UniversalTableController permissionsTable;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    new ChoiceBoxContainer().choicePermission(choicePermission);
    permissionsTable = new UniversalTableController(tabPermissions,colPermission);
        choicePermission.getSelectionModel().selectedItemProperty().addListener((observableValue, number, t1) -> {
            permissionsTable.add(choicePermission.getValue());
        });
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
            permissionsTable.delete(tabPermissions.getSelectionModel().getSelectedItem());
        }catch (RuntimeException e){
            System.out.println("Brak wybranego elementu");
        }
    }

    @FXML
    void next(ActionEvent event) {
        try {
            newOfferObject.setPermissions(permissionsTable.getObservable());
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/managers/loggedEmployersManager/offerAdd/detailsChoice/detailsChoicePane.fxml"));
            Parent root = fxmlLoader.load();
            DetailsChoiceController detailsChoiceController = fxmlLoader.getController();
            detailsChoiceController.prepare(newOfferObject);
            Main.stage.setScene(new Scene(root));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
