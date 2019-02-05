package managers.loggedInformationManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import managers.LoggedUser;
import managers.OutLogger;
import startPack.Main;

import java.io.IOException;
import java.sql.Connection;

public class InformationManagerController extends LoggedUser {
    @FXML
    void addCountryClicked(ActionEvent event) {

    }

    @FXML
    void addCurencyClicked(ActionEvent event) {

    }

    @FXML
    void addLanguageClicked(ActionEvent event) {

    }

    @FXML
    void addOffersDetailClicked(ActionEvent event) {

    }

    @FXML
    void addPermissionClicked(ActionEvent event) {

    }

    @FXML
    void addPositionClicked(ActionEvent event) {

    }

    @FXML
    void addTradeClicked(ActionEvent event) {

    }

    @FXML
    void addTypeOfContractClicked(ActionEvent event) {

    }

    @FXML
    void onLogOut(ActionEvent event) {
        new OutLogger().LogOut();
    }
}
