package by.epam.lab.threads;

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
                drop.put(sc.next());
            }
            drop.put(MESSAGE_DONE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
