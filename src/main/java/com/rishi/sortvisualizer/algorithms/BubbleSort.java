package com.rishi.sortvisualizer.algorithms;

import com.rishi.sortvisualizer.SortPanel;
import com.rishi.sortvisualizer.SortingAlgorithm;

public class BubbleSort implements SortingAlgorithm {

    @Override
    public void sort(int[] array, SortPanel panel,int delay) throws InterruptedException {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                panel.setHighlightIndex(j);
                // check adjacent elements
                if (array[j] > array[j + 1]) {
                    // do swap
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
                panel.repaint();
                panel.pauseifNeeded();
                Thread.sleep(delay);
            }
        }
        // koii na koii j as highlighted reh gaya ho so again make no elements highlight
        panel.setHighlightIndex(-1);
        panel.repaint();
    }
}
