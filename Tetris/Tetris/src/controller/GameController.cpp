#include "GameController.h"
#include <iostream>
#include <sstream>

GameController::GameController(const ConsoleView& view) : view(std::make_unique<ConsoleView>(view)) {}

void GameController::initializeGameWithCustomSettings() {
   // Ask all the questions first and store the responses
    std::string name = view->askForString("Enter your name: ",0);
    int width =  view->askForInt("Enter board width (minimum 5): ", 5);
    int height =  view->askForInt("Enter board height (minimum 10): ", 10);
    bool customShapesWanted =  view->askYesNo("Do you want a custom bag of shapes?");
    bool randomFillWanted =  view->askYesNo("Do you want to fill the board randomly?");

   // Create the game with specified parameters
    game = std::make_unique<Game>(name, width, height);
    game->addObserver(view);

    // Configure the bag of shapes if the user wants it
    if (customShapesWanted) {
        std::vector<tetrisEnum::Shape> shapes = askForCustomShapes();
        game->fillBag(shapes);
    }

   // Randomly fill the game board if the user wants it
    if (randomFillWanted) {
        game->fillBoardRandomly();
    }
}

void GameController::initializeGameWithDefaultSettings() {
    std::string name = view->askForString("Enter your name: ",0);
    game = std::make_unique<Game>(name);
    game->addObserver(view);
}

void GameController::run() {
    view->displayTitle("Welcome to tetris");

    if (view->askYesNo("Do you want to use custom settings?")) {
        initializeGameWithCustomSettings();
    } else {
        initializeGameWithDefaultSettings();
    }

    startGameLoop();
}



void GameController::startGameLoop() {
    game->startGame();
    
    while (!game->isGameOver()) {      
        processUserInput();
    }

}

void GameController::processUserInput() {
    while (true) { // Continue indefinitely until a valid command is processed
        ;
        try {
            std::string input = view->askForString(">",false);

            if (!input.empty()) {
                char command = std::tolower(input[0]);

                if (input == "h" || input == "H") {
                    view->displayHelp();
                    return;
                }

                // If the command is longer than 1 character (and is not 'h' or 'H'), ask for input again
                if (input.length() > 1) {
                    std::cout << "Please enter a single character command." << std::endl;
                    continue;
                }

                switch (command) {

                case 'p':
                    game->setGameOver();
                    return;
                case 'q':
                    game->moveBrick(tetrisEnum::Direction::LEFT);
                    return;
                case 'd':
                    game->moveBrick(tetrisEnum::Direction::RIGHT);
                    return;
                case's':
                    game->moveBrick(tetrisEnum::Direction::DOWN);
                    return;
                    case 'z':
                    game->rotateBrick(tetrisEnum::RotationDirection::CLOCKWISE);
                    return;
                case 'a':
                    game->rotateBrick(tetrisEnum::RotationDirection::COUNTERCLOCKWISE);
                    return;
                case 'x':
                    game->drop();
                    return;
                case 'n':
                    game->getNewCurBrick();
                    return;
                default:
                    std::cout << "Invalid command! Please enter a valid command." << std::endl;
                    break;

                }
            }
        } catch (...) {
            std::cerr << "Exception caught in GameController::processUserInput: "  << std::endl;
        }
    }
}


std::vector<tetrisEnum::Shape> GameController::askForCustomShapes() {
    std::string input = view->askForString("Enter the shapes you want in your bag. per example: (I, O, T, J, L, Z, S): ",0);
    std::vector<tetrisEnum::Shape> shapes;
    std::istringstream iss(input);
    std::string token;
    bool invalidInput = false;

    while (std::getline(iss, token, ',')) {
        std::stringstream ss(token);
        std::string shape;
        while (ss >> shape) {
            if (shape.length() == 1) {
                char c = std::tolower(shape[0]);
                switch (c) {
                case 'i': shapes.push_back(tetrisEnum::Shape::IShape); break;
                case 'o': shapes.push_back(tetrisEnum::Shape::OShape); break;
                case 't': shapes.push_back(tetrisEnum::Shape::TShape); break;
                case 'j': shapes.push_back(tetrisEnum::Shape::JShape); break;
                case 'l': shapes.push_back(tetrisEnum::Shape::LShape); break;
                case 'z': shapes.push_back(tetrisEnum::Shape::ZShape); break;
                case 's': shapes.push_back(tetrisEnum::Shape::SShape); break;
                default:
                    std::cout << "Invalid shape: " << shape << ". Please enter valid shapes." << std::endl;
                    invalidInput = true;
                    break;
                }
            }
            if (invalidInput) break;
        }
        if (invalidInput) break;
    }

    if (shapes.empty() || invalidInput) {
        return askForCustomShapes();
    }

    return shapes;
}


