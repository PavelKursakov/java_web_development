package by.epam.lab.threads;

import by.epam.lab.beans.FakeTrial;
import by.epam.lab.beans.Trial;
import by.epam.lab.services.TrialBuffer;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import static by.epam.lab.utils.Constants.*;

public class TrialProducer implements Runnable {
    private TrialBuffer trialBuffer;
    private Scanner sc;

    public TrialProducer(TrialBuffer trialBuffer, String csvName) throws FileNotFoundException {
        this.trialBuffer = trialBuffer;
        this.sc = new Scanner(new FileReader(csvName));
    }

    @Override
    public void run() {
        try {
            while (sc.hasNextLine()) {
                Trial trial = new Trial(sc.next().split(DELIMITER));
                System.out.println(MESSAGE_GOT + trial);
                trialBuffer.put(trial);
            }
        } finally {
            trialBuffer.put(new FakeTrial());
            sc.close();
        }
    }
}
