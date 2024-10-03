#include "BrickPositions.h"



// Initialization of the unique Data instance
Data Data::uniqueInstance;

Data::Data() {
    initShapes();
}

void Data::initShapes() {
    data[tetrisEnum::Shape::IShape] = {
        {
         Position(0, -2 , tetrisEnum::Shape::IShape),  Position(0, -1, tetrisEnum::Shape::IShape),  Position(0, 0, tetrisEnum::Shape::IShape),  Position(0, 1, tetrisEnum::Shape::IShape)
        },
        {
            Position(-1, 0,tetrisEnum::Shape::IShape),  Position(0, 0, tetrisEnum::Shape::IShape),  Position(1, 0, tetrisEnum::Shape::IShape),  Position(2, 0, tetrisEnum::Shape::IShape)
        },
        {
            Position(0, -2, tetrisEnum::Shape::IShape),  Position(0, -1, tetrisEnum::Shape::IShape),  Position(0, 0, tetrisEnum::Shape::IShape),  Position(0, 1, tetrisEnum::Shape::IShape)
        },
        {
            Position(-1, 0, tetrisEnum::Shape::IShape),  Position(0, 0, tetrisEnum::Shape::IShape),  Position(1, 0, tetrisEnum::Shape::IShape),  Position(2, 0, tetrisEnum::Shape::IShape)
        }
    };

    data[tetrisEnum::Shape::LShape]={
        {
            Position(-2, 0, tetrisEnum::Shape::LShape),  Position(-1, 0, tetrisEnum::Shape::LShape),  Position(0, 0, tetrisEnum::Shape::LShape),  Position(0,-1, tetrisEnum::Shape::LShape)
        },
        {
            Position(0, -2, tetrisEnum::Shape::LShape),  Position(0, -1, tetrisEnum::Shape::LShape),  Position(0, 0, tetrisEnum::Shape::LShape),  Position(1,0, tetrisEnum::Shape::LShape)
        },
        {
            Position(0, 1, tetrisEnum::Shape::LShape),  Position(0, 0, tetrisEnum::Shape::LShape),  Position(1, 0, tetrisEnum::Shape::LShape), Position(2,0, tetrisEnum::Shape::LShape)
        },
        {
            Position(-1, 0, tetrisEnum::Shape::LShape),  Position(0, 0, tetrisEnum::Shape::LShape),  Position(0, 1, tetrisEnum::Shape::LShape),  Position(0,2, tetrisEnum::Shape::LShape)
        }
    };

    data[tetrisEnum::Shape::JShape]={
        {
            Position(0, -1, tetrisEnum::Shape::JShape),  Position(0, 0, tetrisEnum::Shape::JShape),  Position(1, 0, tetrisEnum::Shape::JShape),  Position(2,0, tetrisEnum::Shape::JShape)
        },
        {
            Position(0, 2, tetrisEnum::Shape::JShape),  Position(0, 1, tetrisEnum::Shape::JShape),  Position(0, 0, tetrisEnum::Shape::JShape),  Position(1,0, tetrisEnum::Shape::JShape)
        },
        {
            Position(-2, 0, tetrisEnum::Shape::JShape),  Position(-1, 0, tetrisEnum::Shape::JShape),  Position(0, 0, tetrisEnum::Shape::JShape),  Position(0,1, tetrisEnum::Shape::JShape)
        },
        {
            Position(-1, 0, tetrisEnum::Shape::JShape),  Position(0, 0, tetrisEnum::Shape::JShape),  Position(0, -1, tetrisEnum::Shape::JShape),  Position(0,-2, tetrisEnum::Shape::JShape)
        }
    };

    data[tetrisEnum::Shape::SShape]={
        {
            Position(-1, 1, tetrisEnum::Shape::SShape),  Position(0, 1, tetrisEnum::Shape::SShape),  Position(0, 0, tetrisEnum::Shape::SShape),  Position(1,0, tetrisEnum::Shape::SShape)
        },
        {
            Position(-1, -1, tetrisEnum::Shape::SShape),  Position(-1, 0, tetrisEnum::Shape::SShape),  Position(0, 0, tetrisEnum::Shape::SShape),  Position(0,1, tetrisEnum::Shape::SShape)
        },
        {
            Position(-1, 1, tetrisEnum::Shape::SShape),  Position(0, 1, tetrisEnum::Shape::SShape),  Position(0, 0, tetrisEnum::Shape::SShape),  Position(1,0, tetrisEnum::Shape::SShape)
        },
        {
            Position(-1, -1, tetrisEnum::Shape::SShape),  Position(-1, 0, tetrisEnum::Shape::SShape),  Position(0, 0, tetrisEnum::Shape::SShape),  Position(0,1, tetrisEnum::Shape::SShape)
        }
    };

    data[tetrisEnum::Shape::ZShape]={
        {
            Position(-1, 0, tetrisEnum::Shape::ZShape),  Position(0, 0, tetrisEnum::Shape::ZShape),  Position(0, 1, tetrisEnum::Shape::ZShape),  Position(1,1, tetrisEnum::Shape::ZShape)
        },
        {
            Position(-1, 1, tetrisEnum::Shape::ZShape),  Position(-1, 0, tetrisEnum::Shape::ZShape),  Position(0, 0, tetrisEnum::Shape::ZShape),  Position(0,-1, tetrisEnum::Shape::ZShape)
        },
        {
            Position(-1, 0, tetrisEnum::Shape::ZShape),  Position(0, 0, tetrisEnum::Shape::ZShape),  Position(0, 1, tetrisEnum::Shape::ZShape),  Position(1,1, tetrisEnum::Shape::ZShape)
        },
        {
            Position(-1, 1, tetrisEnum::Shape::ZShape),  Position(-1, 0, tetrisEnum::Shape::ZShape),  Position(0, 0, tetrisEnum::Shape::ZShape),  Position(0,-1, tetrisEnum::Shape::ZShape)
        }
    };
    data[tetrisEnum::Shape::TShape]={
        {
            Position(-1, 0, tetrisEnum::Shape::TShape),  Position(0, 0, tetrisEnum::Shape::TShape),  Position(0, -1, tetrisEnum::Shape::TShape),  Position(1,0, tetrisEnum::Shape::TShape)
        },
        {
            Position(0, -1, tetrisEnum::Shape::TShape),  Position(0, 0, tetrisEnum::Shape::TShape),  Position(0, 1, tetrisEnum::Shape::TShape),  Position(1,0, tetrisEnum::Shape::TShape)
        },
        {
            Position(-1, 0, tetrisEnum::Shape::TShape),  Position(0, 0, tetrisEnum::Shape::TShape),  Position(0, 1, tetrisEnum::Shape::TShape),  Position(1,0, tetrisEnum::Shape::TShape)
        },
        {
            Position(-1, 0, tetrisEnum::Shape::TShape),  Position(0, 0, tetrisEnum::Shape::TShape),  Position(0, -1, tetrisEnum::Shape::TShape),  Position(0,1, tetrisEnum::Shape::TShape)
        }
    };


    data[tetrisEnum::Shape::OShape]={
        {
            Position(-1, 0, tetrisEnum::Shape::OShape),  Position(0, 0, tetrisEnum::Shape::OShape),  Position(-1, 1, tetrisEnum::Shape::OShape),  Position(0,1, tetrisEnum::Shape::OShape)
        },
        {
           Position(-1, 0, tetrisEnum::Shape::OShape),  Position(0, 0, tetrisEnum::Shape::OShape),  Position(-1, 1, tetrisEnum::Shape::OShape),  Position(0,1, tetrisEnum::Shape::OShape)
        },
        {
            Position(-1, 0, tetrisEnum::Shape::OShape),  Position(0, 0, tetrisEnum::Shape::OShape),  Position(-1, 1, tetrisEnum::Shape::OShape),  Position(0,1, tetrisEnum::Shape::OShape)
        },
        {
            Position(-1, 0, tetrisEnum::Shape::OShape),  Position(0, 0, tetrisEnum::Shape::OShape),  Position(0, -1, tetrisEnum::Shape::OShape),  Position(0,1, tetrisEnum::Shape::OShape)
        }
    };
}

Data& Data::getInstance() {
    return uniqueInstance;
}


/**
 * Provides the relative positions of a tetris shape with respect to the center as a function of rotation
 *
 * @param shape The Tetris shape for which positions are requested.
 * @param rotation The rotation index of the shape, which determines its orientation.
 * @return A vector of Position objects that represent the shape's configuration
 *         for the specified rotation.
 * @throws TetrisException if the rotation index is out of bounds for the shape's
 *         configuration or if the shape is not found in the data map.
 */
std::vector<Position> Data::getRelativePositions(tetrisEnum::Shape shape, int rotation) {
    if (rotation < 0 || rotation >=  data[shape].size()) {
        std::cout<< shape<<" "<<rotation;
        throw TetrisException("Invalid rotation for shape.");
    }

    if (data.find(shape) != data.end()) {
        return data[shape][rotation];
    } else {
        throw TetrisException("Shape not found.");
    }
}

