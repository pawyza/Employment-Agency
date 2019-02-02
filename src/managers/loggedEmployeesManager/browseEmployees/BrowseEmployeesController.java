package managers.loggedEmployeesManager.browseEmployees;

import java.sql.Connection;

public class BrowseEmployeesController {
    private Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
