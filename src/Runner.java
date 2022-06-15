import by.epam.lab.beans.Trial;
import by.epam.lab.threads.TrialConsumer;
import by.epam.lab.threads.TrialProducer;
import by.epam.lab.threads.TrialWriter;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.ResourceBundle;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static by.epam.lab.utils.Constants.*;

public class Runner {
    private static final Logger LOGGER = Logger.getLogger(Runner.class.getName());

    public static void main(String[] args) {
        ResourceBundle rb = ResourceBundle.getBundle(TRIALS);
        String folderName = rb.getString(FOLDER_NAME);
        int producerNumber = getInt(MAX_PRODUCERS_NUMBER, rb);
        int consumerNumber = getInt(MAX_CONSUMERS_NUMBER, rb);
        int queueStrLength = getInt(QUEUE_STR_LENGTH, rb);
        ExecutorService producerService = Executors.newFixedThreadPool(producerNumber);
        ExecutorService consumerService = Executors.newFixedThreadPool(consumerNumber);
        Queue<Trial> concurrentLinkedQueue = new ConcurrentLinkedQueue<>();
        BlockingQueue<String> strQueue = new LinkedBlockingQueue<>(queueStrLength);
        TrialWriter writer = new TrialWriter(concurrentLinkedQueue, RESULTS_NAME);
        String[] files = new File(folderName).list();
        List<String> stringList = Arrays
                .stream(files)
                .filter(s -> s.matches(REGEX_FOR_CSV_FILE))
                .map(s -> FOLDER_SRC + s)
                .collect(Collectors.toList());

        System.out.println(stringList);
        CountDownLatch producerLatch = new CountDownLatch(stringList.size());

        ExecutorService writerExecutor = Executors.newSingleThreadExecutor();
        writerExecutor.execute(writer);

        IntStream.range(0, consumerNumber)
                .forEach(i -> consumerService.execute(
                        new TrialConsumer(concurrentLinkedQueue, strQueue)));

        stringList.forEach(str -> producerService.execute(
                new TrialProducer(strQueue, producerLatch, str)));

        try {
            producerLatch.await();
        } catch (InterruptedException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
            throw new IllegalStateException(INCORRECT_WAITING);
        }
        producerService.shutdown();
        IntStream.range(0, producerNumber)
                .forEach(i -> {
                        strQueue.add(DONE);
                });
        consumerService.shutdown();
        writer.stopWorking();
        writerExecutor.shutdown();
    }

    private static int getInt(String s, ResourceBundle rb) {
        return Integer.parseInt(rb.getString(s));
    }
}
