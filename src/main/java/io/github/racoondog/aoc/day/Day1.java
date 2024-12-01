package io.github.racoondog.aoc.day;

import io.github.racoondog.aoc.Day;
import it.unimi.dsi.fastutil.ints.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class Day1 extends Day {
    public static void main(String[] args) {
        new Day1().run();
    }

    public Day1() {
        super(1);
    }

    @Override
    public String solveFirst(Path input) throws IOException {
        IntList left = new IntArrayList();
        IntList right = new IntArrayList();

        try (Stream<String> inputs = Files.lines(input)) {
            inputs.forEach(line -> {
                left.add(Integer.parseInt(line, 0, 5, 10));
                right.add(Integer.parseInt(line, 8, 13, 10));
            });
        }

        left.sort(null);
        right.sort(null);

        long distSum = 0L;
        IntIterator leftIt = left.intIterator();
        IntIterator rightIt = right.intIterator();

        while (leftIt.hasNext() && rightIt.hasNext()) {
            distSum += Math.abs(leftIt.nextInt() - rightIt.nextInt());
        }

        return Long.toString(distSum);
    }

    @Override
    public String solveSecond(Path input) throws IOException {
        IntList left = new IntArrayList();
        Int2IntMap right = new Int2IntRBTreeMap();

        try (Stream<String> inputs = Files.lines(input)) {
            inputs.forEach(line -> {
                left.add(Integer.parseInt(line, 0, 5, 10));
                int r = Integer.parseInt(line, 8, 13, 10);
                right.put(r, right.getOrDefault(r, 0) + 1);
            });
        }

        long sum = 0L;
        for (IntIterator it = left.iterator(); it.hasNext();) {
            int i = it.nextInt();
            sum += (long) i * right.getOrDefault(i, 0);
        }

        return Long.toString(sum);
    }
}
