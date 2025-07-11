package com.rishi.sortvisualizer;

// Create an Interface and individual sorting classes will implement this interface and override its method
public interface SortingAlgorithm {
    void sort(int[] array,SortPanel panel,int delay) throws InterruptedException;
}
