package managers.loggedEmployeesManager.addEmployee;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import startPack.Main;

import java.io.IOException;
import java.sql.Connection;

public class AdditionalLanguagesAddingController {
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
    private TableView<?> tabLanguage;

    @FXML
    private TableColumn<?, ?> colInformation;

    @FXML
    private TableColumn<?, ?> colLanguage;

    @FXML
    void next(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/managers/loggedEmployeesManager/addEmployee/formerPositionsAddingPane.fxml"));
            Parent root = fxmlLoader.load();
            FormerPositionsAddingController formerPositionsAddingController = fxmlLoader.getController();
            formerPositionsAddingController .setConnection(connection);
            formerPositionsAddingController .setDataContainer(dataContainer);
            Main.stage.setScene(new Scene(root));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FXML
    void onDelete(ActionEvent event) {

    }

}
