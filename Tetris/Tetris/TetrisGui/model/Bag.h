#ifndef BAG_H
#define BAG_H
#include <vector>
#include "Brick.h"
#include "Enums.h"


/**
 * @class Bag
 * @brief Singleton class that represents a "bag" of Tetris bricks.
 *
 * The user has the option to play with the classic bag or to customize their own bag.
 *
 * @note As a singleton, this class prevents direct instantiation from outside
 * and copying, ensuring only one instance is used throughout the game.
 */
class Bag {
private:
    static Bag uniqueInstance;
    std::vector<Brick> bricks;
    int index;
    void addBricks();
    Bag();
    void shuffle();
    int nextIndex;
public:
    static Bag& getInstance();
    Brick& getbrick();
    tetrisEnum::Shape getNextBrick();
    void setBricks(const std::vector<tetrisEnum::Shape>& shapes);
};

#endif // BAG_H
