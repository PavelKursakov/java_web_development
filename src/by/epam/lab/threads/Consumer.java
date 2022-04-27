package by.epam.lab.threads;

import by.epam.lab.services.Drop;

import static by.epam.lab.utils.Constants.*;

public class Consumer implements Runnable {
    private Drop drop;

    public Consumer(Drop drop) {
        this.drop = drop;
    }

    @Override
    public void run() {
        for (String message = drop.take();
             !message.equals(MESSAGE_DONE);
             message = drop.take()) {
            System.out.println(MESSAGE_PUT + message);
        }
    }
}
