package managers.loggedEmployeesManager.addEmployee.result;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import managers.loggedEmployeesManager.addEmployee.DataContainer;
import startPack.Main;

import java.io.IOException;

public class AddingResultController {
    private DataContainer dataContainer;

    public void setDataContainer(DataContainer dataContainer) {
        this.dataContainer = dataContainer;
    }

    public void prepare(){
        txtName.setText(dataContainer.getName());
        txtForename.setText(dataContainer.getForename());
        txtBirthDate.setText(dataContainer.getBirthDate());
        txtNativeLanguage.setText(dataContainer.getNativeLanguage());
        txtCountry.setText(dataContainer.getCountry());
        txtPhone.setText(String.valueOf(dataContainer.getNumber()));
        StringBuilder stringBuilder = new StringBuilder();
        for (String i: dataContainer.getAdditionalLanguages()) {
            stringBuilder.append(i).append(" ");
        }
        txtLanguages.setText(stringBuilder.toString());
        stringBuilder = new StringBuilder();
        for (String i: dataContainer.getFormerPositions()) {
            stringBuilder.append(i).append(" ");
        }
        txtFormerPositions.setText(stringBuilder.toString());
        stringBuilder = new StringBuilder();
        for (String i: dataContainer.getEmployeePermissions()) {
            stringBuilder.append(i).append(" ");
        }
        txtPermission.setText(stringBuilder.toString());
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

    @FXML
    private Button btnAdd;


    @FXML
    void onAbort(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/managers/loggedEmployeesManager/employeesManagerPanel.fxml"));
            Parent root = fxmlLoader.load();
            Main.stage.setScene(new Scene(root));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FXML
    void onAdd(ActionEvent event) {
        try {
            EmployeeAdder employeeAdder = new EmployeeAdder(dataContainer);
            employeeAdder.add();
            btnAdd.setDisable(true);
        } catch (RuntimeException e) {
            System.out.println("Blad zapisu");
        }
    }
}