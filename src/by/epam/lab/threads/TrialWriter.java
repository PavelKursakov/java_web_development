package by.epam.lab.threads;

import by.epam.lab.beans.Trial;

import java.io.BufferedWriter;
import java.io.IOException;

import java.util.List;

import static by.epam.lab.utils.Constants.*;

public class TrialWriter implements Runnable {
    private final List<Trial> copyOnWriteArrayList;
    private final BufferedWriter bufferedWriter;

    public TrialWriter(List<Trial> copyOnWriteArrayList,
                       BufferedWriter bufferedWriter) {
        this.copyOnWriteArrayList = copyOnWriteArrayList;
        this.bufferedWriter = bufferedWriter;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            while (!copyOnWriteArrayList.isEmpty()) {
                Trial trial = copyOnWriteArrayList.get(0);
                bufferedWriter.write(trial + TABULATION);
                bufferedWriter.flush();
                copyOnWriteArrayList.remove(0);
            }
        } catch (IOException | InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }
}
