package managers.loggedInformationManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import managers.LoggedUser;
import managers.Switcher;

public class InformationManagerController extends LoggedUser {
    @FXML
    void addCountryClicked(ActionEvent event) {
        new Switcher().change("/managers/loggedInformationManager/Country/countryPanel.fxml");
    }

    @FXML
    void addCurrencyClicked(ActionEvent event) {
        new Switcher().change("/managers/loggedInformationManager/Currency/currencyPanel.fxml");
    }

    @FXML
    void addLanguageClicked(ActionEvent event) {
        new Switcher().change("/managers/loggedInformationManager/Language/languagePanel.fxml");
    }

    @FXML
    void addOffersDetailClicked(ActionEvent event) {
        new Switcher().change("/managers/loggedInformationManager/Details/detailPanel.fxml");
    }

    @FXML
    void addPermissionClicked(ActionEvent event) {
        new Switcher().change("/managers/loggedInformationManager/Permission/permissionPanel.fxml");
    }

    @FXML
    void addPositionClicked(ActionEvent event) {
        new Switcher().change("/managers/loggedInformationManager/Position/positionPanel.fxml");
    }

    @FXML
    void addTradeClicked(ActionEvent event) {
        new Switcher().change("/managers/loggedInformationManager/Trade/tradePanel.fxml");
    }

    @FXML
    void addTypeOfContractClicked(ActionEvent event) {
        new Switcher().change("/managers/loggedInformationManager/Contract/contractPanel.fxml");
    }

    @FXML
    void onLogOut(ActionEvent event) {
        new Switcher().logOut();
    }
}
