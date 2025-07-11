package com.rishi.sortvisualizer.algorithms;

import com.rishi.sortvisualizer.SortPanel;
import com.rishi.sortvisualizer.SortingAlgorithm;

public class SelectionSort implements SortingAlgorithm {

    @Override
    public void sort(int[] array, SortPanel panel,int delay) throws InterruptedException {
        int n = array.length;
        for(int i=0;i<n-1;i++){
            int minIndex = i;
            panel.setHighlightIndex(i);
            for(int j=i+1;j<n;j++){
                if(array[j]<array[minIndex]){
                    minIndex = j;
                }
            }
            // swap the array[i] and the new mid element found
            int temp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = temp;

            panel.repaint();
            panel.pauseifNeeded();
            Thread.sleep(delay);
        }
        panel.setHighlightIndex(-1);
        panel.repaint();
    }
}
