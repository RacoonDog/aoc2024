package io.github.racoondog.aoc.day;

import io.github.racoondog.aoc.Day;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class Day4 extends Day {
    public static void main(String[] args) {
        new Day4().run();
    }

    public Day4() {
        super(4);
    }

    @Override
    public String solveFirst(Path input) throws IOException {
        try (Stream<String> lines = Files.lines(input)) {
            char[][] arr = lines.map(String::toCharArray).toArray(char[][]::new);
            int total = 0;

            for (int x = 0; x < arr.length - 3; x++) {
                for (int y = 0; y < arr[x].length; y++) {
                    if (arr[x][y] == 'X' && arr[x + 1][y] == 'M' && arr[x + 2][y] == 'A' && arr[x + 3][y] == 'S') {
                        total++;
                    } else if (arr[x][y] == 'S' && arr[x + 1][y] == 'A' && arr[x + 2][y] == 'M' && arr[x + 3][y] == 'X') {
                        total++;
                    }
                }
            }

            for (int x = 0; x < arr.length; x++) {
                for (int y = 0; y < arr[x].length - 3; y++) {
                    if (arr[x][y] == 'X' && arr[x][y + 1] == 'M' && arr[x][y + 2] == 'A' && arr[x][y + 3] == 'S') {
                        total++;
                    } else if (arr[x][y] == 'S' && arr[x][y + 1] == 'A' && arr[x][y + 2] == 'M' && arr[x][y + 3] == 'X') {
                        total++;
                    }
                }
            }

            for (int x = 0; x < arr.length - 3; x++) {
                for (int y = 0; y < arr[x].length - 3; y++) {
                    if (arr[x][y] == 'X' && arr[x + 1][y + 1] == 'M' && arr[x + 2][y + 2] == 'A' && arr[x + 3][y + 3] == 'S') {
                        total++;
                    } else if (arr[x][y] == 'S' && arr[x + 1][y + 1] == 'A' && arr[x + 2][y + 2] == 'M' && arr[x + 3][y + 3] == 'X') {
                        total++;
                    }

                    if (arr[x + 3][y] == 'X' && arr[x + 2][y + 1] == 'M' && arr[x + 1][y + 2] == 'A' && arr[x][y + 3] == 'S') {
                        total++;
                    } else if (arr[x + 3][y] == 'S' && arr[x + 2][y + 1] == 'A' && arr[x + 1][y + 2] == 'M' && arr[x][y + 3] == 'X') {
                        total++;
                    }
                }
            }

            return Integer.toString(total);
        }
    }

    @Override
    public String solveSecond(Path input) throws IOException {
        try (Stream<String> lines = Files.lines(input)) {
            char[][] arr = lines.map(String::toCharArray).toArray(char[][]::new);
            int total = 0;

            for (int x = 1; x < arr.length - 1; x++) {
                for (int y = 1; y < arr[x].length - 1; y++) {
                    if (arr[x][y] == 'A') {
                        if ((arr[x - 1][y - 1] == 'M' && arr[x + 1][y + 1] == 'S') || (arr[x - 1][y - 1] == 'S' && arr[x + 1][y + 1] == 'M')) {
                            if ((arr[x + 1][y - 1] == 'M' && arr[x - 1][y + 1] == 'S') || (arr[x + 1][y - 1] == 'S' && arr[x - 1][y + 1] == 'M')) {
                                total++;
                            }
                        }
                    }
                }
            }

            return Integer.toString(total);
        }
    }
}
