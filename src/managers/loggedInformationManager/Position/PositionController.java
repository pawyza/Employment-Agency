package managers.loggedInformationManager.Position;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import managers.Switcher;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PositionController implements Initializable {

    PositionManager positionManager;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            positionManager = new PositionManager(tabPosition,colPosition,colTrade,"[dbo].[STANOWISKO_PELNE]");
            positionManager.setUpBox(choiceTrade,"BRANZE");
        } catch (SQLException e) {
            txtMessage.setText("Required table not found");
            e.printStackTrace();
        }
    }


    @FXML
    private ChoiceBox<String> choiceTrade;

    @FXML
    private TextField txtPosition;

    @FXML
    private Text txtMessage;

    @FXML
    private TableView<PositionObject> tabPosition;

    @FXML
    private TableColumn<PositionObject, String> colPosition;

    @FXML
    private TableColumn<PositionObject, String> colTrade;


    @FXML
    void onAdd(ActionEvent event) {
        try {
            positionManager.updateAdd(txtPosition.getText().toUpperCase(),choiceTrade.getValue());
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
            positionManager.updateRemove(tabPosition.getSelectionModel().getSelectedIndex());
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
