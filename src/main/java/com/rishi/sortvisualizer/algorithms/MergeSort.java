package com.rishi.sortvisualizer.algorithms;

import com.rishi.sortvisualizer.SortPanel;
import com.rishi.sortvisualizer.SortingAlgorithm;

public class MergeSort implements SortingAlgorithm {

    // This function merges the - smaller sorted parts that we got - into single array
    private void merge(int[] array, int start, int mid, int end, SortPanel panel,int delay) throws InterruptedException {
        // left part length
        int len1 = mid - start + 1;
        int len2 = end - mid;

        int[] left = new int[len1];
        int[] right = new int[len2];

        int mainIndex = start;
        for(int i=0;i<len1;i++){
            left[i] = array[mainIndex];
            mainIndex++;
        }
        for(int i=0;i<len2;i++){
            right[i] = array[mainIndex];
            mainIndex++;
        }

        mainIndex = start;
        int i = 0;
        int j = 0;
        while(i<len1 && j<len2){
            panel.setHighlightIndex(mainIndex);
            if(left[i]<=right[j]){
                array[mainIndex] = left[i];
                mainIndex++;
                i++;
            }
            else{
                array[mainIndex] = right[j];
                mainIndex++;
                j++;
            }
            panel.repaint();
            panel.pauseifNeeded();
            Thread.sleep(delay);
        }

        while(i<len1){
            panel.setHighlightIndex(mainIndex);
            array[mainIndex] = left[i];
            mainIndex++;
            i++;
            panel.repaint();
            panel.pauseifNeeded();
            Thread.sleep(delay);
        }

        while(j<len2){
            panel.setHighlightIndex(mainIndex);
            array[mainIndex] = right[j];
            mainIndex++;
            j++;
            panel.repaint();
            panel.pauseifNeeded();
            Thread.sleep(delay);
        }
    }

    // Divide the Array in smaller parts
    private void mergeSort(int[] array,int start,int end, SortPanel panel, int delay) throws InterruptedException {
        // base case
        if(start>=end){
            return;
        }
        int mid = start + (end-start)/2;

        // Recursively break in two parts - left and right
        mergeSort(array,start,mid,panel,delay);
        mergeSort(array,mid+1,end,panel,delay);
        // merge them
        merge(array,start,mid,end,panel,delay);
    }

    @Override
    public void sort(int[] array, SortPanel panel,int delay) throws InterruptedException {
        int start = 0;
        int end = array.length - 1;
        mergeSort(array,start,end,panel,delay);

        panel.setHighlightIndex(-1);
        panel.repaint();
    }
}
