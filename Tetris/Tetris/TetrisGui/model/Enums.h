#ifndef ENUMS_H
#define ENUMS_H

namespace tetrisEnum {
/**
 * @enum Shape
 * @brief Enumeration of the different Tetris piece shapes.
 *
 * Defines all the unique shapes that Tetris pieces can take. `NOShape` is used
 * to represent the absence of a shape, which can be useful for initializing
 * grid spaces without a piece.
 */
// NoShape will be the default shape in our grid.
enum class Shape {
    IShape,
    OShape,
    TShape,
    JShape,
    LShape,
    ZShape,
    SShape,
    NOShape
};


/**
 * @enum Direction
 * @brief Enumeration of the possible directions a Tetris piece can move.
 *
 * Defines the basic movements available for maneuvering Tetris pieces across
 * the game board.
 */
enum class Direction {
    LEFT,
    RIGHT,
    DOWN
};



/**
 * @enum RotationDirection
 * @brief Enumeration for defining the rotation direction of Tetris pieces.
 *
 * Specifies the two possible directions in which a piece can rotate: clockwise
 * or counterclockwise.
 */
enum class RotationDirection {
    CLOCKWISE,
    COUNTERCLOCKWISE


};
} // namespace tetrisEnum

#endif // ENUMS_H
