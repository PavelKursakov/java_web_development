package by.epam.lab.services;

import by.epam.lab.beans.FakeTrial;
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
            }
        }
        if (trial.getClass() != FakeTrial.class) {
            System.out.println(MESSAGE_GOT + trial);
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
            }
        }
        empty = false;
        this.trial = trial;
        notifyAll();
    }
}
