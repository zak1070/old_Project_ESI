#ifndef POSITION_H
#define POSITION_H

#include "Enums.h"

/**
 * @class Position
 * @brief Represents a position on the game board in  Tetris game.
 *
 * This class encapsulates the position (x, y coordinates) of a Tetris game piece on the board,
 * along with the shape of the piece. It provides methods for accessing and modifying the position
 * and shape, as well as for moving the piece in different directions.
 */
class Position {
private:
    int x;
    int y;
    tetrisEnum::Shape brick;

public:
    Position();
    Position(int x, int y, tetrisEnum::Shape brick);
    Position(const Position& position);
    Position(const tetrisEnum::Shape& shape);
    ~Position() = default;

    int getX() const;
    int getY() const;
    tetrisEnum::Shape getBrick() const;

    void setX(int x);
    void setY(int y);
    void move(tetrisEnum::Direction dir);

    Position& operator=(const Position& other);

};

Position operator+(const Position& a, const Position& b);
bool operator==(const Position& a, const Position& b);

#endif // POSITION_H
