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

    public Logging(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public void checkLogin() throws SQLException {
            connection =  DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=EmploymentAgency;user=" + login + ";password=" + password);
    }

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
