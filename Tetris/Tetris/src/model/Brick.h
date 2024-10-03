#ifndef BRICK_H
#define BRICK_H
#include "Position.h"
#include "RelatedPositions.h"
#include "Enums.h"

/**
 * @class Brick
 * @brief Represents a Tetris game piece, including its shape, position, and orientation.
 *
 * Represents the Tetris game brick with its central position, it can move, fall, and rotate.
 */
class Brick {
private:
    Position center; // Center coordinates of the brick
    int rotation; // Current rotation of the brick
    tetrisEnum::Shape shape;
    RelatedPositions * positionsData; // address towards Data


public:

    Brick(const tetrisEnum::Shape& shape = tetrisEnum::Shape::NOShape);
    // Copy constructor - Uses Data::getInstance() to initialize 'data'
    Brick(const Brick& brick);
    ~Brick()=default;

    void move(tetrisEnum::Direction direction); // Moves the brick in a specified direction
    void drop(int y); // Drops the brick a certain number of units
    void rotate(tetrisEnum::RotationDirection); // Rotates the brick in a specified direction
    void resetCenter(int width); // Resets the center of the brick
    std::vector<Position> getPositions() const;
    int getRotation(int directionOfRotation) const; // Calculates the new rotation based on a direction
    Position getCenter();
};


#endif // BRICK_H
