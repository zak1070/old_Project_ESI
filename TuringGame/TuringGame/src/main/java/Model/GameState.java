package Model;

import Model.Validator.Validator;

import java.util.List;

/**
 * The GameState record holds the state of a game at a given moment.
 * It includes information about the game's progress, such as whether the game is over,
 * if the code has been found, the current round, the number of validators tested,
 * and details about the validators and previously entered codes.
 */
public record GameState(
        boolean gameOver,                  // Indicates if the game is over
        boolean isCodeFounded,             // Indicates if the code has been successfully found
        int round,                         // The current round of the game
        int nbValidatorsTested,// The number of validators that have been tested
        int redoSize,
        List<Validator> validatorOfProblem, // A list of all validators involved in the problem
        List<Validator> validatorOK,       // A list of validators that successfully validated the code
        List<Validator> validatorFalse,    // A list of validators that failed to validate the code
        String previousCodes               // A string representation of previously entered codes
) {
    // The record implicitly declares a constructor with all fields as parameters,
    // as well as getters for each field.
}
