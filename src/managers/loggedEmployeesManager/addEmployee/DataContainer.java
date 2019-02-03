package managers.loggedEmployeesManager.addEmployee;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DataContainer {
    private ArrayList<String> languages = new ArrayList<>();
    private ArrayList<String> position = new ArrayList<>();
    private ArrayList<String> trade = new ArrayList<>();
    private ArrayList<String> permissions = new ArrayList<>();
    private ArrayList<String> countries = new ArrayList<>();
    private ArrayList<Integer> offer = new ArrayList<>();

    public DataContainer(Connection connection) throws SQLException {
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
    }

    public ArrayList<String> getLanguages() {
        return languages;
    }

    public ArrayList<String> getPosition() {
        return position;
    }

    public ArrayList<String> getTrade() {
        return trade;
    }

    public ArrayList<String> getPermissions() {
        return permissions;
    }

    public ArrayList<String> getCountries() {
        return countries;
    }

    public ArrayList<Integer> getOffer() {
        return offer;
    }

    //Informations to add
}
