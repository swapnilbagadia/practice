package main.java.heaps;

import java.util.PriorityQueue;

public class KthLargestInStreamOfIntegers
{


    public static void main(String[] args) {

        int[] array = {1, 2, 3, 4, 5, 6, 7 ,8, 9 ,10};

        int k =5;

        int[] kthLargest = getKthLargest(array,k);

        for (int i = 0; i < array.length; i++) {

            System.out.println(kthLargest[i]);
        }

    }


    static int[] getKthLargest(int[] array, int k){

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        int[] kthLargest = new int[array.length];

        for(int i =0; i< array.length; i++){

            if (minHeap.size() < k){
                minHeap.add(array[i]);
                kthLargest[i] = -1;
            }else if(minHeap.peek() < array[i]){
                minHeap.poll();
                minHeap.add(array[i]);
                kthLargest[i] = minHeap.peek();
            }
        }


        return kthLargest;
    }
}
