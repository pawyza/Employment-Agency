package managers.offerBrowser.basic;

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
import managers.offerBrowser.BrowseOffersTable;
import managers.offerBrowser.OfferObject;
import managers.offerBrowser.fullInformation.OfferViewController;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class BrowseOffersController implements Initializable {

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
    void onFire(ActionEvent event) {
        fireEmploy(true);
    }

    @FXML
    void onEmploy(ActionEvent event) {
        fireEmploy(false);
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

    private void fireEmploy (boolean mode){
        try {
            Stage viewStage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/managers/offerBrowser/basic/giveEmployeeWindow.fxml"));
            Parent root = fxmlLoader.load();
            GiveEmployeeWindowController giveEmployeeWindowController = fxmlLoader.getController();
            giveEmployeeWindowController.setMode(mode);
            if(mode){
            viewStage.setTitle("Fire employee");} else{
                viewStage.setTitle("Employ employee");
            giveEmployeeWindowController.setOfferID(browseOffersTable.getObjectObservableList().get(tabOffers.getSelectionModel().getSelectedIndex()).getId());}
            viewStage.setScene(new Scene(root, 600, 110));
            viewStage.show();
            txtMessage.setText("");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IndexOutOfBoundsException e){
            txtMessage.setText("You didn't pick any offer.");
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
