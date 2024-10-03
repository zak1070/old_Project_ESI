#include "ConsoleView.h"
#include <iostream>
#include <sstream>


void ConsoleView::update(GameState state) {
    clearScreen();
    if(!state.isOver){
    displayGridTitle();
    displayGrid(state);
    displayNextBrick(state.nextShape);
    std::cout << "Score: " << state.score << std::endl;
    std::cout << "Lines:" << state.linesCleared << std::endl;
    std::cout << "Level: " << state.currentLevel << std::endl;
    std::cout << "Player: " << state.playerName << std::endl;
    std::cout << "Press h for help:"<<std::endl;
    std::cout << ">";

    }else
        displayEnd(state);
}

void ConsoleView::displayTitle(std::string message) {
    std::cout<<"      ___         ___      ___         ___                   ___     "<<std::endl;
    std::cout<<"     /\\  \\       /\\  \\    /\\  \\       /\\  \\        ___      /\\  \\    "<<std::endl;
    std::cout<<"     \\:\\  \\     /::\\  \\   \\:\\  \\     /::\\  \\      /\\  \\    /::\\  \\   "<<std::endl;
    std::cout<<"      \\:\\  \\   /:/\\:\\  \\   \\:\\  \\   /:/\\:\\  \\     \\:\\  \\  /:/\\ \\  \\  "<<std::endl;
    std::cout<<"      /::\\  \\ /::\\~\\:\\  \\  /::\\  \\ /::\\~\\:\\  \\    /::\\__\\_\\:\\~\\ \\  \\ "<<std::endl;
    std::cout<<"     /:/\\:\\__/:/\\:\\ \\:\\__\\/:/\\:\\__/:/\\:\\ \\:\\__\\__/:/\\/__/\\ \\:\\ \\ \\__\\"<<std::endl;
    std::cout<<"    /:/  \\/__\\:\\~\\:\\ \\/__/:/  \\/__\\/_|::\\/:/  /\\/:/  /  \\:\\ \\:\\ \\/__/"<<std::endl;
    std::cout<<"   /:/  /     \\:\\ \\:\\__\\/:/  /       |:|::/  /\\::/__/    \\:\\ \\:\\__\\  "<<std::endl;
    std::cout<<"   \\/__/       \\:\\ \\/__/\\/__/        |:|\\/__/  \\:\\__\\     \\:\\/:/  /  "<<std::endl;
    std::cout<<"                \\:\\__\\               |:|  |     \\/__/      \\::/  /   "<<std::endl;
    std::cout<<"                 \\/__/                \\|__|                 \\/__/    "<<std::endl;
    std::cout << "                     "<< message << std::endl;
    std::cout << std::endl;
    std::cout << std::endl;
    std::cout << std::endl;

}

void ConsoleView::displayGridTitle() {
    std::cout << "*************** TETRIS ***************" << std::endl;
}

void ConsoleView::displayEnd(GameState state) {
    std::cout << "Game over. Thank you for playing!" << std::endl;
    std::cout << "player: " <<state.playerName << std::endl;
    std::cout <<"Score: "<< state.score << std::endl;
    std::cout << "Lines cleared: " << state.linesCleared << std::endl;
    std::cout << "Level: " << state .currentLevel << std::endl ;
    displayGrid(state);
    displayTitle("Good Bye;");


}



void ConsoleView::displayGrid(const GameState& state) {
    const Board& board = state.grid;
    const auto& currentBrickPositions = state.positionsOfCurrentShape;

    int gridWidth = board.getWidth();
    std::string border = "+" + std::string(gridWidth * 3 + 2, '-') + "+";
    std::cout << border << std::endl;

    for (int y = 0; y < board.getHeight(); ++y) {
        std::cout << "|";
        for (int x = 0; x < board.getWidth(); ++x) {
            if (isOccupiedByCurrentBrick(x, y, currentBrickPositions)) {
                //display just the first position
                displayBrickAtPosition(currentBrickPositions.at(0).getBrick());
            } else {
                displayPosition(board.getPositionAt(x, y));
            }
        }
        std::cout << " |" << std::endl;
    }
    std::cout << border << std::endl;
}

