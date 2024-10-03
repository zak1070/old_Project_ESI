#ifndef GAMEVIEW_H
#define GAMEVIEW_H

#include <QWidget>
#include <QLCDNumber>
#include <QLabel>
#include <QGraphicsScene>
#include <memory>
#include <QKeyEvent>
#include "../util/Observer.h"
#include "../model/GameState.h"

namespace Ui {
class GameView;
}

class GameView : public QWidget, public Observer {
    Q_OBJECT

public:
    explicit GameView(QWidget *parent = nullptr);
    virtual ~GameView();
    void update(GameState state) override;

signals:
    void keyPressed(int key);

protected:
    void keyPressEvent(QKeyEvent *event) override;

private:
    std::unique_ptr<Ui::GameView> ui;
    std::unique_ptr<QGraphicsScene> scene;
    std::unique_ptr<QGraphicsScene> nextBrickScene;

    void displayEnd(int score, int linesCleared, int currentLevel);
    void displayGrid(const GameState &state);
    void displayNextBrick(tetrisEnum::Shape shape);
    void updateLCD(QLCDNumber *lcd, int value);
    bool isOccupiedByCurrentBrick(int x, int y, const std::vector<Position> &currentBrickPositions) const;
    void drawShape(QGraphicsScene *scene, tetrisEnum::Shape shape, int squareSize);
    QBrush getShapeColorBrush(tetrisEnum::Shape shape) const;
};

#endif // GAMEVIEW_H
