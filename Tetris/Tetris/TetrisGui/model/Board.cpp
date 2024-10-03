#include "Board.h"
#include <algorithm>

Board::Board(int width, int height) : height(height), width(width) {
    for (int y = 0; y < height; ++y) {
        std::vector<Position> row;
        for (int x = 0; x < width; ++x) {
            row.push_back(Position(x, y, tetrisEnum::Shape::NOShape));
        }
        grid.push_back(row);
    }
}

Board::Board() : Board(10, 20) {
}

int Board::getHeight() const {
    return height;
}

int Board::getWidth() const {
    return width;
}

const Position& Board::getPositionAt(int x, int y) const {
    static Position nullPosition(-1, -1, tetrisEnum::Shape::NOShape); // Default null position
    if (x >= 0 && x < width && y >= 0 && y < height) {
        return grid[y][x];
    } else {
        return nullPosition;
    }
}

int Board::manageCollision(const std::vector< Position>& positions) {
    for(auto& position : positions) {
        grid[position.getY()][position.getX()]=position;
    }
    std::vector<int> linesToClear;
    linesToClear = checkLines();
    clearLine(linesToClear);

    return linesToClear.size();
}

std::vector<int> Board::checkLines() {
    std::vector<int> linesToClear;
    // Traverse each line starting from the bottom of the grid
    for (int y = height - 1; y >= 0; --y) {
        bool lineComplete = true; // Assume the line is complete initially

        // Check each column of the current line
        for (int x = 0; x < width; ++x) {
            // Check if the cell at position (x, y) is empty, i.e., if the 'brick' character is a space
            if (grid[y][x].getBrick() == tetrisEnum::Shape::NOShape) {
                lineComplete = false; // The line is not complete
                break; // No need to check other columns if one is empty
            }
        }

        // If after checking all columns, lineComplete is still true,
        // it means the line is complete and should be added to the vector to be cleared
        if (lineComplete) {
            linesToClear.push_back(y);
        }
    }
    return linesToClear;
}

void Board::clearLine( std::vector<int>& rows) {
    // Sort rows in descending order to ensure we start from the bottom
    std::sort(rows.begin(), rows.end(), std::greater<int>());

    // Mark rows for clearing by replacing each cell with an "empty" block
    for (int rowIndex : rows) {
        for (int x = 0; x < width; ++x) {
            grid[rowIndex][x] = Position(x, rowIndex, tetrisEnum::Shape::NOShape);
        }
    }

    // Count how many rows have been cleared above each row
    std::vector<int> linesDropped(height, 0);
    for (int i = 0; i < rows.size(); ++i) {
        for (int y = rows[i] - 1; y >= 0; --y) {
            linesDropped[y]++;
        }
    }

    // Shift rows down
    for (int y = height - 1; y >= 0; --y) {
        if (linesDropped[y] > 0) {
            int newY = y + linesDropped[y];
            if (newY < height) { // Check to avoid exceeding grid bounds
                for (int x = 0; x < width; ++x) {
                    grid[newY][x] = grid[y][x];
                    grid[y][x] = Position(x, y, tetrisEnum::Shape::NOShape); // Mark the old row as empty
                }
            }
        }
    }

    // No need to explicitly reset the top line to "empty" blocks
    // as this is handled by shifting the rows.
}

void Board::fillRandomly() {

    int totalShapes = static_cast<int>(tetrisEnum::Shape::NOShape) - 1; // Exclude NOShape from the selection

    int startHeight = height / 2;

    for (int y = startHeight; y < height; ++y) {
        bool fullyFilled = true; // Initially assume the line is fully filled

        for (int x = 0; x < width; ++x) {
            if (std::rand() % 2) { // Random decision to fill or not the cell
                auto shape = static_cast<tetrisEnum::Shape>(1 + std::rand() % totalShapes); // Generate a random shape, excluding NOShape
                grid[y][x] = Position(x, y, shape);
            } else {
                fullyFilled = false; // The cell remains empty, so the line is not fully filled
                grid[y][x] = Position(x, y, tetrisEnum::Shape::NOShape); // Explicitly mark the cell as empty
            }
        }
        // If after the first pass, the line is fully filled, randomly choose a cell to empty
        if (fullyFilled) {
            int emptySlot = std::rand() % width;
            grid[y][emptySlot] = Position(emptySlot, y, tetrisEnum::Shape::NOShape); // Force a cell to remain empty
        }
    }
}

int Board::getFirstPosYAtColBelow(int x, int y){
    // Traverse down from position y+1 to not consider the current brick.
    for (int yPos = y + 1; yPos < getHeight(); yPos++) {
        if (getPositionAt( x, yPos).getBrick()!=tetrisEnum::Shape::NOShape) {
            // Returns the y index of the first occupied block found below.
            return yPos;
        }
    }

    return getHeight();
}
