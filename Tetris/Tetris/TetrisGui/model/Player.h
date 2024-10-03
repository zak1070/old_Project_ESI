#ifndef PLAYER_H
#define PLAYER_H

#include <string>

class Player {
private:
    std::string name;
    int score;

public:
    Player();
    Player(std::string name);
    void addScore(int points);
    int getScore();
    std::string getName();
};



#endif // PLAYER_H
