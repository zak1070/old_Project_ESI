#ifndef GAMECONFIGFORM_H
#define GAMECONFIGFORM_H

#include <QWidget>
#include <QGraphicsView>
#include <QGraphicsScene>
#include <QString>
#include <QMessageBox>

namespace Ui {
class GameConfigForm;
}

/**
 * @class GameConfigForm
 * @brief The GameConfigForm class provides a UI for configuring game settings.
 */
class GameConfigForm : public QWidget {
    Q_OBJECT

public:
    explicit GameConfigForm(QWidget* parent = nullptr);
    ~GameConfigForm();

signals:
    void startGameWithCustomSettings(const QString& name, int width, int height, bool customShapes, const QString& bagOfShapes, bool randomFill, QWidget* configPage);
    void startGameWithDefaultSettings(const QString& name, QWidget* configPage);

private slots:
    void changeInputStyle(int state);
    void startGameButton_clicked();

private:
    Ui::GameConfigForm *ui;
    QGraphicsScene _scene;

    bool isValidShapeInput(const QString& input) const;
};

#endif // GAMECONFIGFORM_H
