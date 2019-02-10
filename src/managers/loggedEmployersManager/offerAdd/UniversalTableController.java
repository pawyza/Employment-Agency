package managers.loggedEmployersManager.offerAdd;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.ArrayList;

public class UniversalTableController {

    private TableView<String> table;
    private TableColumn<String, String> column;
    private ObservableList<String> observable = FXCollections.observableArrayList();

    public UniversalTableController(TableView<String> table, TableColumn column){
        this.table = table;
        this.column = column;
        this.column.setCellValueFactory(cellData ->
            new ReadOnlyStringWrapper(cellData.getValue()));
        this.table.setItems(observable);
}

    public void add(String permission){
        if(!checkIfDuplicate(permission)) {
            observable.add(permission);
        }
    }

    public void delete(String permission) throws RuntimeException{
        observable.remove(permission);
    }

    public ArrayList<String> getObservable(){
        return new ArrayList<>(observable);
    }

    private boolean checkIfDuplicate(String permission){
        for (String p: observable) {
            if(p.equals(permission)){
                return true;
            }
        }
        return false;
    }
}
