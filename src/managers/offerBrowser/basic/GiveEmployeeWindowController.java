package managers.offerBrowser.basic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import managers.LoggedUser;

import java.net.URL;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class GiveEmployeeWindowController implements Initializable {

    private boolean mode;
    private int offerID;

    @FXML
    private TextField txtID;

    @FXML
    private Text txtBasic;

    public void setMode(boolean mode){
        this.mode = mode;
    }

    @FXML
    void onOk(ActionEvent event) {
        try {
        if(mode) { //fire
            CallableStatement cs = LoggedUser.getConnection().prepareCall("{call sp_DELETE_AKTUALNIE_ZATRUDNIENI (?)}");
            cs.setInt(1, Integer.parseInt(txtID.getText()));
            cs.execute();
            Stage stage = (Stage) txtBasic.getScene().getWindow();
            stage.close();
        } else {
            CallableStatement cs = LoggedUser.getConnection().prepareCall("{call sp_INSERT_AKTUALNIE_ZATRUDNIENI (?,?)}");
            cs.setInt(1, Integer.parseInt(txtID.getText()));
            cs.setInt(2, offerID);
            cs.execute();
            Stage stage = (Stage) txtBasic.getScene().getWindow();
            stage.close();
        }
        } catch (SQLException e) {
            System.out.println("Blad przy pobieraniu danych pracownika.");
        } catch (IllegalArgumentException e){
            txtBasic.setText("Wrong ID");
            System.out.println("Niepoprawnie podano Id");
        }
    }

    public void setOfferID(int offerID) {
        this.offerID = offerID;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            txtID.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                StringBuilder basicInfo = new StringBuilder();
                Statement statement = LoggedUser.getConnection().createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM [PRACOWNICY_PELNE] WHERE ID_PRACOWNIKA =" + txtID.getText());
                resultSet.next();
                basicInfo.append(resultSet.getString("IMIE") + ", ");
                basicInfo.append(resultSet.getString("NAZWISKO") + ", ");
                basicInfo.append(resultSet.getString("DATA URODZENIA") + ", ");
                basicInfo.append(resultSet.getString("KRAJ"));
                txtBasic.setText(basicInfo.toString());
            } catch (SQLException e) {
                txtBasic.setText("Wrong ID");
                System.out.println("Blad przy pobieraniu danych pracownika.");
            }});
    }
}
