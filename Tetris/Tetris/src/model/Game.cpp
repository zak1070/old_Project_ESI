#include "Game.h"

// Constructors
Game::Game(std::string name, int width, int height) :
    board(Board(width,height)),
    bag(Bag::getInstance()),
    currentLevel(1),
    lines(0),
    player(name),
    isOver(false){
    fillTimes();
    startTime =std::chrono::system_clock::now();
    getNewCurBrick();}

Game::Game(std::string name)
    : board(),
    bag(Bag::getInstance()),
    currentLevel(1),
    lines(0),
    player(name),
    isOver(false){
    fillTimes();
    startTime =std::chrono::system_clock::now();
    getNewCurBrick();}

// Member functions
void Game::startGame() {

    GameState stat = GameState(board,lines,currentLevel,player.getScore(),currentBrick.getPositions()
                               ,bag.getNextBrick(),player.getName(),isOver);
    notify(stat);
}

void Game::moveBrick(tetrisEnum::Direction dir) {
    Brick tmp(currentBrick);
    tmp.move(dir);
    std::vector<Position> tmpPosititions =tmp.getPositions();
    if(!hasCollision(tmpPosititions)){
        currentBrick.move(dir);
        GameState state = GameState(board,lines,currentLevel,player.getScore(),currentBrick.getPositions()
                                   ,bag.getNextBrick(),player.getName(),isOver);
        notify(state);
    }else if(dir==tetrisEnum::Direction::DOWN){

        if (isBrickOutOfBoard(tmpPosititions)) {
            setGameOver();
            return;
        }

        int linesCleared =board.manageCollision(currentBrick.getPositions());
        updateScore(linesCleared);
        getNewCurBrick();
    }

    if (isTimeUp()) {
        setGameOver();

    }
}

void Game::drop() {
    int lowestDrop = board.getHeight(); // Initialisation avec la hauteur maximale du plateau.

    // For each position of the current brick, calculate the shortest drop distance.
    for (const auto& position : currentBrick.getPositions()) {
        // Use the new method getFirstPosYAtColBelow to find the first non-empty block below.
        int firstBlockBelowY = board.getFirstPosYAtColBelow(position.getX(), position.getY());

        int dropDistance;
        // If no block is found below until the bottom, the drop distance is to the bottom of the grid.
        if (firstBlockBelowY == board.getHeight()) {
            dropDistance = firstBlockBelowY - position.getY()-1;
        } else {
            // Otherwise, the drop distance is to the block directly below, so we need to stop just above it.
            dropDistance = firstBlockBelowY - position.getY() - 1;
        }

        // Keep the shortest drop distance among all the blocks of the brick.
        if (dropDistance < lowestDrop) {
            lowestDrop = dropDistance;
        }
    }

    currentBrick.drop(lowestDrop);

    // Check if the brick is out of the grid after dropping.
    if (isBrickOutOfBoard(currentBrick.getPositions())) {
        setGameOver();
        return;  //// Allows to exit the drop.

    }

    int linesCleared = board.manageCollision(currentBrick.getPositions());
    updateScore(linesCleared, lowestDrop);
    getNewCurBrick();

    if (isTimeUp()) {
        setGameOver();

    }

}


bool Game::isBrickOutOfBoard(const std::vector<Position>& brickPositions) {
    for (const auto& position : brickPositions) {
        if (position.getY() < 0) {
            // If a position of the brick is above the grid, return true.
            return true;
        }
    }
    return false;
}


void Game::rotateBrick(tetrisEnum::RotationDirection dir) {
    Brick tmp(currentBrick);
    tmp.rotate(dir);
    if(!hasCollision(tmp.getPositions())){
        currentBrick.rotate(dir);
    }
    GameState stat = GameState(board,lines,currentLevel,player.getScore(),currentBrick.getPositions()
                               ,bag.getNextBrick(),player.getName(),isOver);
    notify(stat);
}

bool Game::isGameOver() const {
    return isOver;
}

