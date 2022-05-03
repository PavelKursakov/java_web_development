import by.epam.lab.beans.Trial;
import by.epam.lab.threads.TrialConsumer;
import by.epam.lab.threads.TrialProducer;
import by.epam.lab.threads.TrialWriter;

import java.io.*;
import java.util.ResourceBundle;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static by.epam.lab.utils.Constants.*;

public class Runner {
    public static void main(String[] args) {

        ResourceBundle rb = ResourceBundle.getBundle(TRIALS);
        String folderName = rb.getString(FOLDER_NAME);
        int producerNumber = getInt(MAX_PRODUCERS_NUMBER, rb);
        int consumerNumber = getInt(MAX_CONSUMERS_NUMBER, rb);
        int queuePassedLength = getInt(QUEUE_PASSED_LENGTH, rb);
        int queueStrLength = getInt(QUEUE_STR_LENGTH, rb);

        CountDownLatch stopPutting = new CountDownLatch(queueStrLength);

        Lock locker = new ReentrantLock();
        CountDownLatch latch = new CountDownLatch(2);
        ExecutorService producerService = Executors.newFixedThreadPool(producerNumber);
        ExecutorService consumerService = Executors.newFixedThreadPool(consumerNumber);
        BlockingQueue<Trial> passedTrial = new LinkedBlockingQueue<>(queuePassedLength);
        BlockingQueue<String> strQueue = new LinkedBlockingQueue<>(queueStrLength);
        File[] files = new File(folderName).listFiles();
        try {
            int counter = 1;

            TrialWriter trialWriter = new TrialWriter(passedTrial,
                    new BufferedWriter(new FileWriter(RESULTS_NAME)), latch);
            Thread writer = new Thread(trialWriter);
            writer.start();
            for (File file : files) {
                if (file.toString().matches(REGEX_FOR_CSV_FILE)) {
                    TrialProducer trialProducer =
                            new TrialProducer(strQueue, file.toString(), stopPutting, counter);
                    TrialConsumer trialConsumer =
                            new TrialConsumer(passedTrial, strQueue, stopPutting, counter);
                    producerService.execute(trialProducer);
                    consumerService.execute(trialConsumer);
                    counter++;
                }
            }
            producerService.shutdown();
            consumerService.shutdown();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
    }

    private static int getInt(String s, ResourceBundle rb) {
        return Integer.parseInt(rb.getString(s));
    }
}
