package managers.offerBrowser.extended;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import managers.offerBrowser.BrowseOffersTable;
import managers.offerBrowser.OfferObject;
import managers.offerBrowser.basic.BrowseOffersController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import managers.Switcher;
import managers.loggedEmployeesManager.browseEmployees.BrowseEmployeesController;
import managers.offerBrowser.fullInformation.OfferViewController;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class BrowseOffersExtendedController implements Initializable {

    private BrowseOffersTable browseOffersTable;

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
    void onFullInformation(ActionEvent event) {
        try {
            Stage viewStage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/managers/offerBrowser/fullInformation/offerView.fxml"));
            Parent root = fxmlLoader.load();
            OfferViewController offerViewController = fxmlLoader.getController();
            offerViewController.prepare(browseOffersTable.getObjectObservableList().get(tabOffers.getSelectionModel().getSelectedIndex()));
            viewStage.setTitle("Offer information");
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
        new Switcher().change("/managers/loggedEmployersManager/employersManagerPanel.fxml");
    }

    @FXML
    void onDelete(ActionEvent event) {
        try {
            browseOffersTable.delete(tabOffers.getSelectionModel().getSelectedIndex());
            txtMessage.setText("");
        } catch (SQLException e) {
            txtMessage.setText("Offer hasn't been deleted");
        }
    }
}
