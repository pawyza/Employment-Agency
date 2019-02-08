package managers.offerBrowser;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Control;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import managers.LoggedUser;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BrowseOffersTable {
    private TableView<OfferObject> tabOffers;
    private TableColumn<OfferObject, Integer> colID;
    private TableColumn<OfferObject, String> colPosition;
    private TableColumn<OfferObject, String> colCompanyName;
    private TableColumn<OfferObject, String> colCountry;
    private TableColumn<OfferObject, String> colCity;
    private TableColumn<OfferObject, Integer> colVacancy;
    private ObservableList<OfferObject> objectObservableList = FXCollections.observableArrayList();

    public BrowseOffersTable(TableView<OfferObject> tabOffers, TableColumn<OfferObject, Integer> colID, TableColumn<OfferObject, String> colPosition, TableColumn<OfferObject, String> colCompanyName, TableColumn<OfferObject, String> colCountry, TableColumn<OfferObject, String> colCity, TableColumn<OfferObject, Integer> colVacancy) throws SQLException {
        this.tabOffers = tabOffers;
        this.colID = colID;
        this.colPosition = colPosition;
        this.colCompanyName = colCompanyName;
        this.colCountry = colCountry;
        this.colCity = colCity;
        this.colVacancy = colVacancy;
        this.colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.colPosition.setCellValueFactory(new PropertyValueFactory<>("position"));
        this.colCompanyName.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        this.colCompanyName.setCellFactory(tc -> {
            TableCell<OfferObject, String> cell = new TableCell<>();
            Text text = new Text();
            cell.setGraphic(text);
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
            text.wrappingWidthProperty().bind(colCompanyName.widthProperty());
            text.textProperty().bind(cell.itemProperty());
            return cell ;});
        this.colCountry.setCellValueFactory(new PropertyValueFactory<>("country"));
        this.colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        this.colVacancy.setCellValueFactory(new PropertyValueFactory<>("vacancy"));
        prepareObservableList();
        this.tabOffers.setItems(objectObservableList);
    }


    public ObservableList<OfferObject> getObjectObservableList() {
        return objectObservableList;
    }


    public void prepareObservableList() throws SQLException {
        ArrayList<Integer> idArray = new ArrayList<>();
        Statement statement = LoggedUser.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT ID_OFERTY FROM OFERTY");
        while (resultSet.next()){
            idArray.add(resultSet.getInt("ID_OFERTY"));
        }
        for (int id:idArray) {
            OfferObject offerObject = new OfferObject();
            ArrayList<String> result = new ArrayList<>();
            offerObject.setId(id);
            resultSet = statement.executeQuery("SELECT * FROM [OFERTY_PELNE] WHERE ID_OFERTY =" + id);
            resultSet.next();
            offerObject.setCity(resultSet.getString("MIASTO"));
            offerObject.setCompanyName(resultSet.getString("NAZWA"));
            offerObject.setCountry(resultSet.getString("KRAJ"));
            offerObject.setCurrency(resultSet.getString("WALUTA WYPLATY"));
            offerObject.setOfferType(resultSet.getString("RODZAJ UMOWY"));
            offerObject.setPosition(resultSet.getString("STANOWISKO"));
            offerObject.setSalary(new BigDecimal(resultSet.getLong("PENSJA BRUTTO")));
            offerObject.setTrade(resultSet.getString("BRANZA"));
            offerObject.setVacancy(resultSet.getInt("ILOSC OFEROWANYCH MIEJSC"));
            resultSet = statement.executeQuery("  SELECT J.JEZYK FROM OFERTY_WYMAGANE_JEZYKI O JOIN JEZYKI J ON O.WYMAGANE_JEZYKI=J.ID_JEZYKA WHERE ID_OFERTY = " + id);
            while (resultSet.next()){
                result.add(resultSet.getString("JEZYK"));
            }
            offerObject.setLanguages(result);
            result = new ArrayList<>();
            resultSet = statement.executeQuery("  SELECT U.NAZWA_UPRAWNIENIA FROM OFERTY_WYMAGANE_UPRAWNIENIA O JOIN UPRAWNIENIA U ON O.UPRAWNIENIA_WYMAGANE=U.ID_UPRAWNIENIA WHERE ID_OFERTY = " + id);
            while (resultSet.next()){
                result.add(resultSet.getString("NAZWA_UPRAWNIENIA"));
            }
            offerObject.setPermissions(result);
            result = new ArrayList<>();
            resultSet = statement.executeQuery("  SELECT D.DETALE_OFERT FROM OFERTY_DETALE O JOIN DODATKOWE_DETALE_OFERT D ON O.DETAL_OFERTY=D.ID_DETALE WHERE ID_OFERTY = " + id);
            while (resultSet.next()){
                result.add(resultSet.getString("DETALE_OFERT"));
            }
            offerObject.setDetails(result);
            ArrayList<Integer> employees = new ArrayList<>();
            resultSet = statement.executeQuery("SELECT ID_PRACOWNIKA FROM AKTUALNIE_ZATRUDNIENI WHERE ID_OFERTY=" + id);
            while (resultSet.next()){
                employees.add(resultSet.getInt("ID_PRACOWNIKA"));
            }
            offerObject.setEmployeesID(employees);
            objectObservableList.add(offerObject);
        }
    }

    public void delete(int id) throws SQLException {
        CallableStatement cs = LoggedUser.getConnection().prepareCall("{call sp_DELETE_OFERTA (?)}");
        cs.setInt(1,objectObservableList.get(id).getId());
        cs.execute();
        objectObservableList.remove(id);
    }
}
