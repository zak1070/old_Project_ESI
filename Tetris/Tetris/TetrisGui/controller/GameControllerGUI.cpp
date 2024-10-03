#include "GameControllerGUI.h"
#include <QKeyEvent>

GameControllerGUI::GameControllerGUI(QObject* parent)
    : QObject(parent), game(nullptr), timer(new QTimer(this)) {

    // Connect the QTimer timeout signal to the moveDown slot
    connect(timer, &QTimer::timeout, this, &GameControllerGUI::moveDown);

    // Initialize key mappings for game controls
    keyMappings = {
        {Qt::Key_Q, &GameControllerGUI::moveLeft},
        {Qt::Key_D, &GameControllerGUI::moveRight},
        {Qt::Key_S, &GameControllerGUI::moveDown},
        {Qt::Key_Z, &GameControllerGUI::rotateClockwise},
        {Qt::Key_A, &GameControllerGUI::rotateCounterClockwise},
        {Qt::Key_Space, &GameControllerGUI::drop},
        {Qt::Key_P, &GameControllerGUI::nextBrick},
        {Qt::Key_Escape, &GameControllerGUI::pauseAndStartTimer}
    };
}



void GameControllerGUI::initializeGameWithCustomSettings(const QString& name, int width, int height, bool customShapes,
                                                         const QString& bagOfShapes, bool randomFill, QWidget* configPage)
{
    gameView.reset();
    game = std::make_unique<Game>(name.toStdString(), width, height);

    if (customShapes)
    {
        game->fillBag(parseCustomShapes(bagOfShapes));
    }

    if (randomFill)
    {
        game->fillBoardRandomly();
    }

    gameView = std::make_shared<GameView>();
    game->addObserver(gameView);
    connect(gameView.get(), &GameView::keyPressed, this, &GameControllerGUI::handleKeyPress, Qt::UniqueConnection);

    gameView->show();
    configPage->close();

    timer->start(getInterval()); // Start the timer with the first interval

    game->startGame();
}

void GameControllerGUI::initializeGameWithDefaultSettings(const QString& name, QWidget* configPage)
{


    // Reset the game view to ensure any previous view is properly destroyed before initializing a new one.
    gameView.reset();
    game = std::make_unique<Game>(name.toStdString());

    gameView = std::make_shared<GameView>();
    game->addObserver(gameView);
    connect(gameView.get(), &GameView::keyPressed, this, &GameControllerGUI::handleKeyPress, Qt::UniqueConnection);

    gameView->show();
    configPage->close();

    timer->start(getInterval()); // Démarrer le timer avec l'intervalle initial

    game->startGame();
}

void GameControllerGUI::moveLeft()
{
    game->moveBrick(tetrisEnum::Direction::LEFT);
}

void GameControllerGUI::moveRight()
{
    game->moveBrick(tetrisEnum::Direction::RIGHT);
}

void GameControllerGUI::moveDown()
{

    if (game->isGameOver()) {
        timer->stop(); // Arrêter le timer

    } else {
        game->moveBrick(tetrisEnum::Direction::DOWN);
        timer->setInterval(getInterval()*1000);
    }
}

void GameControllerGUI::rotateClockwise()
{
    game->rotateBrick(tetrisEnum::RotationDirection::CLOCKWISE);
}

void GameControllerGUI::rotateCounterClockwise()
{
    game->rotateBrick(tetrisEnum::RotationDirection::COUNTERCLOCKWISE);
}

void GameControllerGUI::drop()
{
    game->drop();
}

void GameControllerGUI::nextBrick()
{
    game->getNewCurBrick();
}

void GameControllerGUI::pauseAndStartTimer()
{
    if (timer->isActive()) {
        timer->stop();
    } else {
        timer->start(getInterval());
    }
}

void GameControllerGUI::handleKeyPress(int key)
{
    if (!game->isGameOver()) {
        auto it = keyMappings.find(key);
        if (it != keyMappings.end()) {
            auto action = it.value();
            (this->*action)();
        }
    }
}

double GameControllerGUI::getInterval() const
{
    double speed= game->getSpeed();
    return speed;  //milliseconds
}

std::vector<tetrisEnum::Shape> GameControllerGUI::parseCustomShapes(const QString& input) const {
    std::vector<tetrisEnum::Shape> shapes;

    QStringList tokens = input.split(',', Qt::SkipEmptyParts);
    for (QString& token : tokens) {

        token = token.trimmed().toLower();

        if (token.length() == 1) {
            QChar c = token[0];
            switch (c.unicode()) {
            case 'i': shapes.push_back(tetrisEnum::Shape::IShape); break;
            case 'o': shapes.push_back(tetrisEnum::Shape::OShape); break;
            case 't': shapes.push_back(tetrisEnum::Shape::TShape); break;
            case 'j': shapes.push_back(tetrisEnum::Shape::JShape); break;
            case 'l': shapes.push_back(tetrisEnum::Shape::LShape); break;
            case 'z': shapes.push_back(tetrisEnum::Shape::ZShape); break;
            case 's': shapes.push_back(tetrisEnum::Shape::SShape); break;
            }
        }
    }
    return shapes;
}
