import by.epam.lab.beans.Trial;
import by.epam.lab.threads.TrialConsumer;
import by.epam.lab.threads.TrialProducer;
import by.epam.lab.threads.TrialWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.*;

import static by.epam.lab.utils.Constants.*;

public class Runner {
    public static void main(String[] args) {
        ResourceBundle rb = ResourceBundle.getBundle(TRIALS);
        String folderName = rb.getString(FOLDER_NAME);
        int producerNumber = getInt(MAX_PRODUCERS_NUMBER, rb);
        int consumerNumber = getInt(MAX_CONSUMERS_NUMBER, rb);
        int queueStrLength = getInt(QUEUE_STR_LENGTH, rb);
        CountDownLatch stop = new CountDownLatch(1);
        ExecutorService producerService = Executors.newFixedThreadPool(producerNumber);
        ExecutorService consumerService = Executors.newFixedThreadPool(consumerNumber);
        BlockingQueue<Trial> passedTrial = new LinkedBlockingQueue<>(30);
        BlockingQueue<String> strQueue = new LinkedBlockingQueue<>(queueStrLength);
        StringBuilder sb = new StringBuilder("NOT_DONE");
        File[] files = new File(folderName).listFiles();
        try {
            TrialConsumer trialConsumer1 =
                    new TrialConsumer(passedTrial, strQueue, sb);
            TrialConsumer trialConsumer2 =
                    new TrialConsumer(passedTrial, strQueue, sb);
            TrialConsumer trialConsumer3 =
                    new TrialConsumer(passedTrial, strQueue, sb);

            consumerService.execute(trialConsumer1);
            consumerService.execute(trialConsumer2);
            consumerService.execute(trialConsumer3);

            TrialWriter trialWriter = new TrialWriter(passedTrial,
                    new BufferedWriter(new FileWriter(RESULTS_NAME)), stop);
            Thread writer = new Thread(trialWriter);
            ExecutorService writerExecutor = Executors.newSingleThreadExecutor();
            writerExecutor.execute(writer);
            writer.join();
            for (File file : files) {
                if (file.toString().matches(REGEX_FOR_CSV_FILE)) {
                    producerService.execute(new TrialProducer(strQueue, file.toString()));
                }
            }
            stop.countDown();
            producerService.shutdown();
            consumerService.shutdown();
            writerExecutor.shutdown();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static int getInt(String s, ResourceBundle rb) {
        return Integer.parseInt(rb.getString(s));
    }
}
