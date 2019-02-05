package managers.loggedInformationManager;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import managers.LoggedUser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UniversalTManager {

    private TableView<String> tableView;
    private TableColumn<String, String> column;
    private ObservableList<String> observableList = FXCollections.observableArrayList();
    private String item;
    private String columnName;

    public UniversalTManager(TableView<String> tableView, TableColumn<String, String> column,String item) throws SQLException {
        this.tableView = tableView;
        this.column = column;
        this.column.setCellValueFactory(cellData ->
                new ReadOnlyStringWrapper(cellData.getValue()));
        this.item = item;
        Statement statement = LoggedUser.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM " + item);
        columnName = resultSet.getMetaData().getColumnLabel(2);
        while (resultSet.next()){
            observableList.add(resultSet.getString(columnName));
        }
        this.tableView.setItems(observableList);
    }

    public void updateAdd(String value) throws SQLException,NullPointerException {
        if(!checkIfDuplicate(value) && !value.equals("")) {
            Statement statement = LoggedUser.getConnection().createStatement();
            statement.execute("INSERT INTO " + item +" VALUES ('" + value +"')");
            observableList.add(value);
        } else {
            throw new NullPointerException();
        }
    }

    public void updateRemove(String value) throws RuntimeException, SQLException {
        Statement statement = LoggedUser.getConnection().createStatement();
        if (!statement.execute("DELETE FROM " + item +" WHERE " + columnName + "='" + value + "'"))
        observableList.remove(value);
    }

    private boolean checkIfDuplicate(String value){
        for (String v: observableList) {
            if(v.equals(value)){
                return true;
            }
        }
        return false;
    }
}
