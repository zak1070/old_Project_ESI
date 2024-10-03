#ifndef GAMESTATE_H
#define GAMESTATE_H
#include "Enums.h"
#include <string>
#include "Board.h"

/**
 * @struct GameState
 * @brief Represents the current state of the Tetris game.
 *
 * This structure is used to encapsulate all the relevant data concerning the current state of the game and allows for notifying the observers.
 */
struct GameState
{
    Board grid;
    int linesCleared;
    int currentLevel;
    double score;
    std::vector<Position> positionsOfCurrentShape;
    tetrisEnum::Shape nextShape;
    std::string playerName;
    bool isOver;


    inline GameState(Board grid, int linesCleared, int currentLevel,double score,std::vector<Position>&& positionsOfCurrentShape,
              tetrisEnum::Shape nextShape, std::string playerName,bool isOver)

        : grid(std::move(grid)),
        linesCleared(linesCleared),
        currentLevel(currentLevel),
        score(score),
        positionsOfCurrentShape(std::move(positionsOfCurrentShape)),
        nextShape(nextShape),
        playerName(std::move(playerName)),
        isOver(isOver)
    {}


};
#endif // GAMESTATE_H
