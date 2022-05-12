package by.epam.lab.threads;

import by.epam.lab.beans.Flag;
import by.epam.lab.beans.Trial;

import java.io.BufferedWriter;
import java.io.IOException;

import java.util.Queue;

import static by.epam.lab.utils.Constants.*;

public class TrialWriter implements Runnable {
    private final Queue<Trial> linkedQueue;
    private final BufferedWriter bufferedWriter;
    private Flag flag;

    public TrialWriter(Queue<Trial> linkedQueue, BufferedWriter bufferedWriter, Flag flag) {
        this.linkedQueue = linkedQueue;
        this.bufferedWriter = bufferedWriter;
        this.flag = flag;
    }

    @Override
    public void run() {
        try {
            int i = 0;
            System.out.println("WRITER STARTED");
            while (flag.isProducing() || linkedQueue.size() > 0) {
                Trial trial = linkedQueue.poll();
                if(trial != null) {
                    bufferedWriter.write(trial + TABULATION);
                    bufferedWriter.flush();
                } else {
                    i++;
                    System.out.println(i + " Empty use");
//                    while (true){
//                        if (linkedQueue.size() != 0){
//                            break;
//                        }
//                    }
                }
            }
            System.out.println("WRITER IS FINISHED");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
