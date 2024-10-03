/********************************************************************************
** Form generated from reading UI file 'GameConfigForm.ui'
**
** Created by: Qt User Interface Compiler version 6.5.2
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_GAMECONFIGFORM_H
#define UI_GAMECONFIGFORM_H

#include <QtCore/QVariant>
#include <QtWidgets/QApplication>
#include <QtWidgets/QCheckBox>
#include <QtWidgets/QGraphicsView>
#include <QtWidgets/QLabel>
#include <QtWidgets/QLineEdit>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QSpinBox>
#include <QtWidgets/QVBoxLayout>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_GameConfigForm
{
public:
    QVBoxLayout *verticalLayout;
    QGraphicsView *graphicsView;
    QVBoxLayout *containerLayout;
    QLabel *titleLabel;
    QCheckBox *customSettingCheckBox;
    QLabel *playerNameLabel;
    QLineEdit *playerNameLineEdit;
    QLabel *gridWidthLabel;
    QSpinBox *gridWidthSpinBox;
    QLabel *gridHeightLabel;
    QSpinBox *gridHeightSpinBox;
    QCheckBox *customShapesCheckBox;
    QLineEdit *bagOfShapesLineEdit;
    QCheckBox *randomFillCheckBox;
    QPushButton *startGameButton;

    void setupUi(QWidget *GameConfigForm)
    {
        if (GameConfigForm->objectName().isEmpty())
            GameConfigForm->setObjectName("GameConfigForm");
        GameConfigForm->resize(421, 471);
        verticalLayout = new QVBoxLayout(GameConfigForm);
        verticalLayout->setObjectName("verticalLayout");
        graphicsView = new QGraphicsView(GameConfigForm);
        graphicsView->setObjectName("graphicsView");
        containerLayout = new QVBoxLayout(graphicsView);
        containerLayout->setObjectName("containerLayout");
        titleLabel = new QLabel(graphicsView);
        titleLabel->setObjectName("titleLabel");
        QFont font;
        font.setFamilies({QString::fromUtf8("Berlin Sans FB")});
        font.setPointSize(22);
        font.setBold(true);
        titleLabel->setFont(font);
        titleLabel->setAlignment(Qt::AlignCenter);

        containerLayout->addWidget(titleLabel);

        customSettingCheckBox = new QCheckBox(graphicsView);
        customSettingCheckBox->setObjectName("customSettingCheckBox");
        QFont font1;
        font1.setFamilies({QString::fromUtf8("8514oem")});
        customSettingCheckBox->setFont(font1);

        containerLayout->addWidget(customSettingCheckBox);

        playerNameLabel = new QLabel(graphicsView);
        playerNameLabel->setObjectName("playerNameLabel");
        playerNameLabel->setEnabled(true);
        QFont font2;
        font2.setFamilies({QString::fromUtf8("8514oem")});
        font2.setPointSize(12);
        font2.setBold(false);
        playerNameLabel->setFont(font2);

        containerLayout->addWidget(playerNameLabel);

        playerNameLineEdit = new QLineEdit(graphicsView);
        playerNameLineEdit->setObjectName("playerNameLineEdit");
        playerNameLineEdit->setEnabled(true);
        QFont font3;
        font3.setFamilies({QString::fromUtf8("8514oem")});
        font3.setPointSize(12);
        playerNameLineEdit->setFont(font3);

        containerLayout->addWidget(playerNameLineEdit);

        gridWidthLabel = new QLabel(graphicsView);
        gridWidthLabel->setObjectName("gridWidthLabel");
        gridWidthLabel->setEnabled(true);
        gridWidthLabel->setFont(font3);

        containerLayout->addWidget(gridWidthLabel);

        gridWidthSpinBox = new QSpinBox(graphicsView);
        gridWidthSpinBox->setObjectName("gridWidthSpinBox");
        gridWidthSpinBox->setEnabled(true);
        gridWidthSpinBox->setFont(font3);
        gridWidthSpinBox->setMinimum(5);
        gridWidthSpinBox->setValue(10);

        containerLayout->addWidget(gridWidthSpinBox);

        gridHeightLabel = new QLabel(graphicsView);
        gridHeightLabel->setObjectName("gridHeightLabel");
        gridHeightLabel->setEnabled(true);
        gridHeightLabel->setFont(font3);

        containerLayout->addWidget(gridHeightLabel);

        gridHeightSpinBox = new QSpinBox(graphicsView);
        gridHeightSpinBox->setObjectName("gridHeightSpinBox");
        gridHeightSpinBox->setEnabled(true);
        gridHeightSpinBox->setFont(font3);
        gridHeightSpinBox->setMinimum(10);
        gridHeightSpinBox->setValue(20);

        containerLayout->addWidget(gridHeightSpinBox);

        customShapesCheckBox = new QCheckBox(graphicsView);
        customShapesCheckBox->setObjectName("customShapesCheckBox");
        customShapesCheckBox->setEnabled(true);
        QSizePolicy sizePolicy(QSizePolicy::Preferred, QSizePolicy::Preferred);
        sizePolicy.setHorizontalStretch(0);
        sizePolicy.setVerticalStretch(0);
        sizePolicy.setHeightForWidth(customShapesCheckBox->sizePolicy().hasHeightForWidth());
        customShapesCheckBox->setSizePolicy(sizePolicy);
        customShapesCheckBox->setFont(font3);

        containerLayout->addWidget(customShapesCheckBox);

        bagOfShapesLineEdit = new QLineEdit(graphicsView);
        bagOfShapesLineEdit->setObjectName("bagOfShapesLineEdit");
        bagOfShapesLineEdit->setEnabled(true);
        bagOfShapesLineEdit->setFont(font3);
        bagOfShapesLineEdit->setStyleSheet(QString::fromUtf8("color: grey;"));

        containerLayout->addWidget(bagOfShapesLineEdit);

        randomFillCheckBox = new QCheckBox(graphicsView);
        randomFillCheckBox->setObjectName("randomFillCheckBox");
        randomFillCheckBox->setEnabled(true);
        sizePolicy.setHeightForWidth(randomFillCheckBox->sizePolicy().hasHeightForWidth());
        randomFillCheckBox->setSizePolicy(sizePolicy);
        randomFillCheckBox->setFont(font3);

        containerLayout->addWidget(randomFillCheckBox);

        startGameButton = new QPushButton(graphicsView);
        startGameButton->setObjectName("startGameButton");
        startGameButton->setFont(font3);

        containerLayout->addWidget(startGameButton);


        verticalLayout->addWidget(graphicsView);


        retranslateUi(GameConfigForm);

        QMetaObject::connectSlotsByName(GameConfigForm);
    } // setupUi

    void retranslateUi(QWidget *GameConfigForm)
    {
        GameConfigForm->setWindowTitle(QCoreApplication::translate("GameConfigForm", "Game Configuration", nullptr));
        titleLabel->setText(QCoreApplication::translate("GameConfigForm", "TETRIS", nullptr));
        customSettingCheckBox->setText(QCoreApplication::translate("GameConfigForm", "Use custom settings", nullptr));
        playerNameLabel->setText(QCoreApplication::translate("GameConfigForm", "Player Name:", nullptr));
        gridWidthLabel->setText(QCoreApplication::translate("GameConfigForm", "Grid Width:", nullptr));
        gridHeightLabel->setText(QCoreApplication::translate("GameConfigForm", "Grid Height:", nullptr));
        customShapesCheckBox->setText(QCoreApplication::translate("GameConfigForm", "Use Custom Bag of Shapes", nullptr));
        bagOfShapesLineEdit->setPlaceholderText(QCoreApplication::translate("GameConfigForm", "e.g.: \"I, O, T, J, L, Z, S\"", nullptr));
        randomFillCheckBox->setText(QCoreApplication::translate("GameConfigForm", "Randomly Fill the Board", nullptr));
        startGameButton->setText(QCoreApplication::translate("GameConfigForm", "Start Game", nullptr));
    } // retranslateUi

};

namespace Ui {
    class GameConfigForm: public Ui_GameConfigForm {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_GAMECONFIGFORM_H
