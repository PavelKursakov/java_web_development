package by.epam.lab.threads;

import by.epam.lab.beans.Flag;
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
    private Flag flag;

    public TrialWriter(Queue<Trial> trialQueue, String resultName, Flag flag) {
        this.trialQueue = trialQueue;
        this.resultName = resultName;
        this.flag = flag;
    }

    @Override
    public void run() {
        try (BufferedWriter bf = new BufferedWriter(new FileWriter(resultName))){
            int i = 0;
            System.out.println("WRITER STARTED");
            while (flag.isProducing() || trialQueue.size() > 0) {
                Trial trial = trialQueue.poll();
                if(trial != null) {
                    bf.write(trial + TABULATION);
                } else {
                    i++;
                    System.out.println(i + " Empty use");
                    try {
                        TimeUnit.MILLISECONDS.sleep(50);
                    } catch (InterruptedException e) {
                        LOGGER.log(Level.WARNING, e.getMessage());
                    }
                }
            }
            bf.flush();
            System.out.println("WRITER IS FINISHED");
        } catch (IOException e) {
            throw new WriteRuntimeException(e.getMessage());
        }
    }
}
