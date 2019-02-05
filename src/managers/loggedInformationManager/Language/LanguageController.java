package managers.loggedInformationManager.Language;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import managers.Switcher;
import managers.loggedInformationManager.UniversalTManager;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LanguageController implements Initializable {

    UniversalTManager universalTManager;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            universalTManager = new UniversalTManager(tabLanguage, colLanguage,"JEZYKI");
        } catch (SQLException e) {
            txtMessage.setText("Required table not found");
            e.printStackTrace();
        }
    }

    @FXML
    private TextField txtLanguage;

    @FXML
    private Text txtMessage;

    @FXML
    private TableView<String> tabLanguage;

    @FXML
    private TableColumn<String, String> colLanguage;

    @FXML
    void onAdd(ActionEvent event) {
        try {
            universalTManager.updateAdd(txtLanguage.getText().toUpperCase());
            txtMessage.setText("");
        } catch (SQLException e) {
            txtMessage.setText("Error while adding");
            e.printStackTrace();
        } catch (NullPointerException e){
            txtMessage.setText("Inserted values are not correct");
        }
    }

    @FXML
    void onDelete(ActionEvent event) {
        try {
            universalTManager.updateRemove(tabLanguage.getSelectionModel().getSelectedItem());
            txtMessage.setText("");
        }catch (RuntimeException e){
            System.out.println("No selection");
        } catch (SQLException e) {
            txtMessage.setText("Value is in use");
        }
    }

    @FXML
    void onReturn(ActionEvent event) {
        new Switcher().change("/managers/loggedInformationManager/informationManagerPanel.fxml");
    }
}