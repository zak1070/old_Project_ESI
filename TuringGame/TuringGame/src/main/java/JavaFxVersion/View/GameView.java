package JavaFxVersion.View;

import JavaFxVersion.View.Util.Style;
import Util.Observable;
import Model.GameState;
import Util.Observer;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.util.Objects;

/**
 * GameView extends BorderPane and implements Observer to provide a comprehensive view for the game.
 * It contains views for validators, code input, action buttons, and messages, and updates based on the game state.
 */
public class GameView extends BorderPane implements Observer {
    private ValidatorsView validatorsView;
    private CodeInputView codeInputView;
    private ActionButtonsView actionButtonsView;
    private MessageAreaView messageAreaView;
    private MenuItem giveUPItm;
    private TextField codeTfd;
    private Button guessCodeButton;

    /**
     * Constructs a GameView and sets up its layout and components.
     *
     * @param observable The observable object to be observed, typically the game state.
     */
    public GameView(Observable observable) {
        observable.addObserver(this);
        initializeViews(observable);
        createMenu();
    }

    /**
     * Initializes the different views and components of the game view.
     *
     * @param observable The observable object to pass to the components that require it.
     */
    private void initializeViews(Observable observable) {
        this.validatorsView = new ValidatorsView(observable);
        this.codeInputView = new CodeInputView();
        this.actionButtonsView = new ActionButtonsView(observable);
        this.messageAreaView = new MessageAreaView(observable);
        this.codeTfd = new TextField();
        this.guessCodeButton = new Button(" GUESS CODE");

        createMenu();

        Label title = new Label("TURING GAME");
        Style.setTitleStyle(title);

        VBox contentBox = new VBox();
        contentBox.setAlignment(Pos.CENTER);
        contentBox.setSpacing(5);
        setVBoxStyle(contentBox);

        HBox msgAndInput = new HBox();
        configureBottomHBox(msgAndInput, codeInputView);
        contentBox.getChildren().addAll(title, validatorsView, actionButtonsView, msgAndInput);

        codeTfd.setPromptText("Enter the code if you're sure:");
        Style.setTfdStyle(codeTfd);
        Style.setBtnStyle(guessCodeButton);

        guessCodeButton.setOnMousePressed(event -> Style.setPressedBtnStyle(guessCodeButton));
        guessCodeButton.setOnMouseReleased(event -> Style.setBtnStyle(guessCodeButton));

        // Create the StackPane and set the background
        StackPane stackPaneWithBackground = new StackPane();
        stackPaneWithBackground.getChildren().add(contentBox);
        setStackPaneBackground(stackPaneWithBackground);

        // Create the ScrollPane and set the StackPane as its content
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        scrollPane.setContent(stackPaneWithBackground); // Set the content

        // Set the ScrollPane as the center component of the BorderPane
        this.setCenter(scrollPane);

        Label screenInfo = new Label("Press F to switch the full screen mode");
        Style.setScreenLblStyle(screenInfo);
        this.setBottom(screenInfo);
    }


    /**
     * Configures the layout for the bottom section of the game view.
     *
     * @param msgAndInput      The HBox container for the message area and code inputs.
     * @param codeLblAndInputs The VBox containing the code label and inputs.
     */
    private void configureBottomHBox(HBox msgAndInput, VBox codeLblAndInputs) {
        // Configurer l'espacement et l'alignement pour le conteneur
        msgAndInput.setSpacing(10);
        msgAndInput.setAlignment(Pos.TOP_CENTER);
        msgAndInput.setFillHeight(true); // S'assurer que le HBox remplit la hauteur disponible


        VBox guessBox = new VBox(10); // Supposer un espacement de 10 entre les éléments
        guessBox.setAlignment(Pos.TOP_CENTER);
        guessBox.setPadding(new Insets(10));
        guessBox.setFillWidth(true); // S'assurer que la VBox étend ses enfants pour remplir sa largeur
        Style.setGuessBoxStyle(guessBox); // Appliquer le style à guessBox

        // Créer et styliser le label
        Label guessLbl = new Label("GUESS YOUR CODE HERE :");
        Style.setLabelStyle(guessLbl); // Appliquer le style au label


        guessBox.getChildren().addAll(guessLbl, codeTfd, guessCodeButton);

        // Définir la croissance verticale pour que chaque enfant de la VBox prenne toute la hauteur
        VBox.setVgrow(guessLbl, Priority.ALWAYS);
        VBox.setVgrow(codeTfd, Priority.ALWAYS);
        guessCodeButton.setMaxWidth(Double.MAX_VALUE);
        VBox.setVgrow(guessCodeButton, Priority.ALWAYS);

        // Ajouter tous les éléments enfants à msgAndInput HBox
        msgAndInput.getChildren().addAll(messageAreaView, codeLblAndInputs, guessBox);

        // Définir les priorités de croissance horizontale
        HBox.setHgrow(messageAreaView, Priority.NEVER); // messageAreaView ne s'étendra pas
        HBox.setHgrow(guessBox, Priority.ALWAYS); // guessBox s'étendra toujours
    }