void Game::setGameOver(){
    isOver = true;
    GameState stat = GameState(board,lines,currentLevel,player.getScore(),currentBrick.getPositions()
                               ,bag.getNextBrick(),player.getName(),isOver);
    notify(stat);
}


bool Game::hasCollision(const std::vector<Position>& positions) {
    for (const auto& pos : positions) {
        // If the position is outside the grid, it's a collision.
        if (pos.getX() < 0 || pos.getX() >= board.getWidth() || pos.getY() >= board.getHeight()) {
            return true;
        }
        // Then, if the position is occupied, it's also a collision.
        Position positionOnBoard = board.getPositionAt(pos.getX(), pos.getY());
        if (positionOnBoard.getBrick() != tetrisEnum::Shape::NOShape) {
            return true;
        }
    }
    // If no block is found at any of the positions, there is no collision.
    return false;
}


void Game::updateScore(int linesCleared,int FallDistance) {

    int scoreIncrease = 0;

    // 'd' represents the drop height, obtained directly via getY()

    int d = FallDistance;
    if (d==0)
        d=1;

    switch (linesCleared) {
    case 0:
        scoreIncrease =(40*linesCleared+d)*currentLevel;
        break;
    case 1:
        scoreIncrease =(40*linesCleared+d)*currentLevel;
        break;
    case 2:
        scoreIncrease = (100*linesCleared+d)*currentLevel;
        break;
    case 3:
        scoreIncrease = (300 * linesCleared + d)*currentLevel;
        break;
    case 4:
        scoreIncrease = (1200 * linesCleared + d) *currentLevel;
        break;
    default:
        break;
    }


    lines += linesCleared;
    player.addScore(scoreIncrease);
    currentLevel=(lines/10)+1;

    if(currentLevel==20){
        setGameOver();
    }
}


void Game::fillBoardRandomly() {
    board.fillRandomly();
}
void Game::getNewCurBrick() {
    int topCenter = (board.getWidth()/2)-1;

     if(board.getPositionAt(topCenter,0).getBrick() == tetrisEnum::Shape::NOShape){
        currentBrick=bag.getbrick();
        currentBrick.resetCenter(board.getWidth());
    }else{
        setGameOver();
    }
    GameState stat = GameState(board,lines,currentLevel,player.getScore(),currentBrick.getPositions(),bag.getNextBrick(),player.getName(),isOver);
    notify(stat);
}

//Method for filling the bag in the custom bag
void Game::fillBag(const std::vector<tetrisEnum::Shape>& shapes) {
    bag.setBricks(shapes); // Update the bricks in Bag
    getNewCurBrick();
    GameState stat = GameState(board,lines,currentLevel,player.getScore(),currentBrick.getPositions()
                               ,bag.getNextBrick(),player.getName(),isOver);
    notify(stat);
}

Board& Game::getBoard()  {
    return this->board;
}

Brick& Game::getCurrentBrick() {
    return currentBrick;
}

Player& Game ::getPlayer(){
    return player;
}

double Game::getSpeed() const {

    return times.at(currentLevel-1);
}


bool Game::isTimeUp() const {
    auto currentTime = std::chrono::system_clock::now();
    auto duration = std::chrono::duration_cast<std::chrono::minutes>(currentTime - startTime);
    return duration.count() >= 20;
}

void Game::fillTimes() {
    times = {1.0,
             static_cast<double>(53)/60.0, static_cast<double>(49)/60.0, static_cast<double>(45)/60.0,
             static_cast<double>(41)/60.0, static_cast<double>(37)/60.0, static_cast<double>(33)/60.0,
             static_cast<double>(28)/60.0, static_cast<double>(23)/60.0, static_cast<double>(18)/60.0,
             static_cast<double>(13)/60.0, static_cast<double>(8)/60.0, static_cast<double>(9)/60.0,
             static_cast<double>(8)/60.0, static_cast<double>(7)/60.0, static_cast<double>(6)/60.0,
             static_cast<double>(5)/60.0, static_cast<double>(4)/60.0, static_cast<double>(3)/60.0,
             static_cast<double>(2)/60.0};
}
