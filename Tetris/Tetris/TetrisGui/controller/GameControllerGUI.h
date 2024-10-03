#ifndef GAMECONTROLLERGUI_H
#define GAMECONTROLLERGUI_H

#include <QObject>
#include <QTimer>
#include <QMap>
#include <memory>
#include "../model/Game.h"
#include "../view/GameView.h"



/**
 * @class GameControllerGUI
 * @brief The GameControllerGUI class handles the user inputs and calls the functions of the model for tetris game.
 */
class GameControllerGUI : public QObject {
    Q_OBJECT

public:
    explicit GameControllerGUI(QObject *parent = nullptr);
    virtual ~GameControllerGUI()= default;
    void initializeGameWithCustomSettings(const QString& name, int width, int height, bool customShapes, const QString& bagOfShapes, bool randomFill, QWidget* configPage);
    void initializeGameWithDefaultSettings(const QString& name, QWidget* configPage);

private slots:
    void moveLeft();
    void moveRight();
    void moveDown();
    void rotateClockwise();
    void rotateCounterClockwise();
    void drop();
    void nextBrick();
    void pauseAndStartTimer();

    /**
     * @brief Handles key press events.
     * @param key The key that was pressed.
     */
    void handleKeyPress(int key);

private:
    std::unique_ptr<Game> game;
    std::shared_ptr<GameView> gameView;
    QTimer* timer;
    QMap<int, void (GameControllerGUI::*)()> keyMappings;

    std::vector<tetrisEnum::Shape> parseCustomShapes(const QString& input) const;
    double getInterval() const;
};

#endif // GAMECONTROLLERGUI_H
