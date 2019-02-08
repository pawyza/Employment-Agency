package managers.loggedEmployersManager.employerAdd;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import managers.Switcher;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EmployerAddController implements Initializable {

    EmployerAdder employerAdder;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            employerAdder = new EmployerAdder(choiceCountry);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtNumber;

    @FXML
    private TextField txtMail;

    @FXML
    private TextField txtIDNumber;

    @FXML
    private ChoiceBox<String> choiceCountry;

    @FXML
    private Button btnAdd;

    @FXML
    private Text txtMessage;


    @FXML
    void onAdd(ActionEvent event) {
        try{
            if (txtNumber.getText().length()<1 || txtNumber.getText().length()>4){
            employerAdder.addEmployer(txtName.getText(),choiceCountry.getSelectionModel().getSelectedItem(),txtMail.getText(),txtNumber.getText(),txtIDNumber.getText());
            btnAdd.setDisable(true);
            txtMessage.setText("Employer successfully added!");}
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (RuntimeException e){
            e.printStackTrace();
            txtMessage.setText("Values are incorrect!");
        }
    }

    @FXML
    void onReturn(ActionEvent event) {
        new Switcher().change("/managers/loggedEmployersManager/employersManagerPanel.fxml");
    }
}
