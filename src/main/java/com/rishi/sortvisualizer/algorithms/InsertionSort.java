package com.rishi.sortvisualizer.algorithms;

import com.rishi.sortvisualizer.SortPanel;
import com.rishi.sortvisualizer.SortingAlgorithm;

public class InsertionSort implements SortingAlgorithm {

    @Override
    public void sort(int[] array, SortPanel panel,int delay) throws InterruptedException {
        int n = array.length;
        // as 0th element is said to be sorted as it has nothing on its left to compare to.
        for(int i=1;i<n;i++){
            int temp = array[i];
            int j=i-1;
            while(j>=0){
                if(array[j]>temp){
                    // move the j index value to one position right
                    array[j+1] = array[j];
                    panel.setHighlightIndex(j); // jth element is shifted at right
                    panel.pauseifNeeded();
                    panel.repaint();
                    Thread.sleep(delay);
                    j--;
                }
                else{
                    break;
                }
            }
            array[j+1] = temp;
            panel.setHighlightIndex(j+1); // highlight the index where the temp is finally placed
            panel.repaint();
            panel.pauseifNeeded();
            Thread.sleep(delay);
        }

        panel.setHighlightIndex(-1);
        panel.repaint();
    }
}
