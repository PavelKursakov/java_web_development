import by.epam.lab.beans.Trial;
import by.epam.lab.threads.TrialConsumer;
import by.epam.lab.threads.TrialProducer;
import by.epam.lab.threads.TrialWriter;

import java.io.*;
import java.util.Arrays;
import java.util.List;
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
        List<Trial> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        BlockingQueue<String> strQueue = new LinkedBlockingQueue<>(queueStrLength);
        File[] files = new File(folderName).listFiles();
        try {
            for (int i = 0; i < consumerNumber; i++) {
                consumerService.execute(new TrialConsumer(copyOnWriteArrayList, strQueue));
            }

            TrialWriter trialWriter = new TrialWriter(copyOnWriteArrayList,
                    new BufferedWriter(new FileWriter(RESULTS_NAME)));

            Thread writer = new Thread(trialWriter);

            Arrays
                    .stream(files)
                    .filter(file -> file.toString().matches(REGEX_FOR_CSV_FILE))
                    .forEach(file -> producerService.execute(
                            new TrialProducer(strQueue, file.toString())));

            writer.start();
            writer.join();

            for (int i = 0; i < producerNumber; i++) {
                strQueue.put("DONE");
            }

            producerService.shutdown();
            consumerService.shutdown();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static int getInt(String s, ResourceBundle rb) {
        return Integer.parseInt(rb.getString(s));
    }
}
