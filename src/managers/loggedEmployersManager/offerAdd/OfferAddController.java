package managers.loggedEmployersManager.offerAdd;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import managers.Switcher;
import managers.loggedEmployersManager.offerAdd.detailsChoice.DetailsChoiceController;
import managers.loggedEmployersManager.offerAdd.languageChoice.LanguagesChoiceController;
import startPack.Main;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

public class OfferAddController implements Initializable {

    private NewOfferObject newOfferObject = new NewOfferObject();
    private ChoiceBoxContainer choiceBoxContainer = new ChoiceBoxContainer();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBoxContainer.choiceCompanyName(choiceCompanyName);
        choiceBoxContainer.choiceCountry(choiceCountry);
        choiceBoxContainer.choiceCurrency(choiceCurrency);
        choiceBoxContainer.choicePosition(choicePosition);
        choiceBoxContainer.choiceOfferType(choiceOfferType);
    }

    @FXML
    private ChoiceBox<String> choicePosition;

    @FXML
    private ChoiceBox<String> choiceCompanyName;

    @FXML
    private ChoiceBox<String> choiceCountry;

    @FXML
    private TextField txtCity;

    @FXML
    private ChoiceBox<String> choiceOfferType;

    @FXML
    private TextField txtVacancy;

    @FXML
    private TextField txtSalary;

    @FXML
    private ChoiceBox<String> choiceCurrency;

    @FXML
    private Text errorMessage;

    @FXML
    void onNext(ActionEvent event) {
        try {
            newOfferObject.setCity(txtCity.getText());
            newOfferObject.setCompanyName(choiceCompanyName.getSelectionModel().getSelectedItem());
            newOfferObject.setCountry(choiceCountry.getSelectionModel().getSelectedItem());
            newOfferObject.setCurrency(choiceCurrency.getSelectionModel().getSelectedItem());
            newOfferObject.setOfferType(choiceOfferType.getSelectionModel().getSelectedItem());
            newOfferObject.setPosition(choicePosition.getSelectionModel().getSelectedItem());
            newOfferObject.setSalary(new BigDecimal(txtSalary.getText()));
            newOfferObject.setVacancy(Integer.parseInt(txtVacancy.getText()));
            errorMessage.setText("");
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/managers/loggedEmployersManager/offerAdd/languageChoice/languagesChoicePane.fxml"));
            Parent root = fxmlLoader.load();
            LanguagesChoiceController languagesChoiceController = fxmlLoader.getController();
            languagesChoiceController.prepare(newOfferObject);
            Main.stage.setScene(new Scene(root));
        } catch (RuntimeException e) {
            e.printStackTrace();
            errorMessage.setText("Values are incorrect");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onReturn(ActionEvent event) {
        new Switcher().change("/managers/loggedEmployersManager/employersManagerPanel.fxml");
    }
}
