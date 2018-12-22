package main.java;

import java.util.Comparator;
import java.util.PriorityQueue;

public class StreamOfMedianIntegers {

    public static void main(String[] args) {

        int[] array = {1, 2, 3, 4};

        double[] medians = getMedians(array);

        for (int i = 0; i < array.length; i++) {

            System.out.println(medians[i]);
        }


    }


    private static double[] getMedians(int[] array) {

        int arrLength = array.length;
        double[] medians = new double[arrLength];

        //MaxHeap of smaller elements
        PriorityQueue<Integer> lowers = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return -1 * o1.compareTo(o2);
            }
        });

        //MinHeap of larger elements
        PriorityQueue<Integer> highers = new PriorityQueue<>();


        for (int i = 0; i < arrLength; i++) {

            addToHeap(array[i], lowers, highers);
            reBalance(lowers, highers);
            medians[i] = getMedian(lowers, highers);

        }


        return medians;
    }

    private static void addToHeap(int n, PriorityQueue<Integer> lowers, PriorityQueue<Integer> highers) {

        if (lowers.size() == 0 || n < lowers.peek()) {
            lowers.add(n);
        } else {
            highers.add(n);
        }


    }

    private static void reBalance(PriorityQueue<Integer> lowers, PriorityQueue<Integer> highers) {

        if (lowers.size() == highers.size()) {
            return;
        } else if (Math.abs(lowers.size() - highers.size()) >= 2) {
            PriorityQueue<Integer> biggerHeap = lowers.size() > highers.size() ? lowers : highers;
            PriorityQueue<Integer> smallerHeap = lowers.size() < highers.size() ? lowers : highers;
            smallerHeap.add(biggerHeap.poll());
        }

    }


    private static double getMedian(PriorityQueue<Integer> lowers, PriorityQueue<Integer> highers) {

        if (lowers.size() == highers.size()) {
            return ((double) lowers.peek() + highers.peek()) / 2;
        } else {
            PriorityQueue<Integer> biggerHeap = lowers.size() > highers.size() ? lowers : highers;
            PriorityQueue<Integer> smallerHeap = lowers.size() < highers.size() ? lowers : highers;
            return biggerHeap.peek();
        }

    }

}
