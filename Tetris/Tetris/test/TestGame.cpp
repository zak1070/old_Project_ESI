#include <catch2/catch_test_macros.hpp>
#include "../src/model/Player.h"
#include "../src/model/Game.h"
#include "../src/model/Brick.h"
#include "model/Enums.h"
#include <vector>



TEST_CASE( "Test of Game") {



    SECTION("Test on the bag filled by the user") {
        Game game("Jhon" ,8,8);

        std::vector<tetrisEnum::Shape> shapes;
        shapes.push_back(tetrisEnum::Shape::IShape);
        game.fillBag(shapes);
        game.startGame();
        game.drop();

        REQUIRE(game.getBoard().getPositionAt(3,7).getBrick() == shapes.at(0));

    }

    SECTION("Test score") {
        Game game("Jhon" ,4,8);

        std::vector<tetrisEnum::Shape> shapes;
        shapes.push_back(tetrisEnum::Shape::OShape);
        game.fillBag(shapes);
        game.startGame();


        game.drop();

        REQUIRE(game.getPlayer().getScore()==6);


        game.moveBrick(tetrisEnum::Direction::RIGHT);
        game.moveBrick(tetrisEnum::Direction::RIGHT);
        game.drop();


        REQUIRE(game.getPlayer().getScore()==212);

    }


    SECTION("Test moving Down") {
        Game game("Jhon" ,8,8);
        std::vector<tetrisEnum::Shape> shapes;
        shapes.push_back(tetrisEnum::Shape::IShape);
        game.fillBag(shapes);
        game.startGame();
        game.moveBrick(tetrisEnum::Direction::DOWN);


        REQUIRE(game.getCurrentBrick().getPositions().at(3).getY() == 2);


    }



    SECTION("Test moving left") {
        Game game("Jhon" ,8,8);
        std::vector<tetrisEnum::Shape> shapes;
        shapes.push_back(tetrisEnum::Shape::IShape);
        game.fillBag(shapes);
        game.startGame();
        game.moveBrick(tetrisEnum::Direction::LEFT);



        REQUIRE(game.getCurrentBrick().getPositions().at(3).getX() == 2);


    }


    SECTION("Test moving Right") {
        Game game("Jhon" ,8,8);
        std::vector<tetrisEnum::Shape> shapes;
        shapes.push_back(tetrisEnum::Shape::IShape);
        game.fillBag(shapes);
        game.startGame();
        game.moveBrick(tetrisEnum::Direction::RIGHT);

        REQUIRE(game.getCurrentBrick().getPositions().at(3).getX() == 4);


    }





}
