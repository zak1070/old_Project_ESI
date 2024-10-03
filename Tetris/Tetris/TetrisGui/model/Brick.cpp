#include "Brick.h"
#include "RelatedPositions.h"

//We use a Data pointer because all bricks share the same address.v
Brick::Brick(const tetrisEnum::Shape& shape )
    : center(shape),
    rotation(0),
    shape(shape),
    positionsData(&(RelatedPositions::getInstance()))
{}


Brick::Brick(const Brick& brique)
    : center(brique.center),
    rotation(brique.rotation),
    shape(brique.shape),
    positionsData(&(RelatedPositions::getInstance()))
{

}


void Brick::move(tetrisEnum::Direction direction) {
    center.move(direction);
}

void Brick::drop(int y) {
    int delta = center.getY() + y;
    center.setY(delta);
}

void Brick::rotate(tetrisEnum::RotationDirection direction) {
    // Clockwise rotation
    if (direction == tetrisEnum::RotationDirection::CLOCKWISE) {
        rotation = (rotation + 1) % 4; // Increments rotation, wraps back to 0 if it reaches 4
    }
    // Counterclockwise rotation
    else if (direction == tetrisEnum::RotationDirection::COUNTERCLOCKWISE) {
        if (rotation == 0) {
            rotation = 3; // Switches to configuration 3 if rotation is at 0
        } else {
            rotation = (rotation - 1 + 4) % 4; // Decrements rotation, wraps back to 3 if it goes below 0
        }
    }
}


void Brick::resetCenter(int width) {
    center.setX((width/2)-1);
    center.setY(0);
    rotation=0;

}

std::vector< Position> Brick::getPositions() const {
    std::vector<Position> positions;
    positions = positionsData->getRelativePositions(shape,rotation);
    for(auto& block : positions){
        block = center+block;
    }
    return positions;

}

Position Brick::getCenter(){
    return center;
}

int Brick::getRotation(int directionOfRotation) const {
    return rotation;
}