bool ConsoleView::isOccupiedByCurrentBrick(int x, int y, const std::vector<Position>& currentBrickPositions) const {
    for (const Position& pos : currentBrickPositions) {
        if (pos.getX() == x && pos.getY() == y) {
            return true;
        }
    }
    return false;
}

void ConsoleView::displayBrickAtPosition(const Position& position) const {
    std::cout << " " << shapeOfPosition(position) << " ";
}

void ConsoleView::displayPosition(const Position& position) const {
    std::cout << " " << shapeOfPosition(position) << " ";
}


//Clear the terminal
void ConsoleView::clearScreen() const {
#ifdef _WIN32
    system("cls");
#else
    system("clear");
#endif
}


void ConsoleView::displayHelp() {
    std::cout << "Commands: [P/p: quit | Q/q: left | D/d: right | S/s: down | Z/z: rotate clockwise | A/a: rotate counterclockwise |"
                 " X/x: drop | N/n: next Brick]: " << std::endl;
}

void ConsoleView::displayNextBrick(tetrisEnum::Shape shape) {
    std::string brickRepresentation; // String to store the brick representation
    switch (shape) {
    case tetrisEnum::Shape::IShape:
        brickRepresentation =
            "#\n"
            "#\n"
            "#\n"
            "#\n";
        break;
    case tetrisEnum::Shape::JShape:
        brickRepresentation =
            " #\n"
            " #\n"
            "##\n";
        break;
    case tetrisEnum::Shape::LShape:
        brickRepresentation =
            "#  \n"
            "#  \n"
            "## \n";
        break;
    case tetrisEnum::Shape::OShape:
        brickRepresentation =
            "##\n"
            "##\n";
        break;
    case tetrisEnum::Shape::SShape:
        brickRepresentation =
            " ##\n"
            "## \n";
        break;
    case tetrisEnum::Shape::TShape:
        brickRepresentation =
            " # \n"
            "###\n";
        break;
    case tetrisEnum::Shape::ZShape:
        brickRepresentation =
            "## \n"
            " ##\n";
        break;
    default:
        brickRepresentation = "Unknown shape";
        break;
    }

    std::cout << "Next Brick:\n" << brickRepresentation << std::endl;
}

std::string ConsoleView::shapeOfPosition(const Position& position) const {
    const std::string reset = "\033[0m";
    std::string color;
    char shape;

    switch (position.getBrick()) {
    case tetrisEnum::Shape::IShape: color = "\033[34m"; shape = '#'; break; // Bleu
    case tetrisEnum::Shape::ZShape: color = "\033[31m"; shape = '#'; break; // Rouge
    case tetrisEnum::Shape::JShape: color = "\033[32m"; shape = '#'; break; // Vert
    case tetrisEnum::Shape::LShape: color = "\033[35m"; shape = '#'; break; // Magenta
    case tetrisEnum::Shape::SShape: color = "\033[36m"; shape = '#'; break; // Cyan
    case tetrisEnum::Shape::TShape: color = "\033[33m"; shape = '#'; break; // Jaune
    case tetrisEnum::Shape::OShape: color = "\033[37m"; shape = '#'; break; // Blanc
    case tetrisEnum::Shape::NOShape: return ".";
    }

    return color + shape + reset;
}



std::string ConsoleView::askForString(const std::string& question, bool allowEmpty) {
    std::string response;
    do {
        std::cout << question;
        std::getline(std::cin, response);
    } while (response.empty() && !allowEmpty);
    return response;
}


int ConsoleView::askForInt(const std::string& question, int minValue) {
    int value;
    std::string input;
    do {
        std::cout << question;
        std::getline(std::cin, input);
        std::istringstream iss(input);
        if (!(iss >> value)) { // Vérifie si la conversion en entier a échoué
            std::cout << "Invalid input. Please enter a valid integer.\n";
            continue; // Continue la boucle si la saisie n'est pas un entier
        }
    } while (value < minValue); // Vérifie aussi si la valeur est inférieure à minValue
    return value;
}


bool ConsoleView::askYesNo(const std::string& question) {
    std::string response;
    do {
        std::cout << question << " (y/n): ";
        std::getline(std::cin, response);
    } while (response!= "y" && response!= "Y" && response != "n" && response !="N");
    return response == "y";
}
