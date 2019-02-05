package managers.loggedEmployeesManager.addEmployee.permissionAdding;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.ArrayList;

public class PermissionsTable {

    private TableView<String> tabPermissions;
    private TableColumn<String, String> colPermission;
    private ObservableList<String> observablePermissions = FXCollections.observableArrayList();

    public PermissionsTable(TableView<String> tableView, TableColumn<String, String> colPermission) {
        this.tabPermissions = tableView;
        this.colPermission = colPermission;
        this.colPermission.setCellValueFactory(cellData ->
                new ReadOnlyStringWrapper(cellData.getValue()));
        this.tabPermissions.setItems(observablePermissions);
    }

    public void updateAdd(String permission){
        if(!checkIfDuplicate(permission)) {
            observablePermissions.add(permission);
        }
    }

    public void updateRemove(String permission) throws RuntimeException{
            observablePermissions.remove(permission);
    }

    public ArrayList<String> getPermissions(){
        return new ArrayList<>(observablePermissions);
    }

    private boolean checkIfDuplicate(String permission){
        for (String p: observablePermissions) {
            if(p.equals(permission)){
                return true;
            }
        }
        return false;
    }
}
