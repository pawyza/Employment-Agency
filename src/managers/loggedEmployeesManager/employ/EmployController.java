package managers.loggedEmployeesManager.employ;

import managers.loggedEmployeesManager.addEmployee.DataContainer;

import java.sql.Connection;

public class EmployController {
    private Connection connection;

    private DataContainer dataContainer;
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void setDataContainer(DataContainer dataContainer) {
        this.dataContainer = dataContainer;
    }
}
