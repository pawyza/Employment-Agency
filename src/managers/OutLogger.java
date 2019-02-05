package managers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import startPack.Main;

import java.io.IOException;

public class OutLogger {
    public void LogOut(){
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
