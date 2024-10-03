/********************************************************************************
** Form generated from reading UI file 'gameview.ui'
**
** Created by: Qt User Interface Compiler version 6.5.2
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_GAMEVIEW_H
#define UI_GAMEVIEW_H

#include <QtCore/QVariant>
#include <QtWidgets/QApplication>
#include <QtWidgets/QGraphicsView>
#include <QtWidgets/QHBoxLayout>
#include <QtWidgets/QLCDNumber>
#include <QtWidgets/QLabel>
#include <QtWidgets/QVBoxLayout>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_GameView
{
public:
    QHBoxLayout *horizontalLayout;
    QVBoxLayout *verticalLayout_3;
    QLabel *tetrisLabel;
    QGraphicsView *graphicsView;
    QLabel *keyInstructionsLabel;
    QVBoxLayout *verticalLayout;
    QHBoxLayout *horizontalLayout_2;
    QLabel *label;
    QGraphicsView *nextBrickView;
    QHBoxLayout *horizontalLayout_6;
    QLabel *playerLabel;
    QLabel *playerNameLabel;
    QHBoxLayout *horizontalLayout_3;
    QLabel *label_2;
    QLCDNumber *scoreLCD;
    QHBoxLayout *horizontalLayout_4;
    QLabel *label_3;
    QLCDNumber *linesLCD;
    QHBoxLayout *horizontalLayout_5;
    QLabel *label_4;
    QLCDNumber *levelLCD;

    void setupUi(QWidget *GameView)
    {
        if (GameView->objectName().isEmpty())
            GameView->setObjectName("GameView");
        GameView->resize(1216, 600);
        horizontalLayout = new QHBoxLayout(GameView);
        horizontalLayout->setObjectName("horizontalLayout");
        verticalLayout_3 = new QVBoxLayout();
        verticalLayout_3->setObjectName("verticalLayout_3");
        tetrisLabel = new QLabel(GameView);
        tetrisLabel->setObjectName("tetrisLabel");
        QSizePolicy sizePolicy(QSizePolicy::Minimum, QSizePolicy::Fixed);
        sizePolicy.setHorizontalStretch(0);
        sizePolicy.setVerticalStretch(0);
        sizePolicy.setHeightForWidth(tetrisLabel->sizePolicy().hasHeightForWidth());
        tetrisLabel->setSizePolicy(sizePolicy);
        QFont font;
        font.setFamilies({QString::fromUtf8("Consolas")});
        font.setPointSize(16);
        font.setBold(false);
        tetrisLabel->setFont(font);
        tetrisLabel->setAlignment(Qt::AlignCenter);

        verticalLayout_3->addWidget(tetrisLabel);

        graphicsView = new QGraphicsView(GameView);
        graphicsView->setObjectName("graphicsView");
        QSizePolicy sizePolicy1(QSizePolicy::Minimum, QSizePolicy::Minimum);
        sizePolicy1.setHorizontalStretch(0);
        sizePolicy1.setVerticalStretch(0);
        sizePolicy1.setHeightForWidth(graphicsView->sizePolicy().hasHeightForWidth());
        graphicsView->setSizePolicy(sizePolicy1);
        keyInstructionsLabel = new QLabel(graphicsView);
        keyInstructionsLabel->setObjectName("keyInstructionsLabel");
        keyInstructionsLabel->setGeometry(QRect(0, 0, 221, 200));
        QFont font1;
        font1.setFamilies({QString::fromUtf8("Fixedsys")});
        font1.setPointSize(10);
        keyInstructionsLabel->setFont(font1);
        keyInstructionsLabel->setStyleSheet(QString::fromUtf8("color: white; background-color: transparent;"));
        keyInstructionsLabel->setAlignment(Qt::AlignLeading|Qt::AlignLeft|Qt::AlignTop);

        verticalLayout_3->addWidget(graphicsView);


        horizontalLayout->addLayout(verticalLayout_3);

        verticalLayout = new QVBoxLayout();
        verticalLayout->setObjectName("verticalLayout");
        horizontalLayout_2 = new QHBoxLayout();
        horizontalLayout_2->setObjectName("horizontalLayout_2");
        label = new QLabel(GameView);
        label->setObjectName("label");
        QFont font2;
        font2.setFamilies({QString::fromUtf8("Consolas")});
        font2.setPointSize(20);
        font2.setBold(false);
        label->setFont(font2);

        horizontalLayout_2->addWidget(label);

        nextBrickView = new QGraphicsView(GameView);
        nextBrickView->setObjectName("nextBrickView");
        QSizePolicy sizePolicy2(QSizePolicy::Preferred, QSizePolicy::Preferred);
        sizePolicy2.setHorizontalStretch(0);
        sizePolicy2.setVerticalStretch(0);
        sizePolicy2.setHeightForWidth(nextBrickView->sizePolicy().hasHeightForWidth());
        nextBrickView->setSizePolicy(sizePolicy2);

        horizontalLayout_2->addWidget(nextBrickView);


        verticalLayout->addLayout(horizontalLayout_2);

        horizontalLayout_6 = new QHBoxLayout();
        horizontalLayout_6->setObjectName("horizontalLayout_6");
        playerLabel = new QLabel(GameView);
        playerLabel->setObjectName("playerLabel");
        playerLabel->setFont(font2);

        horizontalLayout_6->addWidget(playerLabel);

        playerNameLabel = new QLabel(GameView);
        playerNameLabel->setObjectName("playerNameLabel");
        QFont font3;
        font3.setFamilies({QString::fromUtf8("Courier")});
        font3.setPointSize(20);
        font3.setBold(false);
        playerNameLabel->setFont(font3);

        horizontalLayout_6->addWidget(playerNameLabel);


        verticalLayout->addLayout(horizontalLayout_6);

        horizontalLayout_3 = new QHBoxLayout();
        horizontalLayout_3->setObjectName("horizontalLayout_3");
        label_2 = new QLabel(GameView);
        label_2->setObjectName("label_2");
        label_2->setFont(font2);

        horizontalLayout_3->addWidget(label_2);

        scoreLCD = new QLCDNumber(GameView);
        scoreLCD->setObjectName("scoreLCD");
        sizePolicy2.setHeightForWidth(scoreLCD->sizePolicy().hasHeightForWidth());
        scoreLCD->setSizePolicy(sizePolicy2);
        scoreLCD->setStyleSheet(QString::fromUtf8("color: white; background-color: black;"));
        scoreLCD->setSmallDecimalPoint(false);
        scoreLCD->setDigitCount(10);
        scoreLCD->setSegmentStyle(QLCDNumber::Flat);

        horizontalLayout_3->addWidget(scoreLCD);


        verticalLayout->addLayout(horizontalLayout_3);

        horizontalLayout_4 = new QHBoxLayout();
        horizontalLayout_4->setObjectName("horizontalLayout_4");
        label_3 = new QLabel(GameView);
        label_3->setObjectName("label_3");
        label_3->setFont(font2);

        horizontalLayout_4->addWidget(label_3);

        linesLCD = new QLCDNumber(GameView);
        linesLCD->setObjectName("linesLCD");
        sizePolicy2.setHeightForWidth(linesLCD->sizePolicy().hasHeightForWidth());
        linesLCD->setSizePolicy(sizePolicy2);
        linesLCD->setStyleSheet(QString::fromUtf8("color: white; background-color: black;"));
        linesLCD->setDigitCount(10);
        linesLCD->setSegmentStyle(QLCDNumber::Flat);

        horizontalLayout_4->addWidget(linesLCD);


        verticalLayout->addLayout(horizontalLayout_4);

        horizontalLayout_5 = new QHBoxLayout();
        horizontalLayout_5->setObjectName("horizontalLayout_5");
        label_4 = new QLabel(GameView);
        label_4->setObjectName("label_4");
        label_4->setFont(font2);

        horizontalLayout_5->addWidget(label_4);

        levelLCD = new QLCDNumber(GameView);
        levelLCD->setObjectName("levelLCD");
        sizePolicy2.setHeightForWidth(levelLCD->sizePolicy().hasHeightForWidth());
        levelLCD->setSizePolicy(sizePolicy2);
        levelLCD->setStyleSheet(QString::fromUtf8("color: white; background-color: black;"));
        levelLCD->setDigitCount(10);
        levelLCD->setSegmentStyle(QLCDNumber::Flat);

        horizontalLayout_5->addWidget(levelLCD);


        verticalLayout->addLayout(horizontalLayout_5);


        horizontalLayout->addLayout(verticalLayout);


        retranslateUi(GameView);

        QMetaObject::connectSlotsByName(GameView);
    } // setupUi

    void retranslateUi(QWidget *GameView)
    {
        tetrisLabel->setText(QCoreApplication::translate("GameView", "Tetris", nullptr));
        keyInstructionsLabel->setText(QCoreApplication::translate("GameView", " Q - Move Left\n"
" D - Move Right\n"
" S - Move Down\n"
" Z - Rotate Clockwise\n"
" A - Rotate Counter Clockwise\n"
" Space - Drop\n"
" P - Next Brick\n"
" Esc - Pause/Start\n"
" ", nullptr));
        label->setText(QCoreApplication::translate("GameView", "Next Brick:", nullptr));
        playerLabel->setText(QCoreApplication::translate("GameView", "Player:", nullptr));
        playerNameLabel->setText(QString());
        label_2->setText(QCoreApplication::translate("GameView", "Score:", nullptr));
        label_3->setText(QCoreApplication::translate("GameView", "Lines:", nullptr));
        label_4->setText(QCoreApplication::translate("GameView", "Level:", nullptr));
        (void)GameView;
    } // retranslateUi

};

namespace Ui {
    class GameView: public Ui_GameView {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_GAMEVIEW_H
