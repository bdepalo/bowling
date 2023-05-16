import org.apache.commons.csv.CSVFormat;
import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;
import org.apache.commons.math3.stat.descriptive.rank.Median;

import javax.annotation.Nonnull;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Program {

    private final static String FILE_LOCATION = "C:\\Users\\Brandon\\IdeaProjects\\bowling\\data.csv";

    private final static GameParser parser = new GameParser();
    private final static ScoreCalculator calculator = new ScoreCalculator();

    private final static ScheduledExecutorService executor = new ScheduledThreadPoolExecutor(5);

    public static void main(String[] args) {

        try {
            List<Game> games = readInput(new File(FILE_LOCATION));

            double[] scores = games.stream()
                    .map(getScore())
                    .map(awaitProcessing())
                    .mapToDouble(Integer::doubleValue)
                    .toArray();

            printStatistics(scores);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }

    @Nonnull
    private static Function<Game, Future<Integer>> getScore() {
        return game -> executor.submit(() -> calculator.getScore(game));
    }

    @Nonnull
    private static Function<Future<Integer>, Integer> awaitProcessing() {
        return future -> {
            try {
                return future.get();
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        };
    }

    private static void printStatistics(@Nonnull double[] scores) {
        double mean = new Mean().evaluate(scores);
        System.out.println("Average: " + mean);

        double median = new Median().evaluate(scores);
        System.out.println("Median: " + median);

        double stdDev = new StandardDeviation().evaluate(scores);
        System.out.println("Standard Deviation: " + stdDev);

    }

    private static List<Game> readInput(@Nonnull File location) throws IOException {
        BufferedReader reader = Files.newBufferedReader(location.toPath());
        return CSVFormat.DEFAULT.parse(reader).stream()
                .map(x -> x.stream().collect(Collectors.joining(",")))
                .map(parser::parseGame)
                .collect(Collectors.toList());
    }
}
