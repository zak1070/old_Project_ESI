package JavaFxVersion.View;

import JavaFxVersion.View.Util.Style;
import Loader.Loader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.util.Objects;


/**
 * ProblemChoiceView extends BorderPane to provide a user interface for selecting a problem.
 * It contains a ChoiceBox for problem selection and a button to confirm the selection.
 */
public class ProblemChoiceView extends BorderPane {
    Button selectButton;
    ChoiceBox<String> problemsChoiceBox;

    /**
     * Constructs a ProblemChoiceView with a list of problems loaded from the Loader class.
     * Configures the layout, styles, and functionalities of the components.
     */
    public ProblemChoiceView() {
        problemsChoiceBox = new ChoiceBox<>();
        problemsChoiceBox.getItems().add("0 - Random"); // Ajout de l'option Random en premier

        String allProblems = Loader.listAllProblems(); // Récupère la chaîne des problèmes
        String[] problemsArray = allProblems.split("\n");
        for (String problem : problemsArray) {
            problemsChoiceBox.getItems().add(problem); // Ajoute chaque problème à la ChoiceBox
        }
        Style.setChoiceBoxStyle(problemsChoiceBox);

        // Création du bouton de sélection
        this.selectButton = new Button("Choose");
        applyButtonStyle(selectButton);

        VBox vbox = new VBox(5, problemsChoiceBox, selectButton);
        vbox.setAlignment(Pos.CENTER); // Centre les éléments de la VBox
        vbox.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
        // Création du layout principal

        this.setCenter(vbox);
        setAlignment(problemsChoiceBox, Pos.CENTER);
        this.setPadding(new Insets(20));

        // Configuration de l'image en arrière-plan
        ImageView imageView = new ImageView(new Image(Objects.requireNonNull(getClass().
                getResourceAsStream("/pictures/turing.png"))));

        BackgroundImage backgroundImage = new BackgroundImage(
                imageView.getImage(),
                BackgroundRepeat.NO_REPEAT,  // Pas de répétition horizontale
                BackgroundRepeat.NO_REPEAT,  // Pas de répétition verticale
                BackgroundPosition.CENTER,   // Positionne l'image au centre
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true) // Couvre l'écran
        );
       this.setBackground(new Background(backgroundImage));
    }

    /**
     * Applies a predefined style to a button and configures its mouse event actions.
     *
     * @param button The button to apply the style to.
     */
    private void applyButtonStyle(Button button) {
       Style.setBtnStyle(button);
        button.setOnMousePressed(event -> Style.setPressedBtnStyle(button));
        button.setOnMouseReleased(event -> Style.setBtnStyle(button));
    }

    /**
     * Gets the ChoiceBox used for problem selection.
     *
     * @return The ChoiceBox for selecting problems.
     */
    public ChoiceBox<String> getProblemsChoiceBox() {
        return this.problemsChoiceBox;
    }

    /**
     * Gets the button used to confirm the problem selection.
     *
     * @return The button for choosing the selected problem.
     */
    public Button getSelectButton() {
        return this.selectButton;
    }
}
