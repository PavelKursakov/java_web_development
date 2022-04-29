package by.epam.lab.services;

import by.epam.lab.beans.Trial;

public class TrialBuffer {
    private Trial trial;
    private boolean empty = true;

    public synchronized Trial take() {
        while (empty) {
            try {
                wait();
            } catch (InterruptedException e) {
//The thread won’t be interrupted while waiting!
            }
        }
        empty = true;
        notifyAll();
        return trial;
    }

    public synchronized void put(Trial trial) {
        while (!empty) {
            try {
                wait();
            } catch (InterruptedException e) {
//The thread won’t be interrupted while waiting!
            }
        }
        empty = false;
        this.trial = trial;
        notifyAll();
    }
}
