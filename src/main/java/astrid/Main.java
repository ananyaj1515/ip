package astrid;

import astrid.ui.AstridGlowspell;
import astrid.ui.MainWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.io.IOException;

import javafx.fxml.FXMLLoader;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private AstridGlowspell chatbot = new AstridGlowspell();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Astrid Sees ✨");  // <-- ADD THIS
            stage.setScene(scene);



            fxmlLoader.<MainWindow>getController().setDuke(chatbot);  // inject the Duke instance
            stage.show();
            stage.setOnCloseRequest(event -> {
                chatbot.save();
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}