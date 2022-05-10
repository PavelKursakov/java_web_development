import by.epam.lab.beans.Trial;
import by.epam.lab.threads.TrialConsumer;
import by.epam.lab.threads.TrialProducer;
import by.epam.lab.threads.TrialWriter;

import java.io.*;
import java.util.Arrays;
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
        ExecutorService producerService = Executors.newFixedThreadPool(producerNumber);
        ExecutorService consumerService = Executors.newFixedThreadPool(consumerNumber);
        ConcurrentLinkedQueue<Trial> concurrentLinkedQueue = new ConcurrentLinkedQueue<>();
        BlockingQueue<String> strQueue = new LinkedBlockingQueue<>(queueStrLength);
        File[] files = new File(folderName).listFiles();
        if(files == null) {
            files = new File[0];
            System.out.println("File is not found");
        }
        try {
            int latchCount = (int) Arrays.stream(files)
                    .filter(file -> file.toString().matches(REGEX_FOR_CSV_FILE))
                    .count();
            if(latchCount == 0) {
                System.out.println("CSV files are not found");
            }
            CountDownLatch producerLatch = new CountDownLatch(latchCount);
            CountDownLatch consLatch = new CountDownLatch(consumerNumber);
            CountDownLatch writerLatch = new CountDownLatch(1);
            for (int i = 0; i < consumerNumber; i++) {
                consumerService.execute(new TrialConsumer(concurrentLinkedQueue, strQueue,consLatch));
            }

            TrialWriter trialWriter = new TrialWriter(concurrentLinkedQueue, writerLatch
                    , new BufferedWriter(new FileWriter(RESULTS_NAME)));

            ExecutorService writerExecutor = Executors.newSingleThreadExecutor();
            writerExecutor.execute(trialWriter);
            Arrays
                    .stream(files)
                    .filter(file -> file.toString().matches(REGEX_FOR_CSV_FILE))
                    .forEach(file -> producerService.execute(
                            new TrialProducer(strQueue, producerLatch, file.toString())));
            producerLatch.await();
            producerService.shutdown();
            for (int i = 0; i < producerNumber; i++) {
                strQueue.put("DONE");
            }
            consLatch.await();
            writerLatch.countDown();
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
