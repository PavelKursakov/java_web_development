package by.epam.lab.threads;

import by.epam.lab.FakeTrial;
import by.epam.lab.beans.Trial;
import by.epam.lab.services.TrialBuffer;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import static by.epam.lab.utils.Constants.*;

public class TrialProducer implements Runnable {
    private TrialBuffer trialBuffer;
    private String csvName;

    public TrialProducer(TrialBuffer trialBuffer, String csvName) {
        this.trialBuffer = trialBuffer;
        this.csvName = csvName;
    }

    @Override
    public void run() {
        try (Scanner sc = new Scanner(new FileReader(csvName))) {
            while (sc.hasNextLine()) {
                Trial trial = new Trial(sc.next().split(DELIMITER));
                trialBuffer.put(trial);
            }
        } catch (FileNotFoundException e) {
            System.err.println(FILE_IS_NOT_FOUND);
        } finally {
            trialBuffer.put(new FakeTrial());
        }
    }
}
