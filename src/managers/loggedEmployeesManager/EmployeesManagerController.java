package managers.loggedEmployeesManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import managers.LoggedUser;
import managers.loggedEmployeesManager.addEmployee.AddEmployeeController;
import managers.loggedEmployeesManager.browseEmployees.BrowseEmployeesController;
import managers.loggedEmployeesManager.browseOffers.BrowseOffersController;
import managers.loggedEmployeesManager.employ.EmployController;
import startPack.Main;

import java.io.IOException;


public class EmployeesManagerController extends LoggedUser {

    @FXML
    void addEmployeeClicked(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/managers/loggedEmployeesManager/addEmployee/addEmployeePane.fxml"));
            Parent root = fxmlLoader.load();
            AddEmployeeController addEmployeeController = fxmlLoader.getController();
            addEmployeeController.setConnection(super.getConnection());
            addEmployeeController.prepare();
            Main.stage.setScene(new Scene(root));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FXML
    void browseEmployeesClicked(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/managers/loggedEmployeesManager/browseEmployees/browseEmployeesPane.fxml"));
            Parent root = fxmlLoader.load();
            BrowseEmployeesController browseEmployeesController = fxmlLoader.getController();
            browseEmployeesController.setConnection(super.getConnection());
            Main.stage.setScene(new Scene(root));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FXML
    void browseOffersClicked(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/managers/loggedEmployeesManager/browseOffers/BrowseOffersController.fxml"));
            Parent root = fxmlLoader.load();
            BrowseOffersController browseOffersController = fxmlLoader.getController();
            browseOffersController.setConnection(super.getConnection());
            Main.stage.setScene(new Scene(root));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FXML
    void employClicked(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/managers/loggedEmployeesManager/employ/employPane.fxml"));
            Parent root = fxmlLoader.load();
            EmployController employController = fxmlLoader.getController();
            employController.setConnection(super.getConnection());
            Main.stage.setScene(new Scene(root));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }


}
