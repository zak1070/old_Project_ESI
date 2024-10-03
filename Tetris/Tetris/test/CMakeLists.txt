cmake_minimum_required(VERSION 3.5)

add_executable(Tests
    TestPlayer.cpp
../src/model/Bag.cpp


../src/model/Brick.h
../src/model/Position.h
../src/model/Board.h
../src/model/Game.h
../src/model/Player.h
../src/model/Bag.h
../src/model/Enums.h
../src/model/RelatedPositions.h
../src/util/Observable.h
../src/util/Observable.cpp


../src/model/Brick.cpp
../src/model/Position.cpp
../src/model/Board.cpp
../src/model/RelatedPositions.cpp
../src/model/Player.cpp

../src/model/Game.cpp
../src/util/Observer.h
../src/model/GameState.h
TestBoard.cpp
TestBrick.cpp
TestGame.cpp

)
target_include_directories(Tests PRIVATE
    ../src
)

Include(FetchContent)

FetchContent_Declare(
  Catch2
  GIT_REPOSITORY https://github.com/catchorg/Catch2.git
  GIT_TAG        v3.3.1 # or a later release
)

FetchContent_MakeAvailable(Catch2)
list(APPEND CMAKE_MODULE_PATH ${catch2_SOURCE_DIR}/extras)

target_link_libraries(Tests Catch2::Catch2WithMain)
