#include "Player.h"

Player::Player() : name("PLAYER 1"), score(0) {
}

Player::Player(std::string nickname) : name(nickname), score(0) {
}

void Player::addScore(int points) {
    score += points;
}

int Player::getScore() {
    return score;
}

std::string Player::getName() {
    return name;
}
