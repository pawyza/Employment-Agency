package managers.loggedEmployeesManager.addEmployee.permissionAdding;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import managers.loggedEmployeesManager.addEmployee.DataContainer;
import managers.loggedEmployeesManager.addEmployee.result.AddingResultController;
import startPack.Main;

import java.io.IOException;
import java.sql.Connection;

public class PermissionsAddingController {
    private DataContainer dataContainer;
    private PermissionsTable permissionsTable;

    public void setDataContainer(DataContainer dataContainer) {
        this.dataContainer = dataContainer;
    }

    public void prepare(){
        choicePermission.setItems(FXCollections.observableList(dataContainer.getPermissions()));
        permissionsTable = new PermissionsTable(tabPermissions,colPermission);
        choicePermission.getSelectionModel().selectedItemProperty().addListener((observableValue, number, t1) -> {
            permissionsTable.updateAdd(choicePermission.getValue());
        });
    }

    @FXML
    private ChoiceBox<String> choicePermission;

    @FXML
    private TableView<String> tabPermissions;

    @FXML
    private TableColumn<String,String> colPermission;

    @FXML
    void show(ActionEvent event) {
        try {
            dataContainer.setEmployeePermissions(permissionsTable.getPermissions());
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/managers/loggedEmployeesManager/addEmployee/result/addingResult.fxml"));
            Parent root = fxmlLoader.load();
            AddingResultController addingResultController = fxmlLoader.getController();
            addingResultController.setDataContainer(dataContainer);
            addingResultController.prepare();
            Main.stage.setScene(new Scene(root));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FXML
    void onDelete(ActionEvent event) {
        try {
        permissionsTable.updateRemove(tabPermissions.getSelectionModel().getSelectedItem());
        }catch (RuntimeException e){
        System.out.println("Brak wybranego elementu");
    }
    }

}
