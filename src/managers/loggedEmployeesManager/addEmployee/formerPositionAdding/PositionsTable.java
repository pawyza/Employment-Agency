package managers.loggedEmployeesManager.addEmployee.formerPositionAdding;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class PositionsTable {

    private TableView<Positions> tabPositions;
    private TableColumn<Positions, String> colPosition;
    private TableColumn<Positions, String> colTrade;
    private ObservableList<Positions> observablePosition = FXCollections.observableArrayList();

    public PositionsTable(TableView<Positions> tableView, TableColumn<Positions, String> colPosition, TableColumn<Positions, String>  colTrade) {
        this.tabPositions = tableView;
        this.colPosition = colPosition;
        this.colTrade = colTrade;
        this.colPosition.setCellValueFactory(new PropertyValueFactory<>("position"));
        this.colTrade.setCellValueFactory(new PropertyValueFactory<>("trade"));
        this.tabPositions.setItems(observablePosition);
    }

    public void updateAdd(String position,String trade){
        if(!checkIfDuplicate(position,trade))
        observablePosition.add(new Positions(position,trade));
    }
    public void updateRemove(Positions position) throws RuntimeException{
            observablePosition.remove(position);
    }

    public ArrayList<String> getPositions(){
        ArrayList<String> positions = new ArrayList<>();
        for (Positions p: observablePosition) {
                positions.add(p.getPosition());
        }
        return positions;
    }

    private boolean checkIfDuplicate(String position,String trade){
        for (Positions p : observablePosition ) {
            if(p.getPosition().equals(position) && p.getTrade().equals(trade)){
                return true;
            }
        }
        return false;
    }
}
