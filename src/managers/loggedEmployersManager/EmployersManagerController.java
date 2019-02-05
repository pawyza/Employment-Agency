package managers.loggedEmployersManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import managers.LoggedUser;
import managers.Switcher;

public class EmployersManagerController extends LoggedUser {

    @FXML
    void addEmployerClicked(ActionEvent event) {

    }

    @FXML
    void addOfferClicked(ActionEvent event) {

    }

    @FXML
    void browseEmployersClicked(ActionEvent event) {

    }

    @FXML
    void browseOffersClicked(ActionEvent event) {

    }

    @FXML
    void onLogOut(ActionEvent event) {
        new Switcher().logOut();
    }
}
