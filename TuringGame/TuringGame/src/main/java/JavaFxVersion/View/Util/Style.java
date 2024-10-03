package JavaFxVersion.View.Util;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;


/**
 * The Style class provides static methods for applying custom styles
 * to various JavaFX components used in the application.
 */
public class Style {

    /**
     * Applies a custom style to a TextField.
     * This style includes a filter to only accept numbers from 1 to 5 and limits input to 3 characters.
     *
     * @param tfd The TextField to be styled.
     */
   public static void setTfdStyle(TextField tfd){
        applyIntFilter(tfd);
        tfd.setMinHeight(10);
        tfd.setMaxHeight(300);

        tfd.setMinWidth(60);
        tfd.setMaxWidth(Double.MAX_VALUE);

        // Appliquer le style au TextField
        tfd.setStyle(
                "-fx-font-family: 'Comic Sans MS';" + // Police Comic Sans MS
                        "-fx-font-size: 50;" +               // Taille de la police à 40
                        "-fx-background-color: rgba(220,220,220,0.6);" + // Fond gris clair (light grey)
                        "-fx-alignment: center;" +
                        "-fx-prompt-text-fill: rgba(255, 255, 255, 0.5);"
        );

    }

    private static void applyIntFilter(TextField textField) {
        textField.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            String character = event.getCharacter();
            String currentText = textField.getText();

            // Accepte uniquement les chiffres de 1 à 5.
            if (!character.matches("[1-5]")) {
                event.consume();
            }

            // Limite à 3 chiffres
            if (currentText.length() >= 3) {
                event.consume();
            }
        });
    }


    /**
     * Applies a custom style to a Label used as a title.
     * This style includes font size, color, shadow effect, etc.
     *
     * @param title The Label to be styled.
     */
    public static void setTitleStyle(Label title) {
        title.setStyle(
                "-fx-font-size: 20px;" + // Taille de la police grande
                        "-fx-font-weight: bold;" + // Gras pour un meilleur effet 3D
                        "-fx-text-fill: black;" + // Dégradé de couleur personnalisé pour le texte
                        "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 10);" + // Ombre principale pour l'effet 3D
                        "-fx-effect: dropshadow(one-pass-box, white, 0, 0, 0, 2);" +
                        "-fx-underline: true;" + // Lumière supplémentaire pour renforcer l'effet 3D
                        "-fx-background-color:  rgba(72, 69, 68, 0.31);" +
                        "-fx-border-color: black; " +
                        "-fx-border-width: 2px; " +
                        "-fx-margin: 5px;"
        );

    }


    /**
     * Applies a custom style to a Label.
     * This style includes font size, color, font type, etc.
     *
     * @param label The Label to be styled.
     */
    public static void setLabelStyle(Label label) {
        label.setStyle(
                "-fx-font-size: 20px;" + // Taille de la police
                        "-fx-font-family: 'Comic Sans MS',cursive;" + // Police Comic Sans MS
                        "-fx-text-fill: black;" +// Couleur du texte
                        "-fx-font-weight: bold;" +
                        "-fx-wrap-text: true;"
        );
    }



    /**
     * Applies a custom style to a Button.
     * This style includes background color, text color, font size, etc.
     *
     * @param button The Button to be styled.
     */
    public static void setBtnStyle(Button button) {
        button.setStyle("-fx-background-color: white; " +
                "-fx-text-fill: black; " +
                "-fx-font-family: 'Comic Sans MS'; " +
                "-fx-font-size: 14px; " +
                "-fx-border-color: black; " +
                "-fx-border-width: 2px;" +
                "-fx-border-radius: 20px; " +
                "-fx-background-radius: 20px;" ); // Ajoutez cette ligne
    }


    /**
     * Applies a custom style to a Button when it is pressed.
     * This style changes text color, font size, etc.
     *
     * @param button The Button to be styled when pressed.
     */
    public static void setPressedBtnStyle(Button button) {
        button.setStyle("-fx-text-fill: white; " +
                "-fx-font-size: 14px; " +
                "-fx-border-color: white; " +
                "-fx-border-width: 2px;" +
                "-fx-border-radius: 5px;" +
                "-fx-font-family: 'Comic Sans MS',cursive; ");
    }


    /**
     * Applies a custom style to a Label used for displaying information on the screen.
     *
     * @param label The Label to be styled.
     */
    public static void setScreenLblStyle(Label label) {
        label.setStyle(
                "-fx-font-size: 15px;" + // Taille de la police
                        "-fx-font-family: 'Comic Sans MS';" + // Police Comic Sans MS
                        "-fx-text-fill: black;" +// Couleur du texte
                        "-fx-font-weight: bold;"
        );
    }


    /**
     * Applies a custom style to a VBox used for guess display.
     *
     * @param guessBox The VBox to be styled.
     */
    public static void setGuessBoxStyle(VBox guessBox) {
        guessBox.setStyle("-fx-background-color: rgba(220,220,220,0.5);" +
                "-fx-background-radius: 5px;" +
                "-fx-effect: dropshadow(gaussian, black, 10, 0, 5, 5);");
    }


    /**
     * Applies a custom style to a ChoiceBox.
     * This style includes background color, font size, rounded corners, etc.
     *
     * @param choiceBox The ChoiceBox to be styled.
     */
    public static void setChoiceBoxStyle(ChoiceBox<String> choiceBox) {
        // Appliquez le style général à la ChoiceBox
        choiceBox.setStyle("-fx-background-color: rgba(180,180,180,0.6); " + // fond noir
                "-fx-font-family: 'Comic Sans MS'; " + // police Comic Sans MS
                "-fx-font-size: 14px; " +// taille de police 14px
                "-fx-background-radius: 10; " + // coins arrondis
                "-fx-border-radius: 10; " + // bordure arrondie
                "-fx-border-color: black; " + // bordure noire
                "-fx-effect: dropshadow(three-pass-box, black, 10, 0, 0, 0);"); // ombre noire
    }

}
