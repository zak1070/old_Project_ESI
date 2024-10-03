#ifndef GAME_H
#define GAME_H
#include <chrono>
#include <vector>
#include <string>
#include "../util/Observable.h"
#include "Board.h"
#include "Brick.h"
#include"Position.h"
#include "Bag.h"
#include "Enums.h"
#include "Player.h"

/**
 * @class Game
 * @brief Manages the main game logic for Tetris.
 *
 * This class encompasses all the game's functionalities, primarily performing method calls.
 * It inherits from Observable to notify observers about changes in the game's state.
 *
 * @note Copy constructor and copy assignment operator are deleted to prevent copying, ensuring
 *       that a game instance is unique and centrally managed.
 */

class Game : public Observable {
private:
    Board board;
    Bag& bag;
    int currentLevel;
    static const int levelMax = 20;
    int lines;
    Player player;
    bool isOver;
    Brick currentBrick;
    std::chrono::time_point<std::chrono::system_clock> startTime;
    std::vector<double> times;
    bool hasCollision(const std::vector<Position>& positions);
    void updateScore(int linesCleared, int FallDistance = 1);
    bool isBrickOutOfBoard(const std::vector<Position>& brickPositions);
    bool isTimeUp() const;
    void fillTimes();

public:
    Game(std::string name, int width, int height);
    Game(std::string name);
    Game(const Game&) = delete; // Prevent copying
    Game& operator=(const Game&) = delete;


    void startGame();
    void moveBrick(tetrisEnum::Direction dir);
    void drop();
    void rotateBrick(tetrisEnum::RotationDirection dir);
    bool isGameOver() const;
    void setGameOver();
    void getNewCurBrick();
    void fillBag(const std::vector<tetrisEnum::Shape>& shapes);
    void fillBoardRandomly();

    Board& getBoard();
    Brick& getCurrentBrick();
    Player& getPlayer();
    double getSpeed() const;

};
#endif // GAME_H
