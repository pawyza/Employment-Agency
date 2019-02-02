package managers.loggedEmployeesManager.employ;

import java.sql.Connection;

public class EmployController {
    private Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
