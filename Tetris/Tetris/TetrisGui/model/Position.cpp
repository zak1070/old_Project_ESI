#include "Position.h"


Position:: Position() : x(-1),y(-1),brick(tetrisEnum::Shape::NOShape) {}
Position:: Position(int x, int y,const tetrisEnum::Shape brick) : x(x), y(y),brick(brick) {}
Position:: Position(const Position& position) : x(position.x), y(position.y), brick(position.brick) {}
Position:: Position(const tetrisEnum::Shape& shape) : x(0), y(0), brick(shape) {}



int  Position::getX() const {
    return x;
}

int  Position::getY() const {
    return y;
}

tetrisEnum::Shape  Position::getBrick() const {
    return brick;
}

void  Position::setX(int newX) {
    x = newX;
}

void  Position::setY(int newY) {
    y = newY;
}

void  Position::move(tetrisEnum::Direction dir) {
    switch (dir) {
    case tetrisEnum::Direction::DOWN:
        ++y;
        break;
    case tetrisEnum::Direction::LEFT:
        --x;
        break;
    case tetrisEnum::Direction::RIGHT:
        ++x;
        break;
    default:
        break;
    }
}

Position operator+(const  Position& a, const  Position& b) {
    int newX = a.getX() + b.getX();
    int newY = a.getY() + b.getY();
    tetrisEnum::Shape newShape = a.getBrick();

    return  Position(newX, newY, newShape);
}

Position&  Position::operator=(const  Position& other) {
    x = other.x;
    y = other.y;
    brick = other.brick;

    return *this;
}

//Created to enable testing
bool operator==(const Position& a, const Position& b) {
    return a.getX() == b.getX() && a.getY() == b.getY() && a.getBrick() == b.getBrick();
}
