#include "../util/Observable.h"
#include <algorithm>

void Observable::addObserver(std::shared_ptr<Observer> observer) {
    observers.push_back(observer);
}

void Observable::removeObserver(std::shared_ptr<Observer> observer) {
    auto it = std::find(observers.begin(), observers.end(), observer);
    if (it != observers.end()) {
        observers.erase(it);
    }
}

void Observable::notify(GameState state) const {

    for (std::shared_ptr<Observer>  observer : observers) {
        observer->update(state);
    }
}
