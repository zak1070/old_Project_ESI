package JavaFxVersion.View;

import JavaFxVersion.View.Util.Style;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import java.util.Objects;

/**
 * The CodeInputView class extends VBox to create a custom input view for entering a code.
 * It contains buttons for each digit, labels, and an enter button, along with associated styling and functionality.
 */
public class CodeInputView extends VBox {
    private static final String DEFAULT_STYLE = "-fx-background-color: white; " +
            "-fx-text-fill: black; " +
            "-fx-font-size: 14px; " +
            "-fx-border-color: black; " +
            "-fx-border-width: 2px;" +
            "-fx-border-radius: 5px;" +
            "-fx-font-family: 'Comic Sans MS'; ";

    private final Button[][] numberButtons;  // Boutons pour les chiffres
    private final int[] selectedValues;  // Les valeurs sélectionnées pour chaque colonne du code
    private Button enterButton;

    /**
     * Constructor for CodeInputView. Initializes the layout, buttons, labels, and styles.
     */
    public CodeInputView() {
        this.numberButtons = new Button[5][3];
        this.selectedValues = new int[3];

        initializeLayout();
        initializeCodeLabel();
        initializeButtonColumns();
        initializeEnterButton();
        initializeStyle();
    }

    /**
     * Initializes the layout properties of the view.
     */
    private void initializeLayout() {
        setAlignment(Pos.TOP_CENTER);
        setSpacing(10);
        setPadding(new Insets(10));
    }

    /**
     * Initializes the label that prompts users to enter their code.
     */
    private void initializeCodeLabel() {
        Label codeLbl = new Label("ENTER YOUR CODE HERE: ");
        Style.setLabelStyle(codeLbl);
        getChildren().add(codeLbl);
    }

    /**
     * Initializes the columns of buttons for each digit.
     */
    private void initializeButtonColumns() {
        VBox[] vBoxes = new VBox[3];
        for (int i = 0; i < 3; i++) {
            vBoxes[i] = new VBox(10);
            configureButtonColumn(vBoxes[i], i);
        }

        HBox numberButtonContainer = new HBox(10);
        numberButtonContainer.setAlignment(Pos.CENTER);
        numberButtonContainer.getChildren().addAll(vBoxes[0], vBoxes[1], vBoxes[2]);
        getChildren().add(numberButtonContainer);
    }

    /**
     * Configures each column of buttons, adding an image and buttons for digits.
     *
     * @param column      The VBox representing a column of digit buttons.
     * @param columnIndex The index of the column.
     */
    private void configureButtonColumn(VBox column, int columnIndex) {
        column.setAlignment(Pos.CENTER);
        ImageView img = new ImageView();
        configureImageView(img, columnIndex);
        column.getChildren().add(img);
        for (int j = 0; j < 5; j++) {
            Button button = new Button(String.valueOf(j + 1));
            configureButton(button, j, columnIndex);
            column.getChildren().add(button);
        }
    }


    /**
     * Configures the ImageView for a specific column.
     *
     * @param img The ImageView to configure.
     * @param columnIndex The index of the column.
     */
    private void configureImageView(ImageView img, int columnIndex) {
        img.setPreserveRatio(true);
        img.setFitHeight(30);
        setImageForColumn(img, columnIndex);
    }


    /**
     * Configures a single digit button with style and behavior.
     *
     * @param button The button to configure.
     * @param row The row index of the button.
     * @param column The column index of the button.
     */
    private void configureButton(Button button, int row, int column) {
        button.setMinSize(40, 40);
        button.setMaxSize(45, 45);
        setButtonStyle(button, row, column);
        numberButtons[row][column] = button;
    }


    /**
     * Initializes the 'Enter Code' button.
     */
    private void initializeEnterButton() {
        enterButton = new Button("ENTER CODE");
        Style.setBtnStyle(enterButton);
        enterButton.setOnMousePressed(event -> Style.setPressedBtnStyle(enterButton));
        enterButton.setOnMouseReleased(event -> Style.setBtnStyle(enterButton));

        HBox enterCodeContainer = new HBox();
        enterCodeContainer.setAlignment(Pos.CENTER);
        enterCodeContainer.getChildren().add(enterButton);
        getChildren().add(enterCodeContainer);
    }

    /**
     * Sets the image for a column based on its index.
     *
     * @param imageView The ImageView to set the image for.
     * @param columnIndex The index of the column.
     */
    private void setImageForColumn(ImageView imageView, int columnIndex) {
        switch (columnIndex) {
            case 0:
                imageView.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/digitShapes/blueTriangle.png"))));
                break;
            case 1:
                imageView.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/digitShapes/yellowSquare.png"))));
                break;
            case 2:
                imageView.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/digitShapes/purpleCircle.png"))));
                break;
        }
    }



    private void initializeStyle() {
        this.setStyle(
                "-fx-background-color: rgba(220,220,220,0.5);" +
                        "-fx-background-radius: 5px;" +  // Bordures arrondies pour l'arrière-plan
                        "-fx-border-radius: 5px;" +      // Bordures arrondies pour la bordure
                        "-fx-effect: dropshadow(gaussian, black, 10, 0, 5, 5);" // Effet d'ombre
        );
    }

    /**
     * Applies the default style and functionality to a digit button.
     *
     * @param button The button to style.
     * @param rowIndex The row index of the button.
     * @param columnIndex The column index of the button.
     */
    private void setButtonStyle(Button button, int rowIndex, int columnIndex) {
        button.setStyle(DEFAULT_STYLE);

        // Event handler for button clicks
        button.setOnMouseClicked(event -> {
            resetButtonStyles(columnIndex);
           selectedValues[columnIndex] = rowIndex + 1;
            button.setStyle("-fx-text-fill: white; " +
                    "-fx-font-size: 14px; " +
                    "-fx-border-color: white; " +
                    "-fx-border-width: 2px;" +
                    "-fx-border-radius: 2px;" +
                    "-fx-font-family: 'Comic Sans MS'; ");
        });
    }

    /**
     * Resets the styles of all buttons in a column except the selected one.
     *
     * @param columnIndex The index of the column to reset button styles.
     */
   private void resetButtonStyles(int columnIndex) {
        for (int i = 0; i < numberButtons.length; i++) {
            if (selectedValues[columnIndex] != i + 1) {
                Button btn = numberButtons[i][columnIndex];
                btn.setStyle(DEFAULT_STYLE);
            }
        }
    }

    /**
     * Gets the array of number buttons used for code input.
     *
     * @return A 2D array of Buttons representing the number buttons.
     */
    public Button[][] getNumberButtons() {
        return this.numberButtons;
    }


    /**
     * Gets the array of selected values from the number buttons.
     *
     * @return An array of integers representing the selected values.
     */
    public int[] getSelectedValues() {
        return this.selectedValues;
    }


    /**
     * Gets the 'Enter Code' button.
     *
     * @return The 'Enter Code' button.
     */
    public Button getEnterButton() {
        return this.enterButton;
    }

}