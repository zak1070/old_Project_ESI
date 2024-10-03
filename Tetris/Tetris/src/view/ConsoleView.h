#ifndef CONSOLEVIEW_H
#define CONSOLEVIEW_H

#include "../util/Observer.h"
#include "../model/GameState.h"
#include <string>


class ConsoleView : public Observer {
public:
    ConsoleView() = default;
    virtual ~ConsoleView() = default;

    // Overridden method from Observer
    void update(GameState state) override;

    // Display methods
    void displayTitle(std::string message);
    void displayGridTitle();
    void displayEnd(GameState state);
    void clearScreen() const;
    void displayHelp();
    void displayNextBrick(tetrisEnum::Shape shape);
    void displayGrid(const GameState& state);
    std::string askForString(const std::string& question, bool allowEmpty);

    int askForInt(const std::string& question, int minValue);
    bool askYesNo(const std::string& question);


private:
    std::string shapeOfPosition(const Position& position) const;
    bool isOccupiedByCurrentBrick(int x, int y, const std::vector<Position>& currentBrickPositions) const;
    void displayBrickAtPosition(const Position& position) const;
    void displayPosition(const Position& position) const;
};

#endif // CONSOLEVIEW_H
