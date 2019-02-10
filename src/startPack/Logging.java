package startPack;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import managers.LoggedUser;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Logging {
    private String login;
    private String password;
    private Connection connection;

    /**
     * konstruktor klasy login
     * @param login
     * @param password
     */
    public Logging(String login, String password) {
        this.login = login;
        this.password = password;
    }

    /**
     * Metoda odpowiadajaca za przygotowanie polaczenia z baza
     * @throws SQLException blad wyrzucany gdy polaczenie jest niepoprawne
     */
    public void checkLogin() throws SQLException {
            //connection =  DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=EmploymentAgency;user=" + login + ";password=" + password);
            connection =  DriverManager.getConnection("jdbc:sqlserver://projektpwr2.database.windows.net:1433;database=EmploymentAgency;user=" + login + "@projektpwr2;password=" + password + ";encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;");
    }

    /**
     * Metoda sprawdzajaca ktory interfejs nalezy zaladowac
     * @throws NullPointerException
     */
    public void logIn() throws NullPointerException{
        switch(login) {
            case "ManPracownikow":
                typeChanger("/managers/loggedEmployeesManager/employeesManagerPanel.fxml");
                return;
            case "ManPracodawcow":
                typeChanger("/managers/loggedEmployersManager/employersManagerPanel.fxml");
                return;
            case "ManInformacji":
                typeChanger("/managers/loggedInformationManager/informationManagerPanel.fxml");
                return;
        }
        throw new NullPointerException();
    }

    /**
     * Metoda ustawiajaca nowy interfejs oraz podajaca polaczenie
     * @param type sciezka do interfejsu
     */
    private void typeChanger(String type){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource(type));
            Parent root = fxmlLoader.load();
            LoggedUser loggedUser = fxmlLoader.getController();
            loggedUser.setConnection(connection);
            Main.stage.setScene(new Scene(root));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
