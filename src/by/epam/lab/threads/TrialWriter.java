package by.epam.lab.threads;

import by.epam.lab.beans.Flag;
import by.epam.lab.beans.Trial;

import java.io.BufferedWriter;
import java.io.IOException;

import java.util.concurrent.ConcurrentLinkedQueue;

import static by.epam.lab.utils.Constants.*;

public class TrialWriter implements Runnable {
    private final ConcurrentLinkedQueue<Trial> linkedQueue;
    private final BufferedWriter bufferedWriter;
    private Flag flag;

    public TrialWriter(ConcurrentLinkedQueue<Trial> linkedQueue, BufferedWriter bufferedWriter, Flag flag) {
        this.linkedQueue = linkedQueue;
        this.bufferedWriter = bufferedWriter;
        this.flag = flag;
    }

    @Override
    public void run() {
        try {
            System.out.println("WRITER STARTED");
            while (flag.isProducing() || linkedQueue.size() > 0) {
                Trial trial = linkedQueue.poll();
                if(trial != null) {
                    bufferedWriter.write(trial + TABULATION);
                    bufferedWriter.flush();
                }
            }
            System.out.println("WRITER IS FINISHED");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
