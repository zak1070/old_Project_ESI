#ifndef RELATEDPOSITIONS_H
#define RELATEDPOSITIONS_H
#include <map>
#include <vector>
#include "Position.h"
#include "Enums.h"


/**
 * @class RelatedPositions
 * @brief Singleton class for managing Tetris shapes and their positions.
 *
 * This class holds all the different rotations of a brick and adheres to the singleton design pattern to ensure that there is only one instance.
 *
 * @note Copy constructor and copy assignment operator are deleted to prevent copying of
 *       the singleton instance.
 */


class RelatedPositions {
private:
    std::map<tetrisEnum::Shape, std::vector<std::vector<Position>>> relatedPositionsMap;
    static RelatedPositions uniqueInstance;
    RelatedPositions();
    void initShapes(); // Method to initialize the shapes


    RelatedPositions(const RelatedPositions&) = delete;
    RelatedPositions& operator=(const RelatedPositions&) = delete;

public:
    static RelatedPositions& getInstance();

    /**
     * Retrieves the positions related to a specific Tetris shape at a given rotation.
     *
     * @param shape The shape of the Tetris piece.
     * @param rotation The rotation index of the Tetris piece.
     * @return A vector of Position objects representing the shape's layout at the given rotation.
     */
    std::vector<Position> getRelativePositions(tetrisEnum::Shape shape, int rotation);
};

#endif // RELATEDPOSITIONS_H
