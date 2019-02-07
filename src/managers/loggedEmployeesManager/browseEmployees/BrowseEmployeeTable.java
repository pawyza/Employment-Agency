package managers.loggedEmployeesManager.browseEmployees;

import javafx.animation.Animation;
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
import java.util.ArrayList;

public class BrowseEmployeeTable {

    private TableView<EmployeeObject> tabEmployees;
    private TableColumn<EmployeeObject,Integer>  colID;
    private TableColumn<EmployeeObject, String> colName;
    private TableColumn<EmployeeObject, String> colForname;
    private TableColumn<EmployeeObject, String> colCoutry;
    private TableColumn<EmployeeObject, String> colLanguages;
    private TableColumn<EmployeeObject, String> colPermission;
    private TableColumn<EmployeeObject, String> colFormerPostition;
    private ObservableList<EmployeeObject> objectObservableList = FXCollections.observableArrayList();

    public BrowseEmployeeTable(TableView<EmployeeObject> tabEmployees, TableColumn<EmployeeObject,Integer>  colID, TableColumn<EmployeeObject, String> colName, TableColumn<EmployeeObject, String> colForname, TableColumn<EmployeeObject, String> colCoutry, TableColumn<EmployeeObject, String> colLanguages, TableColumn<EmployeeObject, String> colPermission, TableColumn<EmployeeObject, String> colFormerPostition) throws SQLException {
        this.colID = colID;
        this.tabEmployees = tabEmployees;
        this.colName = colName;
        this.colForname = colForname;
        this.colCoutry = colCoutry;
        this.colLanguages = colLanguages;
        this.colPermission = colPermission;
        this.colFormerPostition = colFormerPostition;
        this.colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.colForname.setCellValueFactory(new PropertyValueFactory<>("forename"));
        this.colCoutry.setCellValueFactory(new PropertyValueFactory<>("country"));
        this.colLanguages.setCellValueFactory(new PropertyValueFactory<>("allLanguagesRecord"));
        this.colLanguages.setCellFactory(tc -> {
            TableCell<EmployeeObject, String> cell = new TableCell<>();
            Text text = new Text();
            cell.setGraphic(text);
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
            text.wrappingWidthProperty().bind(colLanguages.widthProperty());
            text.textProperty().bind(cell.itemProperty());
            return cell ;});
        this.colPermission.setCellValueFactory(new PropertyValueFactory<>("employeePermissionsRecord"));
        this.colPermission.setCellFactory(tc -> {
            TableCell<EmployeeObject, String> cell = new TableCell<>();
            Text text = new Text();
            cell.setGraphic(text);
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
            text.wrappingWidthProperty().bind(colPermission.widthProperty());
            text.textProperty().bind(cell.itemProperty());
            return cell ;});
        this.colFormerPostition.setCellValueFactory(new PropertyValueFactory<>("formerPositionsRecord"));
        this.colFormerPostition.setCellFactory(tc -> {
            TableCell<EmployeeObject, String> cell = new TableCell<>();
            Text text = new Text();
            cell.setGraphic(text);
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
            text.wrappingWidthProperty().bind(colFormerPostition.widthProperty());
            text.textProperty().bind(cell.itemProperty());
            return cell ;});
        prepareObservableList();
        this.tabEmployees.setItems(objectObservableList);
    }

    private void prepareObservableList() throws SQLException {
        ArrayList<Integer> idArray = new ArrayList<>();
        Statement statement = LoggedUser.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT ID_PRACOWNIKA FROM PRACOWNICY");
        while (resultSet.next()){
            idArray.add(resultSet.getInt("ID_PRACOWNIKA"));
        }
        for (int id:idArray) {
            EmployeeObject employeeObject = new EmployeeObject();
            ArrayList<String> result = new ArrayList<>();
            employeeObject.setId(id);
            resultSet = statement.executeQuery("SELECT * FROM [PRACOWNICY_PELNE] WHERE ID_PRACOWNIKA =" + id);
            resultSet.next();
            employeeObject.setName(resultSet.getString("IMIE"));
            employeeObject.setForename(resultSet.getString("NAZWISKO"));
            employeeObject.setBirthDate(resultSet.getString("DATA URODZENIA"));
            employeeObject.setCountry(resultSet.getString("KRAJ"));
            employeeObject.setNativeLanguage(resultSet.getString("NATYWNY JEZYK"));
            employeeObject.setNumber(resultSet.getLong("NUMER_KONTAKTOWY"));
            result.add(employeeObject.getNativeLanguage());
            resultSet = statement.executeQuery("  SELECT J.JEZYK FROM PRACOWNICY_JEZYKI_DODATKOWE P JOIN JEZYKI J ON P.JEZYKI_DODATKOWE=J.ID_JEZYKA WHERE ID_PRACOWNIKA = " + id);
            while (resultSet.next()){
                result.add(resultSet.getString("JEZYK"));
            }
            employeeObject.setAllLanguages(result);
            result = new ArrayList<>();
            resultSet = statement.executeQuery("  SELECT S.STANOWISKO FROM PRACOWNICY_POPRZEDNIE_STANOWISKA P JOIN STANOWISKA S ON P.STANOWISKO=S.ID_STANOWISKA WHERE ID_PRACOWNIKA = " + id);
            while (resultSet.next()){
                result.add(resultSet.getString("STANOWISKO"));
            }
            employeeObject.setFormerPositions(result);
            result = new ArrayList<>();
            resultSet = statement.executeQuery("  SELECT U.NAZWA_UPRAWNIENIA FROM PRACOWNICY_UPRAWNIENIA P JOIN UPRAWNIENIA U ON P.POSIADANE_UPRAWNIENIA=U.ID_UPRAWNIENIA WHERE ID_PRACOWNIKA = " + id);
            while (resultSet.next()){
                result.add(resultSet.getString("NAZWA_UPRAWNIENIA"));
            }
            employeeObject.setEmployeePermissions(result);
            objectObservableList.add(employeeObject);
        }
    }

    public void delete(int id) throws SQLException {
        CallableStatement cs = LoggedUser.getConnection().prepareCall("{call sp_DELETE_PRACOWNIK (?)}");
        cs.setInt(1,objectObservableList.get(id).getId());
        cs.execute();
        objectObservableList.remove(id);
    }

    public ObservableList<EmployeeObject> getObjectObservableList() {
        return objectObservableList;
    }
}
