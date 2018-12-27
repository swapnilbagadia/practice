package main.java;

import java.util.LinkedList;
import java.util.*;

public class MergeKSortedArrays {

    public static void main(String[] args) {

        List<ArrayList<Integer>> myList = new ArrayList<>();

        ArrayList<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(20);
        list1.add(30);

        ArrayList<Integer> list2 = new ArrayList<>();
        list2.add(4);
        list2.add(7);
        list2.add(9);

        ArrayList<Integer> list3 = new ArrayList<>();
        list3.add(2);
        list3.add(6);
        list3.add(8);

        myList.add(list1);
        myList.add(list2);
        myList.add(list3);

        List<Integer> result = mergeKSortedArrays(myList);

        System.out.println(result);

    }

    private static List<Integer> mergeKSortedArrays(List<ArrayList<Integer>> myList) {

        PriorityQueue<MinHeapKey> minHeap = new PriorityQueue<>(new Comparator<MinHeapKey>() {
            @Override
            public int compare(MinHeapKey o1, MinHeapKey o2) {
                return o1.data < o2.data ? -1 : 1;
            }
        });

        List<Integer> result = new LinkedList<>();

        for (ArrayList<Integer> integerArrayList : myList) {
            //0th element inserted to MinHeap
            minHeap.add(new MinHeapKey(integerArrayList.get(0), myList.indexOf(integerArrayList), 0));
        }

        while (minHeap.peek().data != Integer.MAX_VALUE) {

            MinHeapKey key = minHeap.poll();
            int arrayNumber = key.arrayNumber;
            int elementIndex = key.elementIndex;

            ArrayList<Integer> array = myList.get(arrayNumber);

            if (elementIndex == array.size() - 1) {
                minHeap.add(new MinHeapKey(Integer.MAX_VALUE, arrayNumber, array.size()));
            } else {
                minHeap.add(new MinHeapKey(array.get(elementIndex + 1), arrayNumber, elementIndex + 1));
            }
            result.add(key.data);


        }


        return result;

    }


    static class MinHeapKey {
        int data;
        int arrayNumber;
        int elementIndex;

        MinHeapKey(int data, int arrayNumber, int elementIndex) {
            this.data = data;
            this.arrayNumber = arrayNumber;
            this.elementIndex = elementIndex;
        }


    }
}
