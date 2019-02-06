package managers.loggedEmployeesManager.browseEmployees.fullInformation;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import managers.loggedEmployeesManager.browseEmployees.EmployeeObject;

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
        for (String s:list) {
            sb.append(s+", ");
        }
        if(sb.length()>2)
            sb.delete(sb.length()-2,sb.length());
        return sb.toString();
    }
}