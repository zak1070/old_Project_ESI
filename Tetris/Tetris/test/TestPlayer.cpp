#include <catch2/catch_test_macros.hpp>
#include "../src/model/Player.h"

TEST_CASE( "Test of Bag") {

    Player player("John");

    SECTION("Player Score") {
        player.addScore(50);
        REQUIRE(player.getScore() == 50);
    }

    SECTION("Player Name") {
        REQUIRE(player.getName() == "John");
    }

}
