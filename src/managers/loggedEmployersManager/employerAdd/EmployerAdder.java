package managers.loggedEmployersManager.employerAdd;

import javafx.collections.FXCollections;
import javafx.scene.control.ChoiceBox;
import managers.LoggedUser;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EmployerAdder {

    public EmployerAdder(ChoiceBox<String> choiceCountry) throws SQLException {
        ArrayList<String> countries = new ArrayList<>();
        Statement statement = LoggedUser.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT KRAJ FROM KRAJE");
        while (resultSet.next()){
            countries.add(resultSet.getString("KRAJ"));
        }
        choiceCountry.setItems(FXCollections.observableList(countries));
    }

    public void addEmployer(String name,String country,String mail,String number,String idNumber) throws SQLException {
        CallableStatement cs = LoggedUser.getConnection().prepareCall("{call sp_INSERT_PRACODAWCA (?,?,?,?,?)}");
        cs.setString(1,name.toUpperCase());
        cs.setString(2,country.toUpperCase());
        if(number.equals(""))
            number = "0";
        cs.setInt(3,Integer.parseInt(number));
        cs.setString(4,mail.toUpperCase());
        cs.setString(5,idNumber);
        cs.execute();
    }
}
