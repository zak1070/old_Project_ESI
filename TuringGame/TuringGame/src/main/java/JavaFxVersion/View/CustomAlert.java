package JavaFxVersion.View;

import JavaFxVersion.View.Util.Style;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

/**
 * CustomAlert extends the JavaFX Alert class to provide a custom styled alert dialog.
 * It allows for customizing the appearance and adding images based on the alert type.
 */
public class CustomAlert extends Alert {

    /**
     * Constructs a CustomAlert with specified alert type, title, header text, and content text.
     * Applies custom styles and sets an image based on the alert type.
     *
     * @param alertType    The type of alert (Error, Information, etc.).
     * @param title        The title of the alert dialog.
     * @param headerText   The header text of the alert.
     * @param contentText  The content text of the alert.
     */
    public CustomAlert(AlertType alertType, String title, String headerText, String contentText) {
        super(alertType);
        setTitle(title);
        setHeaderText(headerText);
        setContentText(contentText);
        setAlertStyle();
        setImageForAlertType(alertType);
    }

    /**
     * Sets the style for the alert dialog.
     * Styles the background, font size, and text color of the dialog pane and its content.
     */
    private void setAlertStyle() {
        getDialogPane().setStyle("-fx-background-color: gray; -fx-font-size: 16px;");
        getDialogPane().lookup(".content.label").setStyle("-fx-text-fill: white; -fx-font-size: 16px; -fx-font-family: 'Arial';");
        styleAlertButtons();
    }


    /**
     * Applies custom styles to the buttons in the alert dialog.
     * Styles each button according to the defined styles in the Style class.
     */
    private void styleAlertButtons() {
        ButtonBar buttonBar = (ButtonBar) getDialogPane().lookup(".button-bar");
        buttonBar.getButtons().forEach(button -> {
            if (button instanceof Button) {
                Button btn = (Button) button;
                Style.setBtnStyle(btn);
                btn.setOnMousePressed(e -> Style.setPressedBtnStyle(btn));
                btn.setOnMouseReleased(e -> Style.setBtnStyle(btn));
            }
        });
    }


    /**
     * Sets an image for the alert dialog based on its alert type.
     * Chooses different images for error and information alert types.
     *
     * @param alertType The type of alert, used to determine which image to display.
     */
    private void setImageForAlertType(AlertType alertType) {
        ImageView imageView = null;
        if (alertType == AlertType.ERROR) {
            imageView = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/robots/robotC.png"))));
        } else if (alertType == AlertType.INFORMATION) {
            imageView = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/robots/robotA.png"))));
        }

        if (imageView != null) {
            imageView.setFitHeight(100);
            imageView.setFitWidth(100);
            setGraphic(imageView);
        }
    }
}