    /**
     * Sets the style for a VBox container.
     *
     * @param vbox The VBox to style.
     */
    private void setVBoxStyle(VBox vbox) {
        // Set padding for the VBox
        vbox.setPadding(new Insets(5, 10, 20, 10)); // Top, Right, Bottom, Left
    }


    /**
     * Sets the background image for a StackPane.
     *
     * @param stackPane The StackPane to set the background for.
     */
    private void setStackPaneBackground(StackPane stackPane) {

        String imageUrl = Objects.requireNonNull(getClass().getResource("/pictures/wallpaper.png")).toExternalForm();
        stackPane.setStyle("-fx-background-image: url('" + imageUrl + "'); " +
                "-fx-background-position: center center; " +
                "-fx-background-repeat: no-repeat; " +
                "-fx-background-size: cover;");
    }


    /**
     * Creates the top menu bar for the game view.
     */
    private void createMenu() {
        MenuBar menuBar = new MenuBar();

        Menu fileMenu = new Menu("File");
        giveUPItm = new MenuItem("GiveUP");

        fileMenu.getItems().add(giveUPItm);
        menuBar.getMenus().add(fileMenu);

        menuBar.setStyle("-fx-background-color: rgba(72, 69, 68, 0.41);" +
                "-fx-border-color: black; " +
                "-fx-border-width: 2px 0 2px 0; "); // 2px en haut et en bas, 0 à gauche et à droite

        this.setTop(menuBar);
    }


    /**
     * Updates the view based on changes in the observed GameState.
     *
     * @param observable The observable object.
     * @param obj        The updated object, expected to be an instance of GameState.
     */
    @Override
    public void update(Observable observable, Object obj) {
        if (obj instanceof GameState) {
            GameState gameState = (GameState) obj;

            validatorsView.update(observable, gameState);
            messageAreaView.update(observable, gameState);
            actionButtonsView.update(observable, gameState);
            if (gameState.redoSize() == 0 || gameState.round() != 0) {
                displayAlert(Alert.AlertType.INFORMATION, "Redo Stack is empty", "Redo stack is empty", "the stack size =0");
            }
        }
    }

    private void displayAlert(Alert.AlertType alertType, String title, String header, String content) {
        CustomAlert alert = new CustomAlert(alertType, title, header, content);
        alert.showAndWait();
    }

    /**
     * Gets the ValidatorsView component of the game view.
     *
     * @return The ValidatorsView instance used in the game view.
     */
    public ValidatorsView getValidatorsView() {
        return this.validatorsView;
    }

    /**
     * Gets the CodeInputView component of the game view.
     *
     * @return The CodeInputView instance used in the game view.
     */
    public CodeInputView getCodeInputView() {
        return this.codeInputView;
    }

    /**
     * Gets the ActionButtonsView component of the game view.
     *
     * @return The ActionButtonsView instance used in the game view.
     */
    public ActionButtonsView getActionButtonsView() {
        return this.actionButtonsView;
    }

    /**
     * Gets the 'Give Up' menu item of the game view.
     *
     * @return The MenuItem instance for giving up in the game.
     */
    public MenuItem getGiveUPItm() {
        return giveUPItm;
    }

    /**
     * Gets the 'Guess Code' button of the game view.
     *
     * @return The Button instance used for guessing the code.
     */
    public Button getGuessCodeButton() {
        return guessCodeButton;
    }

    /**
     * Gets the TextField for code input in the game view.
     *
     * @return The TextField instance used for code input.
     */
    public TextField getCodeTfd() {
        return codeTfd;
    }
}