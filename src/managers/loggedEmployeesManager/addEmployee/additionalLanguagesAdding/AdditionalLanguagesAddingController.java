package managers.loggedEmployeesManager.addEmployee.additionalLanguagesAdding;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import managers.loggedEmployeesManager.addEmployee.DataContainer;
import managers.loggedEmployeesManager.addEmployee.formerPositionAdding.FormerPositionsAddingController;
import startPack.Main;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

public class AdditionalLanguagesAddingController {
    private DataContainer dataContainer;
    private LanguagesTable languagesTable;

    public void setDataContainer(DataContainer dataContainer) {
        this.dataContainer = dataContainer;
    }

    public void prepare(){
        choiceLanguage.setItems(FXCollections.observableList(dataContainer.getLanguages()));
        languagesTable = new LanguagesTable(tabLanguage,colInformation,colLanguage,dataContainer.getNativeLanguage());
        choiceLanguage.getSelectionModel().selectedItemProperty().addListener((observableValue, number, t1) -> {
            languagesTable.updateAdd(choiceLanguage.getValue());
            if(languagesTable.updateCheck()){
                btnDelete.setDisable(false);
            }});
    }


    @FXML
    private ChoiceBox<String> choiceLanguage;

    @FXML
    private Button btnDelete;

    @FXML
    private TableView<Language> tabLanguage;

    @FXML
    private TableColumn<Language, ArrayList<String>> colInformation;

    @FXML
    private TableColumn<Language, ArrayList<String>> colLanguage;



    @FXML
    void next(ActionEvent event) {
        try {
            dataContainer.setAdditionalLanguages(languagesTable.getAdditionalLanguages());
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/managers/loggedEmployeesManager/addEmployee/formerPositionAdding/formerPositionsAddingPane.fxml"));
            Parent root = fxmlLoader.load();
            FormerPositionsAddingController formerPositionsAddingController = fxmlLoader.getController();
            formerPositionsAddingController .setDataContainer(dataContainer);
            formerPositionsAddingController.prepare();
            Main.stage.setScene(new Scene(root));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FXML
    void onDelete(ActionEvent event) {
        try {
        languagesTable.updateRemove(tabLanguage.getSelectionModel().getSelectedItem());
        if(!languagesTable.updateCheck()){
            btnDelete.setDisable(true);
        }}catch (RuntimeException e){
            System.out.println("Brak wybranego elementu");
        }
    }

}
