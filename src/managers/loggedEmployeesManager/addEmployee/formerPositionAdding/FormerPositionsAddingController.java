package managers.loggedEmployeesManager.addEmployee.formerPositionAdding;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import managers.LoggedUser;
import managers.loggedEmployeesManager.addEmployee.DataContainer;
import managers.loggedEmployeesManager.addEmployee.permissionAdding.PermissionsAddingController;
import startPack.Main;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class FormerPositionsAddingController {
    private DataContainer dataContainer;
    private PositionsTable positionTable;

    public void setDataContainer(DataContainer dataContainer) {
        this.dataContainer = dataContainer;
    }

    public void prepare(){
        choiceTrade.setItems(FXCollections.observableList(dataContainer.getTrade()));
        choiceTrade.getSelectionModel().selectedIndexProperty().addListener((observableValue, number, t1) -> {
            preparePositions(dataContainer.getTrade().get(t1.intValue()));
                });
        positionTable = new PositionsTable(tabPosition,colPosition,colTrade);
        choicePosition.getSelectionModel().selectedItemProperty().addListener((observableValue, number, t2) -> {
            if (!(t2 == null))
            positionTable.updateAdd(choicePosition.getValue(),choiceTrade.getValue());
            });
    }

    @FXML
    private ChoiceBox<String> choiceTrade;

    @FXML
    private ChoiceBox<String> choicePosition;

    @FXML
    private TableView<Positions> tabPosition;

    @FXML
    private TableColumn<Positions, ArrayList<String>> colPosition;

    @FXML
    private TableColumn<Positions, ArrayList<String>> colTrade;

    @FXML
    void next(ActionEvent event) {
        try {
            dataContainer.setFormerPositions(positionTable.getPositions());
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/managers/loggedEmployeesManager/addEmployee/permissionAdding/permissionsAddingPane.fxml"));
            Parent root = fxmlLoader.load();
            PermissionsAddingController permissionsAddingController = fxmlLoader.getController();
            permissionsAddingController.setDataContainer(dataContainer);
            permissionsAddingController.prepare();
            Main.stage.setScene(new Scene(root));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FXML
    void onDelete(ActionEvent event) {
        try {
        positionTable.updateRemove(tabPosition.getSelectionModel().getSelectedItem());
        }catch (RuntimeException e){
        System.out.println("Brak wybranego elementu");
    }
    }

    private void preparePositions(String trade) {
        ArrayList<String> positions = new ArrayList<>();
        try {
            Statement statement = LoggedUser.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT STANOWISKO FROM STANOWISKO_PELNE WHERE BRANZA='"+trade+"'");
            while (resultSet.next()){
                positions.add(resultSet.getString("STANOWISKO"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(!positions.isEmpty())
        choicePosition.setItems(FXCollections.observableList(positions));
    }
}
