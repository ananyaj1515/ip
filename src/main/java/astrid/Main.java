package astrid;

import java.io.IOException;

import astrid.ui.AstridGlowspell;
import astrid.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for AstridGlowspell using FXML.
 *
 * This is the entry point for the JavaFX application. It loads the FXML layout,
 * initializes the chatbot instance, and displays the main window with proper
 * scene management.
 */
public class Main extends Application {

    private AstridGlowspell chatbot = new AstridGlowspell();

    /**
     * Starts the JavaFX application by loading the FXML layout and setting up the UI.
     *
     * Loads the MainWindow.fxml file, creates a scene with it, and injects the
     * chatbot instance into the controller. Also sets up a close request handler
     * to save tasks when the window is closed.
     *
     * @param stage the primary stage for this application
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Astrid Sees ✨");
            stage.setScene(scene);

            fxmlLoader.<MainWindow>getController().setDuke(chatbot);
            stage.show();
            stage.setOnCloseRequest(event -> {
                chatbot.save();
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
