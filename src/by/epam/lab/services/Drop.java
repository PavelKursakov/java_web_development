package by.epam.lab.services;

import static by.epam.lab.utils.Constants.*;

public class Drop {
    private String message;
    private boolean empty = true;

    public synchronized String take() {
        while (empty) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        if (!message.equals(MESSAGE_PUT)) {
            System.out.println(MESSAGE_GOT + message);
        }
        empty = true;
        notifyAll();
        return message;
    }

    public synchronized void put(String message) {
        while (!empty) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        empty = false;
        this.message = message;
        notifyAll();
    }
}
