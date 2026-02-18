package astrid.ui;

import javafx.animation.PauseTransition;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Represents the main window of the AstridGlowspell GUI application.
 * Manages the layout and interaction between the user input field, dialog container,
 * and the chatbot instance. Displays messages from both the user and the chatbot
 * in a scrollable dialog container.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private AstridGlowspell chatbot;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/me.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/astrid.png"));


    /**
     * Initializes the main window by binding the vertical scroll value of the scroll pane
     * to the height of the dialog container. This ensures the scroll pane automatically
     * scrolls to the bottom as new messages are added.
     */
    @FXML
    public void initialize() {

        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

    }

    /**
     * Injects the AstridGlowspell chatbot instance and displays the initial greeting message.
     * This method should be called after the window is initialized to set up the chatbot.
     *
     * @param d the AstridGlowspell instance to use for handling user commands
     */
    public void setDuke(AstridGlowspell d) {

        chatbot = d;
        dialogContainer.getChildren().add(
                DialogBox.getAstridDialog(chatbot.greet(), dukeImage)
        );
    }

    /**
     * Handles user input from the text field. Retrieves the user's input, gets the chatbot's
     * response, and displays both in the dialog container as separate dialog boxes.
     * Clears the input field after processing.
     *
     * If the user input is "bye", schedules the application window to close after 2 seconds,
     * allowing time for the user to see the final response.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = chatbot.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getAstridDialog(response, dukeImage)
        );
        userInput.clear();

        if (input.trim().equalsIgnoreCase("bye")) {
            PauseTransition delay = new PauseTransition(Duration.seconds(2));
            delay.setOnFinished(event -> {
                Stage stage = (Stage) sendButton.getScene().getWindow();
                stage.close();
            });
            delay.play();
        }
    }
}
