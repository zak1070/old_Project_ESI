package Util;

/**
 * An Observable object in the Observer/Observable pattern.
 * Maintains a list of observer and notifies them
 * whenever an internal state changed.
 *
 * @author Frédéric Servais
 */
public interface Observable {

    /**
     * Adds an observer to the list of observers.
     *
     * @param observer The observer to be added.
     */
    void addObserver(Observer observer);

    /**
     * Removes the observer from the list.
     *
     * @param observer The  observer to be removed.
     */
    void removeObserver(Observer observer);

    /**
     * Notifies all observers by calling their 'update' method.
     */
    void notifyObservers();
}
