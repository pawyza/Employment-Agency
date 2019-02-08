package managers.loggedEmployersManager.employersBrowser;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Control;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import managers.LoggedUser;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EmployersTable {

    private TableView<EmployerObject> tabEmployers;
    private TableColumn<EmployerObject, Integer> colID;
    private TableColumn<EmployerObject, String> colName;
    private TableColumn<EmployerObject, String> colCountry;
    private TableColumn<EmployerObject, String> colNumber;
    private TableColumn<EmployerObject, String> colMail;
    private TableColumn<EmployerObject, String> colIDNumber;
    private ObservableList<EmployerObject> objectObservableList = FXCollections.observableArrayList();

    public EmployersTable(TableView<EmployerObject> tabEmployers, TableColumn<EmployerObject, Integer> colID, TableColumn<EmployerObject, String> colName, TableColumn<EmployerObject, String> colCountry, TableColumn<EmployerObject, String> colNumber, TableColumn<EmployerObject, String> colMail, TableColumn<EmployerObject, String> colIDNumber) throws SQLException {
        this.tabEmployers = tabEmployers;
        this.colID = colID;
        this.colName = colName;
        this.colCountry = colCountry;
        this.colNumber = colNumber;
        this.colMail = colMail;
        this.colIDNumber = colIDNumber;
        this.colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.colName.setCellFactory(tc -> {
            TableCell<EmployerObject, String> cell = new TableCell<>();
            Text text = new Text();
            cell.setGraphic(text);
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
            text.wrappingWidthProperty().bind(colName.widthProperty());
            text.textProperty().bind(cell.itemProperty());
            return cell ;});
        this.colNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        this.colCountry.setCellValueFactory(new PropertyValueFactory<>("country"));
        this.colMail.setCellValueFactory(new PropertyValueFactory<>("mail"));
        this.colIDNumber.setCellValueFactory(new PropertyValueFactory<>("idNumber"));
        prepareObservableList();
        this.tabEmployers.setItems(objectObservableList);
    }

    public void prepareObservableList() throws SQLException {
        Statement statement = LoggedUser.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM [PRACODAWCY_PELNE]");
        while (resultSet.next()) {
            EmployerObject employerObject = new EmployerObject();
            employerObject.setId(resultSet.getInt("ID_PRACODAWCY"));
            employerObject.setName(resultSet.getString("NAZWA"));
            employerObject.setCountry(resultSet.getString("KRAJ"));
            employerObject.setNumber(String.valueOf(resultSet.getInt("NUMER KONTAKTOWY")));
            employerObject.setIdNumber(resultSet.getString("NUMER IDENTYFIKACYJNY"));
            employerObject.setMail(resultSet.getString("ADRES EMAIL"));
            objectObservableList.add(employerObject);
        }
    }

    public void delete(int id) throws SQLException {
        CallableStatement cs = LoggedUser.getConnection().prepareCall("{call sp_DELETE_PRACODAWCA (?)}");
        cs.setInt(1,objectObservableList.get(id).getId());
        cs.execute();
        objectObservableList.remove(id);
    }

}
