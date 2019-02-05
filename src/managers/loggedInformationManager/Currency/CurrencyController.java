package managers.loggedInformationManager.Currency;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import managers.Switcher;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CurrencyController implements Initializable {

    CurrencyManager currencyManager;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            currencyManager = new CurrencyManager(tabCurrency,colCurrencyName,colShort,colValue,"WALUTY");
        } catch (SQLException e) {
            txtMessage.setText("Required table not found");
            e.printStackTrace();
        }
    }

    @FXML
    private TextField txtCurrency;

    @FXML
    private TextField txtShort;

    @FXML
    private TextField txtValue;

    @FXML
    private Text txtMessage;

    @FXML
    private TableView<CurrencyObject> tabCurrency;

    @FXML
    private TableColumn<CurrencyObject, String> colCurrencyName;

    @FXML
    private TableColumn<CurrencyObject, String> colShort;

    @FXML
    private TableColumn<CurrencyObject, BigDecimal> colValue;

    @FXML
    void onAdd(ActionEvent event) {
        try {
            currencyManager.updateAdd(txtCurrency.getText().toUpperCase(),txtShort.getText().toUpperCase(),BigDecimal.valueOf(Double.parseDouble(txtValue.getText())));
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
            currencyManager.updateRemove(tabCurrency.getSelectionModel().getSelectedIndex());
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