package managers.loggedInformationManager.Currency;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import managers.LoggedUser;
import managers.loggedInformationManager.UniversalTManager;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CurrencyManager{

    private TableView<CurrencyObject> tableView;
    private TableColumn<CurrencyObject, String> columnFullname;
    private TableColumn<CurrencyObject, String> columnShortcut;
    private TableColumn<CurrencyObject, BigDecimal> columnValue;
    private ObservableList<CurrencyObject> observableList = FXCollections.observableArrayList();
    private String item;
    private String columnName;


    public CurrencyManager(TableView<CurrencyObject> tableView, TableColumn<CurrencyObject, String> columnFullname, TableColumn<CurrencyObject, String> columnShortcut, TableColumn<CurrencyObject, BigDecimal> columnValue, String item) throws SQLException {
        this.tableView = tableView;
        this.columnFullname = columnFullname;
        this.columnShortcut = columnShortcut;
        this.columnValue = columnValue;
        this.item = item;
        this.columnFullname.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.columnShortcut.setCellValueFactory(new PropertyValueFactory<>("shortcut"));
        this.columnValue.setCellValueFactory(new PropertyValueFactory<>("value"));
        this.tableView.setItems(observableList);
        Statement statement = LoggedUser.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM " + item);
        columnName = resultSet.getMetaData().getColumnLabel(2);
        while (resultSet.next()){
            observableList.add(new CurrencyObject(resultSet.getString(resultSet.getMetaData().getColumnLabel(2)),resultSet.getString(resultSet.getMetaData().getColumnLabel(1)),resultSet.getBigDecimal(resultSet.getMetaData().getColumnLabel(3))));
        }
        this.tableView.setItems(observableList);
    }


    public void updateAdd(String name, String shortcut, BigDecimal value) throws SQLException,NullPointerException {
        if(!checkIfDuplicate(name) && !name.equals("") && !shortcut.equals("") && !(value.equals(0))) {
            Statement statement = LoggedUser.getConnection().createStatement();
            statement.execute("INSERT INTO " + item +" VALUES ('" + shortcut +"','"+name+"',"+value.toString()+")");
            observableList.add(new CurrencyObject(name,shortcut,value));
        } else {
            throw new NullPointerException();
        }
    }

    public void updateRemove(int value) throws RuntimeException, SQLException {
        Statement statement = LoggedUser.getConnection().createStatement();
        if (!statement.execute("DELETE FROM " + item +" WHERE " + columnName + "='" + observableList.get(value).getName() + "'"))
            observableList.remove(value);
    }

    private boolean checkIfDuplicate(String value){
        for (CurrencyObject v: observableList) {
            if(v.getName().equals(value)){
                return true;
            }
        }
        return false;
    }
}
