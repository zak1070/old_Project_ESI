#include "Bag.h"
#include"TetrisException.h"
#include <algorithm>
#include <random>

Bag Bag::uniqueInstance;

Bag::Bag() : bricks(),
    index(0)
{
    addBricks();
    shuffle();

}
void Bag::addBricks(){

    bricks.push_back(Brick(tetrisEnum::Shape::IShape));
    bricks.push_back(Brick(tetrisEnum::Shape::JShape));
    bricks.push_back(Brick(tetrisEnum::Shape::LShape));
    bricks.push_back(Brick(tetrisEnum::Shape::OShape));
    bricks.push_back(Brick(tetrisEnum::Shape::SShape));
    bricks.push_back(Brick(tetrisEnum::Shape::TShape));
    bricks.push_back(Brick(tetrisEnum::Shape::ZShape));

}

Bag& Bag::getInstance() {
    return uniqueInstance;
}


void Bag::shuffle() {
    auto rng = std::default_random_engine(std::random_device{}());
    std::shuffle(bricks.begin(), bricks.end(), rng);
    index = 0;
}

Brick& Bag ::getbrick() {
    if (bricks.empty()){
        throw TetrisException("the bag is Empty");
    }
    Brick& currentBrick = bricks[index];
    index++;

    return currentBrick;
}

//We make suhffle before get the nextbrick
tetrisEnum::Shape Bag::getNextBrick() {
    if (bricks.empty()) {
        throw std::runtime_error("The bag is empty");
    }

    if (index >= static_cast<int>(bricks.size()) - 1) {
        shuffle();
        index = 0;
    }
    return bricks[index].getCenter().getBrick();

}



void Bag::setBricks(const std::vector<tetrisEnum::Shape>& shapes) {
    bricks.clear();
    for (const auto& shape : shapes) {
        bricks.push_back(Brick(shape));
    }
    index = 0;
    shuffle();
}



