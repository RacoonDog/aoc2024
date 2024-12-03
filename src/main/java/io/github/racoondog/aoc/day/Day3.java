package io.github.racoondog.aoc.day;

import io.github.racoondog.aoc.Day;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 extends Day {
    public static void main(String[] args) {
        new Day3().run();
    }

    public Day3() {
        super(3);
    }

    private static final Pattern MUL = Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)");

    @Override
    public String solveFirst(Path input) throws IOException {
        String program = Files.readString(input);
        int sum = 0;

        Matcher matcher = MUL.matcher(program);
        while (matcher.find()) {
            sum += Integer.parseInt(matcher.group(1)) * Integer.parseInt(matcher.group(2));
        }

        return Integer.toString(sum);
    }

    private static final Pattern INS = Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)|don't\\(\\)|do\\(\\)");

    @Override
    public String solveSecond(Path input) throws IOException {
        String program = Files.readString(input);
        int sum = 0;
        boolean active = true;

        Matcher matcher = INS.matcher(program);
        while (matcher.find()) {
            String group = matcher.group();
            if (group.startsWith("don't()")) {
                active = false;
            } else if (group.startsWith("do()")) {
                active = true;
            } else if (active) {
                sum += Integer.parseInt(matcher.group(1)) * Integer.parseInt(matcher.group(2));
            }
        }

        return Integer.toString(sum);
    }
}
