package managers.loggedEmployeesManager.addEmployee;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import managers.Switcher;
import managers.loggedEmployeesManager.addEmployee.additionalLanguagesAdding.AdditionalLanguagesAddingController;
import startPack.Main;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class AddEmployeeController {
    private DataContainer dataContainer;
    public void prepare(){
        try {
            dataContainer = new DataContainer();
            choiceCountry.setItems(FXCollections.observableList(dataContainer.getCountries()));
            choiceNativeLanguage.setItems(FXCollections.observableList(dataContainer.getLanguages()));
            dateBirthDate.setValue(LocalDate.of(2000,1,1));
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error while loading data");
        }
    }

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtForname;

    @FXML
    private TextField txtNumber;

    @FXML
    private DatePicker dateBirthDate;

    @FXML
    private ChoiceBox<String> choiceCountry;

    @FXML
    private ChoiceBox<String> choiceNativeLanguage;

    @FXML
    private Text errorMessage;

    @FXML
    void next(ActionEvent event) {
        try {
            dataContainer.setBirthDate(dateBirthDate.getValue());
            dataContainer.setCountry(choiceCountry.getValue());
            dataContainer.setForename(txtForname.getText());
            dataContainer.setName(txtName.getText());
            dataContainer.setNativeLanguage(choiceNativeLanguage.getValue());
            dataContainer.setNumber(txtNumber.getText());

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/managers/loggedEmployeesManager/addEmployee/additionalLanguagesAdding/additionalLanguagesAddingPane.fxml"));
            Parent root = fxmlLoader.load();
            AdditionalLanguagesAddingController additionalLanguagesAddingController = fxmlLoader.getController();
            additionalLanguagesAddingController.setDataContainer(dataContainer);
            additionalLanguagesAddingController.prepare();
            Main.stage.setScene(new Scene(root));
        } catch (RuntimeException e){
            errorMessage.setText("Check if all values are correct");
            System.out.println("Blad podczas zapisywania wartosci.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Blad podczas otwierania nowej strony.");
            e.printStackTrace();
        }
    }

    @FXML
    void onReturn(ActionEvent event) {
        new Switcher().change("/managers/loggedEmployeesManager/employeesManagerPanel.fxml");
    }

}
