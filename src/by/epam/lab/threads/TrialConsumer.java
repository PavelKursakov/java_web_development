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
        while (true) {
            Trial trial = trialBuffer.take();
            if (trial.getClass() == FakeTrial.class) {
                break;
            }
            System.out.println(TABULATION + MESSAGE_PUT + trial);
        }
    }
}
