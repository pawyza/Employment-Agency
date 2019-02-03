package managers.loggedEmployeesManager.addEmployee.result;

import managers.loggedEmployeesManager.addEmployee.DataContainer;

import java.sql.Connection;

public class AddingResultController {
    private Connection connection;

    private DataContainer dataContainer;
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void setDataContainer(DataContainer dataContainer) {
        this.dataContainer = dataContainer;
    }
}
