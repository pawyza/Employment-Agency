package managers.loggedEmployeesManager.browseEmployees;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import managers.Switcher;
import managers.loggedEmployeesManager.browseEmployees.fullInformation.EmployeeViewController;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class BrowseEmployeesController implements Initializable {

    BrowseEmployeeTable browseEmployeeTable;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            browseEmployeeTable = new BrowseEmployeeTable(tabEmployees,colName,colForname,colCoutry,colLanguages,colPermission,colFormerPostition);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private Text txtMessage;

    @FXML
    private TableView<EmployeeObject> tabEmployees;

    @FXML
    private TableColumn<EmployeeObject, String> colName;

    @FXML
    private TableColumn<EmployeeObject, String> colForname;

    @FXML
    private TableColumn<EmployeeObject, String> colCoutry;

    @FXML
    private TableColumn<EmployeeObject, String> colLanguages;

    @FXML
    private TableColumn<EmployeeObject, String> colPermission;

    @FXML
    private TableColumn<EmployeeObject, String> colFormerPostition;


    @FXML
    void onDelete(ActionEvent event) {
        try {
            browseEmployeeTable.delete(tabEmployees.getSelectionModel().getSelectedIndex());
            txtMessage.setText("");
        } catch (SQLException e) {
            txtMessage.setText("Employee hasn't been deleted");
        }
    }

    @FXML
    void onFindBy(ActionEvent event) {

    }

    @FXML
    void onFitTo(ActionEvent event) {

    }

    @FXML
    void onFullInformation(ActionEvent event) {
        try {
            Stage viewStage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/managers/loggedEmployeesManager/browseEmployees/fullInformation/employeeView.fxml"));
            Parent root = fxmlLoader.load();
            EmployeeViewController employeeViewController = fxmlLoader.getController();
            employeeViewController.prepare(browseEmployeeTable.getObjectObservableList().get(tabEmployees.getSelectionModel().getSelectedIndex()));
            viewStage.setTitle("Employee information");
            viewStage.setScene(new Scene(root, 600, 400));
            viewStage.show();
            txtMessage.setText("");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            e.printStackTrace();
            txtMessage.setText("Error while creating view.");
        }
    }

    @FXML
    void onReturn(ActionEvent event) {
        new Switcher().change("/managers/loggedEmployeesManager/employeesManagerPanel.fxml");
    }
}
