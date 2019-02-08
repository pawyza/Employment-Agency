package managers.loggedEmployeesManager.browseEmployees.fullInformation;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import managers.LoggedUser;
import managers.loggedEmployeesManager.browseEmployees.EmployeeObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EmployeeViewController {
    public void prepare(EmployeeObject employeeObject) {
        txtName.setText(employeeObject.getName());
        txtForename.setText(employeeObject.getForename());
        txtBirthDate.setText(employeeObject.getBirthDate());
        txtCountry.setText(employeeObject.getCountry());
        txtNativeLanguage.setText(employeeObject.getNativeLanguage());
        txtPhone.setText(String.valueOf(employeeObject.getNumber()));
        txtLanguages.setText(prepLanguages(employeeObject.getAllLanguages()));
        txtFormerPositions.setText(employeeObject.getFormerPositionsRecord());
        txtPermission.setText(employeeObject.getEmployeePermissionsRecord());
        try {
            txtOfferId.setText(getOfferId(employeeObject.getId()));
        }catch (SQLException e){
            System.out.println("Blad podczas pobierania id oferty");
        }
    }

    @FXML
    private Text txtName;

    @FXML
    private Text txtForename;

    @FXML
    private Text txtBirthDate;

    @FXML
    private Text txtCountry;

    @FXML
    private Text txtNativeLanguage;

    @FXML
    private Text txtOfferId;

    @FXML
    private Text txtPhone;

    @FXML
    private Text txtLanguages;

    @FXML
    private Text txtFormerPositions;

    @FXML
    private Text txtPermission;

    private String prepLanguages(ArrayList<String> list){
        list.remove(0);
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (String s:list) {
            sb.append(s+", ");
            if(sb.length()>50*i){
                sb.append("\n");
                i++;
            }
        }
        if(sb.length()>2)
            sb.delete(sb.length()-2,sb.length());
        else
            sb.append("BRAK");
        return sb.toString();
    }

    private String getOfferId(int id) throws SQLException {
        Statement statement = LoggedUser.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT ID_OFERTY FROM AKTUALNIE_ZATRUDNIENI WHERE ID_PRACOWNIKA=" + id);
        if(resultSet.next()){
            return String.valueOf(resultSet.getInt("ID_OFERTY"));
        };
        return "NOT EMPLOYED";
    }
}
