package by.epam.lab.threads;

import by.epam.lab.beans.Trial;
import by.epam.lab.exceptions.WriteRuntimeException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import static by.epam.lab.utils.Constants.*;

public class TrialWriter implements Runnable {
    private static final Logger LOGGER = Logger.getLogger(TrialWriter.class.getName());
    private final Queue<Trial> trialQueue;
    private final String resultName;
    private volatile boolean isWorking = true;

    public TrialWriter(Queue<Trial> trialQueue, String resultName) {
        this.trialQueue = trialQueue;
        this.resultName = resultName;
    }

    @Override
    public void run() {
        try (BufferedWriter bf = new BufferedWriter(new FileWriter(resultName))) {
            System.out.println(WRITER_STARTED);
            while (isWorking || trialQueue.size() > 0) {
                Trial trial = trialQueue.poll();
                if (trial != null) {
                    bf.write(trial + TABULATION);
                } else {
                    try {
                        TimeUnit.MILLISECONDS.sleep(50);
                    } catch (InterruptedException e) {
                        LOGGER.log(Level.WARNING, e.getMessage());
                    }
                }
            }
            bf.flush();
            System.out.println(WRITER_IS_FINISH);
        } catch (IOException e) {
            throw new WriteRuntimeException(e.getMessage());
        }
    }

    public void stopWorking() {
        isWorking = false;
    }
}
