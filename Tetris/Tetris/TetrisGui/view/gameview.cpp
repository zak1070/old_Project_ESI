#include "GameView.h"
#include "ui_GameView.h"
#include <QGraphicsRectItem>
#include <QGraphicsTextItem>
#include <QMessageBox>

GameView::GameView(QWidget *parent)
    : QWidget(parent), ui(std::make_unique<Ui::GameView>())
{
    ui->setupUi(this);
    scene = std::make_unique<QGraphicsScene>(this);
    ui->graphicsView->setScene(scene.get());
    nextBrickScene = std::make_unique<QGraphicsScene>(this);
    ui->nextBrickView->setScene(nextBrickScene.get());

    // Set retro style
    ui->tetrisLabel->setStyleSheet("color: white; background-color: black;");
    ui->graphicsView->setStyleSheet("background-color: black;");
    ui->nextBrickView->setStyleSheet("background-color: black;");
    setStyleSheet("background-color: #efcc19;");
}

GameView::~GameView()
{
    // unique_ptr will automatically handle deletion of ui, scene, and nextBrickScene
    //items in the scene are automaticly deleted
}


void GameView::keyPressEvent(QKeyEvent *event)
{
    emit keyPressed(event->key());
}

void GameView::update(GameState state)
{
    displayGrid(state);
     if(state.isOver){
         displayEnd(state.score, state.linesCleared, state.currentLevel);

    }
    else{

         updateLCD(ui->scoreLCD, state.score);
         updateLCD(ui->linesLCD, state.linesCleared);
         updateLCD(ui->levelLCD, state.currentLevel);
         ui->playerNameLabel->setText(QString::fromStdString(state.playerName));
         displayNextBrick(state.nextShape);
    }
}



void GameView::displayEnd(int score, int linesCleared, int currentLevel){
    QString message = QString("GAME OVER\n\nScore: %1\nLines Cleared: %2\nLevel: %3").arg(score).arg(linesCleared).arg(currentLevel);
    QMessageBox::information(this, tr("Game Over"), message);
}

void GameView::displayGrid(const GameState &state)
{
    scene->clear();
    const Board &board = state.grid;
    const auto &currentBrickPositions = state.positionsOfCurrentShape;

    int rectSize = 20;
    QPen gridPen(Qt::white);
    QPen emptyPen(Qt::transparent);

    for (int y = 0; y < board.getHeight(); ++y)
    {
        for (int x = 0; x < board.getWidth(); ++x)
        {
            QRectF rect(x * rectSize, y * rectSize, rectSize, rectSize);
            QGraphicsRectItem *brick = nullptr;

            if (isOccupiedByCurrentBrick(x, y, currentBrickPositions))
            {
                QBrush colorBrush(Qt::gray);
                brick = new QGraphicsRectItem(rect);
                brick->setBrush(colorBrush);
                brick->setPen(gridPen);
            }
            else
            {
                tetrisEnum::Shape shape = board.getPositionAt(x, y).getBrick();
                if (shape != tetrisEnum::Shape::NOShape)
                {
                    QBrush colorBrush = getShapeColorBrush(shape);
                    brick = new QGraphicsRectItem(rect);
                    brick->setBrush(colorBrush);
                    brick->setPen(gridPen);
                }
                else
                {
                    brick = new QGraphicsRectItem(rect);
                    brick->setBrush(Qt::transparent);
                    brick->setPen(emptyPen);
                }
            }
            //the brick is added to the scene, so we don't have to manage its deletion
            scene->addItem(brick);
        }
    }

    // Draw the outer grid border
    scene->addRect(0, 0, board.getWidth() * rectSize, board.getHeight() * rectSize, gridPen, Qt::NoBrush);

    // Adjust the size of the scene to fit the grid
    scene->setSceneRect(0, 0, board.getWidth() * rectSize, board.getHeight() * rectSize);
}

//Check if the current brick is in the current position

bool GameView::isOccupiedByCurrentBrick(int x, int y, const std::vector<Position> &currentBrickPositions) const
{
    for (const Position &pos : currentBrickPositions)
    {
        if (pos.getX() == x && pos.getY() == y)
        {
            return true;
        }
    }
    return false;
}

void GameView::displayNextBrick(tetrisEnum::Shape shape)
{
    nextBrickScene->clear();
    drawShape(nextBrickScene.get(), shape, 20);
}

void GameView::drawShape(QGraphicsScene *scene, tetrisEnum::Shape shape, int squareSize)
{
    QBrush brush = getShapeColorBrush(shape);
    QPen pen(Qt::black);
    std::vector<QPoint> points;

    switch (shape)
    {
    case tetrisEnum::Shape::IShape:
        points = {QPoint(0, 0), QPoint(0, 1), QPoint(0, 2), QPoint(0, 3)};
        break;
    case tetrisEnum::Shape::JShape:
        points = {QPoint(0, 0), QPoint(0, 1), QPoint(0, 2), QPoint(-1, 2)};
        break;
    case tetrisEnum::Shape::LShape:
        points = {QPoint(0, 0), QPoint(0, 1), QPoint(0, 2), QPoint(1, 2)};
        break;
    case tetrisEnum::Shape::OShape:
        points = {QPoint(0, 0), QPoint(1, 0), QPoint(0, 1), QPoint(1, 1)};
        break;
    case tetrisEnum::Shape::SShape:
        points = {QPoint(0, 0), QPoint(-1, 1), QPoint(0, 1), QPoint(1, 0)};
        break;
    case tetrisEnum::Shape::TShape:
        points = {QPoint(-1, 1), QPoint(0, 1), QPoint(1, 1), QPoint(0, 0)};
        break;
    case tetrisEnum::Shape::ZShape:
        points = {QPoint(-1, 0), QPoint(0, 0), QPoint(0, 1), QPoint(1, 1)};
        break;
    default:
        points = {};
    }

    for (const QPoint &point : points)
    {
        QRectF rect(point.x() * squareSize, point.y() * squareSize, squareSize, squareSize);
        scene->addRect(rect, pen, brush);
    }
}

void GameView::updateLCD(QLCDNumber *lcd, int value)
{
    lcd->display(value);
}



QBrush GameView::getShapeColorBrush(tetrisEnum::Shape shape) const
{
    switch (shape)
    {
    case tetrisEnum::Shape::IShape:
        return QBrush(Qt::cyan);
    case tetrisEnum::Shape::JShape:
        return QBrush(Qt::blue);
    case tetrisEnum::Shape::LShape:
        return QBrush(QColorConstants::Svg::orange);
    case tetrisEnum::Shape::OShape:
        return QBrush(Qt::yellow);
    case tetrisEnum::Shape::SShape:
        return QBrush(Qt::green);
    case tetrisEnum::Shape::TShape:
        return QBrush(QColorConstants::Svg::purple);
    case tetrisEnum::Shape::ZShape:
        return QBrush(Qt::red);
    default:
        return QBrush(Qt::black);
    }
}
