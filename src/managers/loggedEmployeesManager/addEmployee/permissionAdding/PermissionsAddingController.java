package managers.loggedEmployeesManager.addEmployee.permissionAdding;

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
    private Connection connection;

    private DataContainer dataContainer;
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void setDataContainer(DataContainer dataContainer) {
        this.dataContainer = dataContainer;
    }
    @FXML
    private ChoiceBox<?> choiceLanguage;

    @FXML
    private Button btnDelete;

    @FXML
    private TableView<?> tabPermissions;

    @FXML
    private TableColumn<?, ?> colPermission;

    @FXML
    void show(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/managers/loggedEmployeesManager/addEmployee/result/addingResult.fxml"));
            Parent root = fxmlLoader.load();
            AddingResultController addingResultController = fxmlLoader.getController();
            addingResultController.setConnection(connection);
            addingResultController.setDataContainer(dataContainer);
            Main.stage.setScene(new Scene(root));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FXML
    void onDelete(ActionEvent event) {

    }
}
