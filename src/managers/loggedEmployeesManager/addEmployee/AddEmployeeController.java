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
import managers.loggedEmployeesManager.addEmployee.additionalLanguagesAdding.AdditionalLanguagesAddingController;
import startPack.Main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class AddEmployeeController {
    private Connection connection;

    private DataContainer dataContainer;
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void prepare(){
        try {
            dataContainer = new DataContainer(connection);
            choiceCountry.setItems(FXCollections.observableList(dataContainer.getCountries()));
            choiceNativeLanguage.setItems(FXCollections.observableList(dataContainer.getLanguages()));
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
            dataContainer.setForname(txtForname.getText());
            dataContainer.setName(txtName.getText());
            dataContainer.setNativeLanguage(choiceNativeLanguage.getValue());
            dataContainer.setNumber(txtNumber.getText());

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/managers/loggedEmployeesManager/addEmployee/additionalLanguagesAdding/additionalLanguagesAddingPane.fxml"));
            Parent root = fxmlLoader.load();
            AdditionalLanguagesAddingController additionalLanguagesAddingController = fxmlLoader.getController();
            additionalLanguagesAddingController.setConnection(connection);
            additionalLanguagesAddingController.setDataContainer(dataContainer);
            additionalLanguagesAddingController.prepare();
            Main.stage.setScene(new Scene(root));
        } catch (IllegalArgumentException e){
            errorMessage.setText("Check if all values are correct");
            System.out.println("Blad podczas zapisywania wartosci.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Blad podczas otwierania nowej strony.");
            e.printStackTrace();
        }
    }

}
