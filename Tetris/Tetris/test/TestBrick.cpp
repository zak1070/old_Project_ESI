#include <catch2/catch_test_macros.hpp>
#include "../src/model/Brick.h"
#include "../src/model/Enums.h"
#include <vector>
#include "../src/model/Position.h"


TEST_CASE( "Test of Brick") {


    SECTION("Get Position") {
        Brick brick(tetrisEnum::Shape::LShape);
        std::vector<Position> position;


        position.push_back(Position(-2, 0, tetrisEnum::Shape::LShape));//retrouve pas l enum
        position.push_back(Position(-1, 0, tetrisEnum::Shape::LShape));
        position.push_back(Position(0, 0, tetrisEnum::Shape::LShape));
        position.push_back(Position(0, -1, tetrisEnum::Shape::LShape));

        std::vector<Position> BrickVerif  = brick.getPositions();

        REQUIRE(BrickVerif.at(0).getBrick()== position.at(0).getBrick());
        REQUIRE(BrickVerif.at(1).getBrick()== position.at(1).getBrick());
        REQUIRE(BrickVerif.at(2).getBrick()== position.at(2).getBrick());
        REQUIRE(BrickVerif.at(3).getBrick()== position.at(3).getBrick());


    }

    SECTION("Rotation Clockwise Check") {
        Brick brick (tetrisEnum::Shape::ZShape);
        std::vector<Position> position;

        position.push_back(Position(-1, -1, tetrisEnum::Shape::ZShape));
        position.push_back(Position(-1, 0, tetrisEnum::Shape::ZShape));
        position.push_back(Position(0, 0, tetrisEnum::Shape::ZShape));
        position.push_back(Position(0, -1, tetrisEnum::Shape::ZShape));


        std::vector<Position> BrickVerif  = brick.getPositions();


        REQUIRE(BrickVerif.at(0).getBrick()== position.at(0).getBrick());
        REQUIRE(BrickVerif.at(1).getBrick()== position.at(1).getBrick());
        REQUIRE(BrickVerif.at(2).getBrick()== position.at(2).getBrick());
        REQUIRE(BrickVerif.at(3).getBrick()== position.at(3).getBrick());
    }


    SECTION("Rotation Counterclockwise Check") {
        Brick brick (tetrisEnum::Shape::ZShape);
        std::vector<Position> position;

        position.push_back(Position(-1, -1, tetrisEnum::Shape::ZShape));
        position.push_back(Position(-1, 0, tetrisEnum::Shape::ZShape));
        position.push_back(Position(0, 0, tetrisEnum::Shape::ZShape));
        position.push_back(Position(0, -1, tetrisEnum::Shape::ZShape));

        std::vector<Position> BrickVerif  = brick.getPositions();


        REQUIRE(BrickVerif.at(0).getBrick()== position.at(0).getBrick());
        REQUIRE(BrickVerif.at(1).getBrick()== position.at(1).getBrick());
        REQUIRE(BrickVerif.at(2).getBrick()== position.at(2).getBrick());
        REQUIRE(BrickVerif.at(3).getBrick()== position.at(3).getBrick());

    }


    SECTION("Drop Check") {
        Brick brick (tetrisEnum::Shape::ZShape);
        std::vector<Position> position;

        position.push_back(Position(-1, 2, tetrisEnum::Shape::ZShape));
        position.push_back(Position(0, 2, tetrisEnum::Shape::ZShape));
        position.push_back(Position(0, 3, tetrisEnum::Shape::ZShape));
        position.push_back(Position(1, 3, tetrisEnum::Shape::ZShape));

        brick.drop(2);

        std::vector<Position> BrickVerif  = brick.getPositions();

        REQUIRE(BrickVerif.at(0)== position.at(0));
        REQUIRE(BrickVerif.at(1)== position.at(1));
        REQUIRE(BrickVerif.at(2)== position.at(2));
        REQUIRE(BrickVerif.at(3)== position.at(3));

    }



    SECTION("Deplace Check") {
        Brick brick (tetrisEnum::Shape::ZShape);
        std::vector<Position> position;

        position.push_back(Position(-1, 1, tetrisEnum::Shape::ZShape));
        position.push_back(Position(0, 1, tetrisEnum::Shape::ZShape));
        position.push_back(Position(0, 2, tetrisEnum::Shape::ZShape));
        position.push_back(Position(1, 2, tetrisEnum::Shape::ZShape));

        brick.move(tetrisEnum::Direction::DOWN);

        std::vector<Position> BrickVerif  = brick.getPositions();

        REQUIRE(BrickVerif.at(0)== position.at(0));
        REQUIRE(BrickVerif.at(1)== position.at(1));
        REQUIRE(BrickVerif.at(2)== position.at(2));
        REQUIRE(BrickVerif.at(3)== position.at(3));


    }

}



