package com.rishi.sortvisualizer.algorithms;

import com.rishi.sortvisualizer.SortPanel;
import com.rishi.sortvisualizer.SortingAlgorithm;

public class QuickSort implements SortingAlgorithm {

    // returns the pivot index and also satisfies the condition for pivot index
    int partition(int[] array,int start,int end,SortPanel panel,int delay) throws InterruptedException {
        // first choose pivot element
        int pivot = array[start];

        // count the no of elements smaller than pivot element
        int count = 0;
        for(int i=start+1;i<=end;i++){
            panel.incrementComparisons();
            if(array[i] <= pivot){
                count++;
            }
        }

        // find the index to place the pivot element
        int pivotIndex = start + count;
        panel.setHighlightIndex(pivotIndex);

        // swap the value to place th pivot at that index
        int temp = array[start];
        array[start] = array[pivotIndex];
        array[pivotIndex] = temp;

        panel.repaint();
        Thread.sleep(2);

        // now fulfill the condition of pivot element that all element to its left ,must be smaller than it
        // and all elements right to it, must be larger than it
        // 2 pointer approach
        int i = start;
        int j = end;
        while(i<pivotIndex && j>pivotIndex){
            while(array[i] <= array[pivotIndex]){
                panel.incrementComparisons();
                panel.setHighlightIndex(i);
                panel.repaint();
                panel.pauseifNeeded();
                Thread.sleep(delay);
                i++; // sab sahi hai
            }

            while(array[j] > array[pivotIndex]){
                panel.incrementComparisons();
                panel.setHighlightIndex(j);
                panel.repaint();
                panel.pauseifNeeded();
                Thread.sleep(delay);
                j--; // sab sahi hai
            }

            if(i < pivotIndex && j > pivotIndex){ // check whether we are in within the range
                // swap them they are not present in correct places
                panel.setHighlightIndex(i);

                int temp2 = array[i];
                array[i] = array[j];
                panel.repaint();
                panel.pauseifNeeded();
                Thread.sleep(delay);

                panel.setHighlightIndex(j);
                array[j] =  temp2;
                panel.repaint();
                panel.pauseifNeeded();
                Thread.sleep(delay);
                i++;
                j--;
            }
        }
        return pivotIndex;
    }

    void QuickSort(int[] array,int start, int end, SortPanel panel,int delay) throws InterruptedException {
        // base case
        if(start >= end){
            return;
        }

        // it will give us the pivot index using which we will partition the array in left and right part
        int p = partition(array,start,end,panel,delay);

        // to consider pivot in any part as it is already placed in its correct position
        QuickSort(array,start,p-1,panel,delay);
        QuickSort(array,p+1,end,panel,delay);
    }


    @Override
    public void sort(int[] array, SortPanel panel, int delay) throws InterruptedException {
        panel.resetTimeTaken();
        panel.resetComparisons();
        long startTime = System.currentTimeMillis();

        int start = 0;
        int end = array.length-1;
        QuickSort(array,start,end,panel,delay);

        // remove any remaining higlighted index
        panel.setHighlightIndex(-1);
        panel.repaint();

        long endTime = System.currentTimeMillis();
        panel.setTimeTaken(endTime-startTime);
    }
}
