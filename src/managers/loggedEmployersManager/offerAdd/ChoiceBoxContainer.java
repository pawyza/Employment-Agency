package managers.loggedEmployersManager.offerAdd;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import managers.LoggedUser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ChoiceBoxContainer {

    public void choicePosition(ChoiceBox<String> choiceBox) {
        choiceBox.setItems(FXCollections.observableList(loadData("SELECT STANOWISKO FROM STANOWISKA")));
    }

    public void choiceCompanyName(ChoiceBox<String> choiceBox) {
        choiceBox.setItems(FXCollections.observableList(loadData("SELECT NAZWA FROM PRACODAWCY")));
    }

    public void choiceCountry(ChoiceBox<String> choiceBox) {
        choiceBox.setItems(FXCollections.observableList(loadData("SELECT KRAJ FROM KRAJE")));
    }

    public void choiceOfferType(ChoiceBox<String> choiceBox) {
        choiceBox.setItems(FXCollections.observableList(loadData("SELECT RODZAJ_UMOWY FROM RODZAJ_UMOWY")));
    }

    public void choiceCurrency(ChoiceBox<String> choiceBox) {
        choiceBox.setItems(FXCollections.observableList(loadData("SELECT SKROT FROM WALUTY ")));
    }

    public void choiceLanguages(ChoiceBox<String> choiceBox) {
        choiceBox.setItems(FXCollections.observableList(loadData("SELECT JEZYK FROM JEZYKI")));
    }

    public void choiceDetails(ChoiceBox<String> choiceBox) {
        choiceBox.setItems(FXCollections.observableList(loadData("SELECT DETALE_OFERT FROM DODATKOWE_DETALE_OFERT")));
    }

    public void choicePermission(ChoiceBox<String> choiceBox) {
        choiceBox.setItems(FXCollections.observableList(loadData("SELECT NAZWA_UPRAWNIENIA FROM UPRAWNIENIA")));
    }
    private ArrayList<String> loadData(String sql){
            ArrayList<String> boxSet = new ArrayList<>();
            try{
                Statement statement = LoggedUser.getConnection().createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()){
                    boxSet.add(resultSet.getString(resultSet.getMetaData().getColumnLabel(1)));
                }
            }catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error while loading data.");
            }
            return boxSet; }

}