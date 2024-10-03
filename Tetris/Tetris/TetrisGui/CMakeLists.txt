cmake_minimum_required(VERSION 3.5)

project(TetrisGui LANGUAGES CXX)

set(CMAKE_CXX_STANDARD 20)
set(CMAKE_CXX_STANDARD_REQUIRED ON)

# ask for all warnings and strict compliance with the standard
set(CMAKE_CXX_FLAGS "-Wall -pedantic-errors")

set (CMAKE_AUTOUIC ON)
set (CMAKE_AUTOMOC ON)
set (CMAKE_AUTORCC ON)



add_executable(TetrisGui main.cpp
    model/Brick.h
    model/Position.h
    model/Board.h
    model/Game.h
    model/Player.h
    model/Bag.h
    model/Enums.h

    model/RelatedPositions.h
    model/GameState.h

    model/Brick.cpp
    model/Position.cpp
    model/Board.cpp
    model/RelatedPositions.cpp
    model/Player.cpp
    model/Bag.cpp
    model/Game.cpp

    util/Observer.h
    util/Observable.h
    util/Observable.cpp





    model/TetrisException.h
    main.cpp
    view/GameConfigForm.h
    view/GameConfigForm.cpp
    view/GameConfigForm.ui
    controller/GameControllerGUI.h
    controller/GameControllerGUI.cpp
    view/gameview.h view/gameview.cpp view/gameview.ui
)

find_package (QT NAMES Qt6 Qt5 REQUIRED COMPONENTS Widgets )
find_package ( Qt${QT_VERSION_MAJOR} REQUIRED COMPONENTS Widgets )
target_link_libraries (TetrisGui PRIVATE Qt${QT_VERSION_MAJOR}::Widgets )


include(GNUInstallDirs)
install(TARGETS TetrisGui
    LIBRARY DESTINATION ${CMAKE_INSTALL_LIBDIR}
    RUNTIME DESTINATION ${CMAKE_INSTALL_BINDIR}
)
