package io.github.racoondog.aoc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public abstract class Day {
    private static final Path INPUT_CACHE_DIR = Path.of("aoc-inputs");
    private final Path input;
    public final int day;

    public Day(int day) {
        this.day = day;
        this.input = INPUT_CACHE_DIR.resolve(day + ".txt");
    }

    public void run() {
        if (!Files.isRegularFile(this.input)) {
            Util.downloadInput(this.day, this.input);
        }

        try {
            long firstStartTime = System.nanoTime();
            String firstResult = solveFirst(this.input);
            long firstEndTime = System.nanoTime();

            if (firstResult != null) {
                System.out.printf("[Advent of Code] Day %d first half solution '%s'.%n", this.day, firstResult);
                System.out.printf("[Advent of Code] Took %.2f μs.", (firstEndTime - firstStartTime) / 1000f);
            }

            long secondStartTime = System.nanoTime();
            String secondResult = solveSecond(this.input);
            long secondEndTime = System.nanoTime();

            if (secondResult != null) {
                System.out.printf("[Advent of Code] Day %d second half solution '%s'.%n", this.day, secondResult);
                System.out.printf("[Advent of Code] Took %.2f μs.", (secondEndTime - secondStartTime) / 1000f);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public abstract String solveFirst(Path input) throws IOException;

    public abstract String solveSecond(Path input) throws IOException;
}
