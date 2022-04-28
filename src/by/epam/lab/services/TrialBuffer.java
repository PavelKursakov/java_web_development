package by.epam.lab.services;

import by.epam.lab.beans.Trial;

import static by.epam.lab.utils.Constants.*;

public class TrialBuffer {
    private Trial trial;
    private boolean empty = true;

    public synchronized Trial take() {
        while (empty) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.err.println(THE_THREAD_WONT_BE_INTERRUPTED);
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
                System.err.println(THE_THREAD_WONT_BE_INTERRUPTED);
            }
        }
        empty = false;
        this.trial = trial;
        notifyAll();
    }
}
