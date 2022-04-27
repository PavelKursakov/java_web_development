package by.epam.lab.threads;

import by.epam.lab.beans.Trial;
import by.epam.lab.services.Drop;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import static by.epam.lab.utils.Constants.*;

public class Producer implements Runnable {
    private Drop drop;

    public Producer(Drop drop) {
        this.drop = drop;
    }

    @Override
    public void run() {
        try (Scanner sc = new Scanner(new FileReader(CSV_NAME))) {
            while (sc.hasNextLine()) {
                drop.put(new Trial(sc.next().split(DELIMITER)));
            }
            drop.put(new Trial(MESSAGE_DONE,0,0));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
