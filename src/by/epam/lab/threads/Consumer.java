package by.epam.lab.threads;

import by.epam.lab.beans.Trial;
import by.epam.lab.services.Drop;

import static by.epam.lab.utils.Constants.*;

public class Consumer implements Runnable {
    private Drop drop;

    public Consumer(Drop drop) {
        this.drop = drop;
    }

    @Override
    public void run() {
        for (Trial trial = drop.take();
             !(trial.equals(new Trial(null,0,0)));
             trial = drop.take()) {
            System.out.println(TABULATION + MESSAGE_PUT + trial);
        }
    }
}
