package managers.loggedEmployersManager.employersBrowser;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import managers.Switcher;

import java.math.BigInteger;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EmployerBrowseController implements Initializable {

    private EmployersTable employersTable;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
        employersTable = new EmployersTable(tabEmployers,colID,colName,colCountry,colNumber,colMail,colIDNumber);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private Text txtMessage;

    @FXML
    private TableView<EmployerObject> tabEmployers;

    @FXML
    private TableColumn<EmployerObject, Integer> colID;

    @FXML
    private TableColumn<EmployerObject, String> colName;

    @FXML
    private TableColumn<EmployerObject, String> colCountry;

    @FXML
    private TableColumn<EmployerObject, String> colNumber;

    @FXML
    private TableColumn<EmployerObject, String> colMail;

    @FXML
    private TableColumn<EmployerObject, String> colIDNumber;

    @FXML
    void onDelete(ActionEvent event) {
        try {
            employersTable.delete(tabEmployers.getSelectionModel().getSelectedIndex());
            txtMessage.setText("");
        } catch (SQLException e) {
            txtMessage.setText("Employee hasn't been deleted");
        }
    }

    @FXML
    void onFindBy(ActionEvent event) {

    }

    @FXML
    void onReturn(ActionEvent event) {
            new Switcher().change("/managers/loggedEmployersManager/employersManagerPanel.fxml");
    }
}
