package managers.loggedEmployersManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import managers.LoggedUser;
import managers.Switcher;
import startPack.Main;

import java.io.IOException;

public class EmployersManagerController extends LoggedUser {

    @FXML
    void addEmployerClicked(ActionEvent event) {
        new Switcher().change("/managers/loggedEmployersManager/employerAdd/employerAddPane.fxml");
    }

    @FXML
    void addOfferClicked(ActionEvent event) {
        new Switcher().change("/managers/loggedEmployersManager/offerAdd/offerAddPane.fxml");
    }

    @FXML
    void browseEmployersClicked(ActionEvent event) {
        new Switcher().change("/managers/loggedEmployersManager/employersBrowser/employerBrowsePanel.fxml");
    }

    @FXML
    void browseOffersClicked(ActionEvent event) {
        new Switcher().change("/managers/offerBrowser/extended/browseOffersPane.fxml");
    }

    @FXML
    void onLogOut(ActionEvent event) {
        new Switcher().logOut();
    }
}
