package startPack;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class LoginPanel {

    @FXML
    private TextField txtLogin;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Text txtResponse;

    @FXML
    void loginPressed(ActionEvent event) throws IOException {
        Logging logging = new Logging(txtLogin.getText(),txtPassword.getText());
        try{
        logging.checkLogin();
        logging.logIn();
        } catch (SQLException e) {
            e.printStackTrace();
            txtResponse.setText("Login error.");
        } catch (NullPointerException e){
            e.printStackTrace();
            txtResponse.setText("Something went wrong.");
        }


    }

}
