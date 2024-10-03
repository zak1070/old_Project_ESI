#ifndef GAMECONTROLLER_H
#define GAMECONTROLLER_H
#include "../model/Enums.h"
#include "../model/Game.h"
#include "../view/ConsoleView.h"
#include <memory>

class GameController {
private:
    std::unique_ptr<Game> game;
    std::shared_ptr<ConsoleView> view;

    std::string askNameOfPlayer();
    void initializeGameWithCustomSettings();
    void initializeGameWithDefaultSettings();
    void startGameLoop();
    std::vector<tetrisEnum::Shape> askForCustomShapes();


public:
    GameController(const ConsoleView& view);
    void run();
    void processUserInput();

};

#endif // GAMECONTROLLER_H
