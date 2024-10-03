#ifndef OBSERVABLE_H
#define OBSERVABLE_H
#include "../model/GameState.h"
#include "./Observer.h"
#include <memory>
#include <vector>



class Observer;

class Observable {
public:
    Observable() = default;
    virtual ~Observable() = default;

    void addObserver(std::shared_ptr<Observer> observer);

    void removeObserver(std::shared_ptr<Observer> observer);

    void notify(GameState state) const;

private:
    std::vector<std::shared_ptr<Observer>> observers;
};

#endif // OBSERVABLE_H
