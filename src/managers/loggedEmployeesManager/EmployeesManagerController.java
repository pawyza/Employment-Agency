package managers.loggedEmployeesManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import managers.LoggedUser;
import managers.Switcher;
import managers.loggedEmployeesManager.addEmployee.AddEmployeeController;
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
            addEmployeeController.prepare();
            Main.stage.setScene(new Scene(root));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FXML
    void browseEmployeesClicked(ActionEvent event) {
        new Switcher().change("/managers/loggedEmployeesManager/browseEmployees/browseEmployeesPane.fxml");
    }


    @FXML
    void browseOffersClicked(ActionEvent event) {
        new Switcher().change(("/managers/offerBrowser/basic/browseOffersPane.fxml"));

    }

    @FXML
    void onLogOut(ActionEvent event) {
        new Switcher().logOut();
    }
}
