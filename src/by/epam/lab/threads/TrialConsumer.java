package by.epam.lab.threads;

import by.epam.lab.beans.FakeTrial;
import by.epam.lab.beans.Trial;
import by.epam.lab.services.TrialBuffer;

import static by.epam.lab.utils.Constants.*;

public class TrialConsumer implements Runnable {
    private TrialBuffer trialBuffer;

    public TrialConsumer(TrialBuffer drop) {
        this.trialBuffer = drop;
    }

    @Override
    public void run() {
        for (Trial trial = trialBuffer.take();
             trial.getClass() != FakeTrial.class;
             trial = trialBuffer.take()) {
            System.out.println(TABULATION + MESSAGE_PUT + trial);
        }
    }
}
