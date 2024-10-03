#include <QApplication>
#include "./view/GameConfigForm.h"
#include "controller/GameControllerGUI.h"
using namespace std;


int main(int argc, char* argv[]) {
    QApplication app(argc, argv);

    GameConfigForm configForm;
    GameControllerGUI controller;

    //connects the signaux of the config pages to the slots of the controller
    QObject::connect(&configForm, &GameConfigForm::startGameWithCustomSettings,
                     &controller, &GameControllerGUI::initializeGameWithCustomSettings, Qt::UniqueConnection);

    QObject::connect(&configForm, &GameConfigForm::startGameWithDefaultSettings,
                     &controller, &GameControllerGUI::initializeGameWithDefaultSettings, Qt::UniqueConnection);

    configForm.show();
    return app.exec();
}


