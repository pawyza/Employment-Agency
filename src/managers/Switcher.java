package managers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import startPack.Main;

import java.io.IOException;

public class Switcher {
    /**
     * metoda zmieniajaca aktualny panel na nowy
     * @param path nowa sciezka do nowego panelu
     */
    public void change(String path){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource(path));
            Parent root = fxmlLoader.load();
            Main.stage.setScene(new Scene(root));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    /**
     * Metoda cofajaca do ekranu logowania
     */
    public void logOut(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/startPack/loginPanel.fxml"));
            Parent root = fxmlLoader.load();
            Main.stage.setScene(new Scene(root));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
