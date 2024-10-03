#ifndef OBSERVER_H
#define OBSERVER_H
#include "../model/GameState.h"

class Observer
{
public:
    virtual ~Observer() = default;
    virtual void update(GameState state) = 0;
};

#endif // OBSERVER_H
