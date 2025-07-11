package com.rishi.sortvisualizer;

import java.awt.*;
import java.util.Random;

import javax.swing.JPanel;

public class SortPanel extends JPanel{
    private int[] array;
    private int highlightIndex = -1;
    private int BARWIDTH = 6;
    private boolean isPaused = false;

    @Override
    public Dimension getPreferredSize() {
        int width = array.length * BARWIDTH;
        return new Dimension(width,500);
    }

    public SortPanel() {
        setBackground(Color.BLACK);
        generateRandomArray(150); // Here you can adjust the no. of elements
    }

    public void generateRandomArray(int size) {
        array = new int[size];
        Random rand = new Random();
        for(int i=0;i<size;i++) {
            // nextInt(int bound) it gives any random no between 0 -> 399 but don't want 0 so we add 50 to keep the minimum value possible 50
            array[i] = rand.nextInt(400) + 50; // gives values between 50 and 450 now
        }

        revalidate();
        repaint();
    }

    // Everytime we repaint the Jframe then this function is called automatically
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // To find what should be the width of each bar as it may change according to total no of elements
        // to properly fit them in panel
//        int width = Math.max(2,getWidth() / array.length);

        for(int i=0;i<array.length;i++) {
            if(i == highlightIndex) {
                g.setColor(new Color(0xFF1414)); // For Highlighted bar
            }
            else {
                g.setColor(new Color(0x5C972E));
            }

            int barHeight = array[i];
//            int barWidth = Math.max(1,width - 1);
            g.fillRect(i*BARWIDTH, getHeight()-barHeight, BARWIDTH -1, barHeight);
        }

    }

    // synchronized keywork makes sure that only one thread can access this method at a time to prevent multile
    // threads causing race condition
    public synchronized void pauseifNeeded() throws InterruptedException {
        if(isPaused){
            wait(); // causes the current thread to wait/pause until called or awakened which is done bu notify()
        }
    }

    public synchronized void togglePause(){
        isPaused = !isPaused;
        if(!isPaused){
            // if not pause then awaken the thread
            notify();
        }
    }

    public boolean isPaused(){
        return isPaused;
    }

    // it provides the array which is being sorted
    public int[] getArray(){
        return array;
    }

    // helps to set the index which needs to be highlighted
    public void setHighlightIndex(int index){
        this.highlightIndex = index;
    }
}

