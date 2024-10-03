package JavaFxVersion.Controller;

import Model.GameFacade;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 * The CodeInputHandler class implements EventHandler to handle code input actions in a JavaFX application.
 * It processes button clicks for entering a numerical code in the game.
 */
public class CodeInputHandler implements EventHandler<ActionEvent> {

    private final GameFacade gameFacade;
    private final Button[][] numberButtons;
    private final int[] selectedValues;
    private final Button enterCodeButton;


    /**
     * Constructor for CodeInputHandler.
     * Initializes the handler with the game facade, number buttons, selected values array, and the enter code button.
     *
     * @param gameFacade The facade of the game logic.
     * @param numberButtons A 2D array of Buttons representing the number buttons.
     * @param selectedValues An array to store selected values.
     * @param enterCodeButton The button used to enter the code.
     */
    public CodeInputHandler(GameFacade gameFacade, Button[][] numberButtons, int[] selectedValues, Button enterCodeButton) {
        this.gameFacade = gameFacade;
        this.numberButtons = numberButtons;
        this.selectedValues = selectedValues;
        this.enterCodeButton = enterCodeButton;
    }


    /**
     * Handles ActionEvents triggered by buttons.
     * This method distinguishes between number buttons and the enter code button and performs actions accordingly.
     *
     * @param actionEvent The action event to be handled.
     */    @Override
    public void handle(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();

        if (source instanceof Button) {
            Button clickedButton = (Button) source;
            processButtonAction(clickedButton);
        }
    }

    private void processButtonAction(Button clickedButton) {
        if (isNumberButton(clickedButton)) {
            handleNumberButtonAction(clickedButton);
        } else if (clickedButton == enterCodeButton) {
            enterCode();
        }
    }

    private boolean isNumberButton(Button button) {
        for (int col = 0; col < numberButtons[0].length; col++) {
            for (int row = 0; row < numberButtons.length; row++) {
                if (numberButtons[row][col] == button) {
                    return true;
                }
            }
        }
        return false;
    }

    private void handleNumberButtonAction(Button clickedButton) {
        int column = getColumnForButton(clickedButton);
        updateSelectedValues(clickedButton, column);

    }

    private int getColumnForButton(Button button) {
        for (int col = 0; col < numberButtons[0].length; col++) {
            for (int row = 0; row < numberButtons.length; row++) {
                if (numberButtons[row][col] == button) {
                    return col;
                }
            }
        }
        return -1;
    }

    private void updateSelectedValues(Button selectedButton, int column) {
        int selectedValue = Integer.parseInt(selectedButton.getText());
        selectedValues[column] = selectedValue;
    }

    private void enterCode() {
        int code = calculateCode();
        gameFacade.enterUserCode(code);
    }

    private int calculateCode() {
        return selectedValues[0] * 100 + selectedValues[1] * 10 + selectedValues[2];
    }
}
