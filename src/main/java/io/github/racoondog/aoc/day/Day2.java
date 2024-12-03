package io.github.racoondog.aoc.day;

import io.github.racoondog.aoc.Day;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class Day2 extends Day {
    public static void main(String[] args) {
        new Day2().run();
    }

    public Day2() {
        super(2);
    }

    private int[] parse(String[] strings) {
        int[] nums = new int[strings.length];
        for (int i = 0; i < strings.length; i++) {
            nums[i] = Integer.parseInt(strings[i]);
        }
        return nums;
    }

    @Override
    public String solveFirst(Path input) throws IOException {
        try (Stream<String> lines = Files.lines(input)) {
            return Long.toString(lines.map(str -> str.split(" "))
                .map(this::parse)
                .filter(this::isSafe)
                .count());
        }
    }

    private boolean isSafe(int[] nums) {
        int first = nums[0];
        int second = nums[1];

        if (first == second || Math.abs(first - second) > 3) return false;

        boolean increasing = second > first;
        int previous = second;
        for (int i = 2; i < nums.length; i++) {
            int current = nums[i];
            if (current == previous || Math.abs(current - previous) > 3) return false;
            if ((increasing && current < previous) || (!increasing && current > previous)) return false;
            previous = current;
        }

        return true;
    }

    @Override
    public String solveSecond(Path input) throws IOException {
        try (Stream<String> lines = Files.lines(input)) {
            return Long.toString(lines.map(str -> str.split(" "))
                .map(this::parse)
                .filter(this::dampenedIsSafe)
                .count());
        }
    }

    private int[] remove(int[] nums, int index) {
        IntList list = new IntArrayList(nums);
        list.removeInt(index);
        return list.toIntArray();
    }

    private boolean dampenedIsSafe(int[] nums) {
        int unsafeIndex = getUnsafeIndex(nums);
        if (unsafeIndex == -1) return true; //safe

        if (isSafe(remove(nums, 0))) return true;
        if (isSafe(remove(nums, 1))) return true;

        // -2 means unsafe index is either 0 or 1
        if (unsafeIndex != -2) {
            if (isSafe(remove(nums, unsafeIndex))) return true;
            if (isSafe(remove(nums, unsafeIndex - 1))) return true;
        }

        return false;
    }

    private int getUnsafeIndex(int[] nums) {
        int first = nums[0];
        int second = nums[1];

        if (first == second || Math.abs(first - second) > 3) return -2;

        boolean increasing = second > first;
        int previous = second;
        for (int i = 2; i < nums.length; i++) {
            int current = nums[i];
            if (current == previous || Math.abs(current - previous) > 3) return i;
            if ((increasing && current < previous) || (!increasing && current > previous)) return i;
            previous = current;
        }

        return -1;
    }
}
