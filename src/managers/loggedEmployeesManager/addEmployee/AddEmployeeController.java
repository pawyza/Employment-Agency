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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AddEmployeeController {
    private Connection connection;

    private ArrayList<String> languages = new ArrayList<>();
    private ArrayList<String> position = new ArrayList<>();
    private ArrayList<String> trade = new ArrayList<>();
    private ArrayList<String> permissions = new ArrayList<>();
    private ArrayList<String> countries = new ArrayList<>();
    private ArrayList<Integer> offer = new ArrayList<>();

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void prepare(){
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT JEZYK FROM JEZYKI");
            while (resultSet.next()){
                languages.add(resultSet.getString("JEZYK"));
            }
            resultSet = statement.executeQuery("SELECT STANOWISKO FROM STANOWISKA");
            while (resultSet.next()){
                position.add(resultSet.getString("STANOWISKO"));
            }
            resultSet = statement.executeQuery("SELECT BRANZA FROM BRANZE");
            while (resultSet.next()){
                trade.add(resultSet.getString("BRANZA"));
            }
            resultSet = statement.executeQuery("SELECT KRAJ FROM KRAJE");
            while (resultSet.next()){
                countries.add(resultSet.getString("KRAJ"));
            }
            resultSet = statement.executeQuery("SELECT NAZWA_UPRAWNIENIA FROM UPRAWNIENIA");
            while (resultSet.next()){
                permissions.add(resultSet.getString("NAZWA_UPRAWNIENIA"));
            }
            resultSet = statement.executeQuery("SELECT ID_OFERTY FROM OFERTY");
            while (resultSet.next()){
                offer.add(resultSet.getInt("ID_OFERTY"));
            }
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
    void nextPage(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/managers/loggedEmployeesManager/addEmployee/secondaryOptionPane.fxml"));
            Parent root = fxmlLoader.load();
            fxmlLoader.setController(this);
            Main.stage.setScene(new Scene(root));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

}
