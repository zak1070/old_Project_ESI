#include <catch2/catch_test_macros.hpp>
#include "../src/model/Board.h"
#include "../src/model/Position.h"
#include "../src/model/Brick.h"
#include <iostream>
#include <iomanip>


TEST_CASE( "Test of Board") {


    SECTION("Board Size") {
        Board board(8,8);

        REQUIRE(board.getWidth()  == 8);
        REQUIRE(board.getHeight() == 8);
    }

    SECTION("Brick Placed") {
        Board board(8,8);

        std::vector<Position> brick1;
        std::vector<Position> brick2;


        brick1.push_back(Position(0, 7, tetrisEnum::Shape::LShape));
        brick1.push_back(Position(1, 7, tetrisEnum::Shape::LShape));
        brick1.push_back(Position(2, 7, tetrisEnum::Shape::LShape));
        brick1.push_back(Position(3, 7, tetrisEnum::Shape::LShape));

        board.manageCollision(brick1);
       REQUIRE(board.getPositionAt(2,7).getBrick() == tetrisEnum::Shape::LShape);

}

    SECTION("Check colision") {
        Board board(8,8);

    std::vector<Position> brick1;
    std::vector<Position> brick2;


    brick1.push_back(Position(0, 7, tetrisEnum::Shape::LShape));
    brick1.push_back(Position(1, 7, tetrisEnum::Shape::LShape));
    brick1.push_back(Position(2, 7, tetrisEnum::Shape::LShape));
    brick1.push_back(Position(3, 7, tetrisEnum::Shape::LShape));

    brick2.push_back(Position(4, 7, tetrisEnum::Shape::LShape));
    brick2.push_back(Position(5, 7, tetrisEnum::Shape::LShape));
    brick2.push_back(Position(6, 7, tetrisEnum::Shape::LShape));
    brick2.push_back(Position(7, 7, tetrisEnum::Shape::LShape));

    board.manageCollision(brick1);
    board.manageCollision(brick2);

    REQUIRE(board.getPositionAt(2,7).getBrick() == tetrisEnum::Shape::NOShape);

}


SECTION("Check Multiple colision") {
    Board board(6,6);

    std::vector<Position> brick1;
    std::vector<Position> brick2;
    std::vector<Position> brick3;


    brick1.push_back(Position(0, 5, tetrisEnum::Shape::LShape));
    brick1.push_back(Position(1, 5, tetrisEnum::Shape::LShape));
    brick1.push_back(Position(2, 5, tetrisEnum::Shape::LShape));
    brick1.push_back(Position(3, 5, tetrisEnum::Shape::LShape));


    brick2.push_back(Position(0, 4, tetrisEnum::Shape::LShape));
    brick2.push_back(Position(1, 4, tetrisEnum::Shape::LShape));
    brick2.push_back(Position(2, 4, tetrisEnum::Shape::LShape));
    brick2.push_back(Position(3, 4, tetrisEnum::Shape::LShape));

    brick3.push_back(Position(4, 5, tetrisEnum::Shape::OShape));
    brick3.push_back(Position(5, 5, tetrisEnum::Shape::OShape));
    brick3.push_back(Position(4, 4, tetrisEnum::Shape::OShape));
    brick3.push_back(Position(5, 4, tetrisEnum::Shape::OShape));



    board.manageCollision(brick1);
    board.manageCollision(brick2);
    board.manageCollision(brick3);


    REQUIRE(board.getPositionAt(5,5).getBrick() == tetrisEnum::Shape::NOShape);

}

    SECTION("Check clearLine") {
        Board board(7,7);

    std::vector<Position> brick1;
    std::vector<Position> brick2;


    brick1.push_back(Position(0, 6, tetrisEnum::Shape::LShape));
    brick1.push_back(Position(1, 6, tetrisEnum::Shape::LShape));
    brick1.push_back(Position(2, 6, tetrisEnum::Shape::LShape));
    brick1.push_back(Position(3, 6, tetrisEnum::Shape::LShape));

    brick2.push_back(Position(4, 6, tetrisEnum::Shape::TShape));
    brick2.push_back(Position(5, 6, tetrisEnum::Shape::TShape));
    brick2.push_back(Position(6, 5, tetrisEnum::Shape::TShape));
    brick2.push_back(Position(7, 6, tetrisEnum::Shape::TShape));

    board.manageCollision(brick1);
    board.manageCollision(brick2);

    REQUIRE(board.getPositionAt(6,5).getBrick() == tetrisEnum::Shape::TShape);

}





}
