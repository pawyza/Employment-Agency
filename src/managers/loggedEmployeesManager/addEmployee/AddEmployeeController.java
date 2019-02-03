package managers.loggedEmployeesManager.addEmployee;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
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
    private ChoiceBox<?> choiceCountry;

    @FXML
    private ChoiceBox<?> choiceNativeLanguage;

    @FXML
    private Text errorMessage;

    @FXML
    void next(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/managers/loggedEmployeesManager/addEmployee/additionalLanguagesAddingPane.fxml"));
            Parent root = fxmlLoader.load();
            AdditionalLanguagesAddingController additionalLanguagesAddingController = fxmlLoader.getController();
            additionalLanguagesAddingController.setConnection(connection);
            additionalLanguagesAddingController.setDataContainer(dataContainer);
            Main.stage.setScene(new Scene(root));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

}
