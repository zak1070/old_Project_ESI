#include "./view/ConsoleView.h"
#include"./controller/GameController.h"

using namespace std;


int main()
{
    ConsoleView view;
    GameController controller (view);
    controller.run();
    return 0;

}

