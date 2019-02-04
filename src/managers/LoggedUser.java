package managers;

import java.sql.Connection;

public class LoggedUser {
    private static Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public static Connection getConnection() {
        return connection;
    }
}
