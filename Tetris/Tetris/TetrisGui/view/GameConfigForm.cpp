#include "GameConfigForm.h"
#include "ui_GameConfigForm.h"


GameConfigForm::GameConfigForm(QWidget* parent) :
    QWidget(parent),
    ui(new Ui::GameConfigForm),
    _scene(this)
{
    ui->setupUi(this);

    // Set the scene for the QGraphicsView

    connect(ui->customSettingCheckBox, &QCheckBox::stateChanged, this, &GameConfigForm::changeInputStyle);
    connect(ui->startGameButton, &QPushButton::clicked, this, &GameConfigForm::startGameButton_clicked, Qt::UniqueConnection);

    // Set default state for the custom settings
    changeInputStyle(ui->customSettingCheckBox->checkState());
}

GameConfigForm::~GameConfigForm()
{
    delete ui;
}

// Slot that enables or disables entries based on the 'Use custom settings' checkbox state
void GameConfigForm::changeInputStyle(int state)
{
    bool enabled = (state == Qt::Checked);

    // Enable/disable widgets
    ui->gridWidthSpinBox->setEnabled(enabled);
    ui->gridHeightSpinBox->setEnabled(enabled);
    ui->customShapesCheckBox->setEnabled(enabled);
    ui->bagOfShapesLineEdit->setEnabled(enabled);
    ui->randomFillCheckBox->setEnabled(enabled);

    // Enable/disable and style labels
    ui->gridWidthLabel->setStyleSheet(enabled ? "" : "color: gray;");
    ui->gridHeightLabel->setStyleSheet(enabled ? "" : "color: gray;");
    ui->customShapesCheckBox->setStyleSheet(enabled ? "" : "color: gray;");
    ui->randomFillCheckBox->setStyleSheet(enabled ? "" : "color: gray;");
}


// Slot to handle the start game button click event
void GameConfigForm::startGameButton_clicked() {
    QString name = ui->playerNameLineEdit->text();
    QString shapesOfBag = ui->bagOfShapesLineEdit->text();
    bool customSettingsChecked = ui->customSettingCheckBox->isChecked();
    bool allFieldsFilled = true;


    // Check if necessary fields are filled
    if (name.isEmpty() || (customSettingsChecked && ui->customShapesCheckBox->isChecked() && shapesOfBag.isEmpty())) {
        QMessageBox::critical(this, "Error", "Fill all fields.");
        return; // Stop execution if fields are missing
    }

     // Check if shape input is valid
    if (customSettingsChecked && ui->customShapesCheckBox->isChecked() && !isValidShapeInput(shapesOfBag)) {
        QMessageBox::critical(this, "Error", "Shape are not valid please enter valid shapes.");
        return; // Stop execution if shape input is invalid
    }

    if (allFieldsFilled) {
        int width = ui->gridWidthSpinBox->value();
        int height = ui->gridHeightSpinBox->value();
        bool randomFill = ui->randomFillCheckBox->isChecked();


        // Emit signal to start the game with custom or default settings based on the checkbox state
        if (customSettingsChecked) {
            emit startGameWithCustomSettings(name, width, height, ui->customShapesCheckBox->isChecked(),
                                             shapesOfBag, randomFill, this);
        } else {
            emit startGameWithDefaultSettings(name, this);
        }
    }
}


bool GameConfigForm::isValidShapeInput(const QString& input) const {
    // Valid shapes as a single string for easier lookup
    QString validShapes = "iotsjzl";
    QStringList tokens = input.split(',', Qt::SkipEmptyParts); // Split the string by commas, ignoring empty parts

    for (QString token : tokens) {
        token = token.trimmed().toLower(); // Clean each token by removing spaces and converting to lowercase

        // Ensure each cleaned token is of length 1 and contained in the valid shapes
        if (token.length() != 1 || !validShapes.contains(token[0])) {
            return false; // Return false if a token is not exactly a valid single letter
        }
    }

    return true; // All tokens are valid
}
