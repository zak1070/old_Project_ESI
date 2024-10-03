#ifndef BRICKPOSITIONS_H
#define BRICKPOSITIONS_H
#include <map>
#include <vector>
#include "Position.h"
#include "TetrisException.h"
#include "Enums.h"
#include <iostream>

/**
 * @class Data
 * @brief Singleton class for managing Tetris shapes and their positions.
 *
 * This class holds all the different rotations of a brick and adheres to the singleton design pattern to ensure that there is only one instance.
 *
 * @note Copy constructor and copy assignment operator are deleted to prevent copying of
 *       the singleton instance.
 */


class Data {
private:
    std::map<tetrisEnum::Shape, std::vector<std::vector<Position>>> data;
    static Data uniqueInstance;
    Data();
    void initShapes(); // Method to initialize the shapes


    Data(const Data&) = delete;
    Data& operator=(const Data&) = delete;

public:
    static Data& getInstance();

    /**
     * Retrieves the positions related to a specific Tetris shape at a given rotation.
     *
     * @param shape The shape of the Tetris piece.
     * @param rotation The rotation index of the Tetris piece.
     * @return A vector of Position objects representing the shape's layout at the given rotation.
     */
    std::vector<Position> getRelativePositions(tetrisEnum::Shape shape, int rotation);
};

#endif // BRICKPOSITIONS_H
