#ifndef BOARD_H
#define BOARD_H
#include <vector>
#include "Position.h"

/**
 * @class Board
 * @brief Manages the Tetris game board.
 *
 * This class represents the game board for a Tetris game.
 * It supports operations such as adding bricks to the board, checking for complete lines, randomly filling the board, and managing collisions.
 */
class Board {
private:
    int height;
    int width;
    std::vector<std::vector<Position>> grid;
    void clearLine(std::vector<int>& rows);

public:
    Board(int width, int height);
    Board();

    int manageCollision(const std::vector<Position>& positions); //Method that handles collisions
    std::vector<int> checkLines();
    int getHeight() const;
    int getWidth() const;
    const  Position& getPositionAt(int x, int y) const;
    int getFirstPosYAtColBelow(int x, int y);
    void fillRandomly();
};


#endif // BOARD_H
