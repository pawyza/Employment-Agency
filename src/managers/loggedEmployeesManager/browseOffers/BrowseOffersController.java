package managers.loggedEmployeesManager.browseOffers;

import java.sql.Connection;

public class BrowseOffersController {
    private Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
