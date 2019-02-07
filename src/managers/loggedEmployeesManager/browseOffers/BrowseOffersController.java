package managers.loggedEmployeesManager.browseOffers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import managers.Switcher;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class BrowseOffersController implements Initializable {

    BrowseOffersTable browseOffersTable;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            browseOffersTable = new BrowseOffersTable(tabOffers,colID,colPosition,colCompanyName,colCountry,colCity,colVacancy);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private Text txtMessage;

    @FXML
    private TableView<OfferObject> tabOffers;

    @FXML
    private TableColumn<OfferObject, Integer> colID;

    @FXML
    private TableColumn<OfferObject, String> colPosition;

    @FXML
    private TableColumn<OfferObject, String> colCompanyName;

    @FXML
    private TableColumn<OfferObject, String> colCountry;

    @FXML
    private TableColumn<OfferObject, String> colCity;

    @FXML
    private TableColumn<OfferObject, Integer> colVacancy;

    @FXML
    void onFindBy(ActionEvent event) {

    }

    @FXML
    void onFitTo(ActionEvent event) {

    }

    @FXML
    void onFullInformation(ActionEvent event) {

    }

    @FXML
    void onReturn(ActionEvent event) {
        new Switcher().change("/managers/loggedEmployeesManager/employeesManagerPanel.fxml");
    }
}
