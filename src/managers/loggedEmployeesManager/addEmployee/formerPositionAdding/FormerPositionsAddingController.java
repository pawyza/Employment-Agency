package managers.loggedEmployeesManager.addEmployee.formerPositionAdding;

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
import managers.loggedEmployeesManager.addEmployee.permissionAdding.PermissionsAddingController;
import startPack.Main;

import java.io.IOException;
import java.sql.Connection;

public class FormerPositionsAddingController {
    private Connection connection;

    private DataContainer dataContainer;
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void setDataContainer(DataContainer dataContainer) {
        this.dataContainer = dataContainer;
    }

    @FXML
    private ChoiceBox<?> choiceTrade;

    @FXML
    private ChoiceBox<?> choicePosition;

    @FXML
    private Button btnDelete;

    @FXML
    private TableView<?> tabPosition;

    @FXML
    private TableColumn<?, ?> colPosition;

    @FXML
    private TableColumn<?, ?> colTrade;

    @FXML
    void next(ActionEvent event) {
        try {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/managers/loggedEmployeesManager/addEmployee/permissionAdding/permissionsAddingPane.fxml"));
        Parent root = fxmlLoader.load();
        PermissionsAddingController permissionsAddingController = fxmlLoader.getController();
        permissionsAddingController.setConnection(connection);
        permissionsAddingController.setDataContainer(dataContainer);
        Main.stage.setScene(new Scene(root));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FXML
    void onDelete(ActionEvent event) {

    }

}
