package managers.loggedInformationManager.Position;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import managers.LoggedUser;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PositionManager {

    private TableView<PositionObject> tableView;
    private TableColumn<PositionObject, String> columnPosition;
    private TableColumn<PositionObject, String> columnTrade;
    private ObservableList<PositionObject> observableList = FXCollections.observableArrayList();
    private String item;
    private String columnName;


    public PositionManager(TableView<PositionObject> tableView, TableColumn<PositionObject, String> columnPosition,TableColumn<PositionObject, String> columnTrade, String view) throws SQLException {
        this.tableView = tableView;
        this.columnPosition = columnPosition;
        this.columnTrade = columnTrade;
        this.item = view;
        this.columnPosition.setCellValueFactory(new PropertyValueFactory<>("position"));
        this.columnTrade.setCellValueFactory(new PropertyValueFactory<>("trade"));
        this.tableView.setItems(observableList);
        Statement statement = LoggedUser.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM " + view);
        columnName = resultSet.getMetaData().getColumnLabel(2);
        while (resultSet.next()){
            observableList.add(new PositionObject(resultSet.getString(resultSet.getMetaData().getColumnLabel(2)),resultSet.getString(resultSet.getMetaData().getColumnLabel(3))));
        }
        this.tableView.setItems(observableList);
    }

    public void setUpBox(ChoiceBox<String> box, String item) throws SQLException {
        Statement statement = LoggedUser.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT BRANZA FROM " + item);
        ArrayList<String> result = new ArrayList<>();
        while (resultSet.next()){
            result.add(resultSet.getString("BRANZA"));
        }
        box.setItems(FXCollections.observableList(result));
    }


    public void updateAdd(String position, String trade) throws SQLException,NullPointerException {
        if(!checkIfDuplicate(position) && !position.equals("") && !trade.equals("")) {
            CallableStatement cs = LoggedUser.getConnection().prepareCall("{call sp_INSERT_STANOWISKO (?,?)}");
            cs.setString(1,position.toUpperCase());
            cs.setString(2,trade.toUpperCase());
            cs.execute();
            observableList.add(new PositionObject(position,trade));
        } else {
            throw new NullPointerException();
        }
    }

    public void updateRemove(int value) throws RuntimeException, SQLException {
        Statement statement = LoggedUser.getConnection().createStatement();
        if (!statement.execute("DELETE FROM STANOWISKA WHERE " + columnName + "='" + observableList.get(value).getPosition() + "'"))
            observableList.remove(value);
    }

    private boolean checkIfDuplicate(String value){
        for (PositionObject v: observableList) {
            if(v.getPosition().equals(value)){
                return true;
            }
        }
        return false;
    }
}
