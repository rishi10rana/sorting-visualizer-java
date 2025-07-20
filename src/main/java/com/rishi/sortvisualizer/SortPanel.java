package com.rishi.sortvisualizer;

import java.awt.*;
import java.util.Random;

import javax.swing.JPanel;

public class SortPanel extends JPanel{
    private int[] array;
    private int highlightIndex = -1;
    private int BARWIDTH = 6;
    private boolean isPaused = false;
    private boolean isCancelled = false;

    // General Information
    private String algorithmName = "";
    private String complexity = "";
    private int comparisons = 0;
    private long timeTaken = 0;

    // Some Values
    int currentSpeed = 30;
    int currentSize = 150;


    // Setter Functions
    public void setAlgorithmInfo(String name, String complexity){
        this.algorithmName = name;
        this.complexity = complexity;
    }

    public void setTimeTaken(long timeTaken){
        this.timeTaken = timeTaken;
    }

    public void resetTimeTaken(){
        this.timeTaken = 0;
    }

    public void setCurrentSpeed(int speed){
        this.currentSpeed = speed;
    }

    public void setCurrentSize(int size){
        this.currentSize = size;
    }

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

        for(int i=0;i<array.length;i++) {
            if(i == highlightIndex) {
                g.setColor(new Color(0xD244E8)); // For Highlighted bar
            }
            else {
                g.setColor(new Color(0x39109E));
            }

            int barHeight = array[i];
//            int barWidth = Math.max(1,width - 1);
            g.fillRect(i*BARWIDTH, getHeight()-barHeight, BARWIDTH -1, barHeight);
        }

        // Set some general information
        // we use this "g" graphic object to draw things on panel
        g.setColor(Color.WHITE);
        g.setFont(new Font("consolas",Font.PLAIN,14));

        // place these information on top left
        int posTextX = 10;
        int posTextY = 20;

        g.drawString("Algorithm: " + algorithmName, posTextX, posTextY);
        g.drawString("Complexity: " + complexity, posTextX, posTextY + 20);
        g.drawString("Comparisons: " + comparisons, posTextX, posTextY + 40);
        g.drawString("Time taken: " + getFormatedTime(), posTextX, posTextY + 60);

        String speedText = "Speed(Delay): " + currentSpeed;
        String sizeText = "Array Size: " + currentSize;

        g.drawString(speedText, posTextX, posTextY + 80);
        g.drawString(sizeText, posTextX, posTextY + 100);

    }

    // synchronized keywork makes sure that only one thread can access this method at a time to prevent multile
    // threads causing race condition
    public synchronized void pauseifNeeded() throws InterruptedException {
        while(isPaused){
            wait(); // causes the current thread to wait/pause until called or awakened which is done bu notify()
        }
        if(isCancelled){
            throw new InterruptedException("Sorting Cancelled.");
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

    public void incrementComparisons(){
        this.comparisons = this.comparisons + 1;
    }

    public void resetComparisons(){
        this.comparisons = 0;
    }

    public String getFormatedTime(){
        // Time Taken original comes in long format
        if(timeTaken < 1000){
            return timeTaken + " ms";
        }
        else{
            double seconds = timeTaken / 1000.0;
            return String.format("%.2f sec" ,seconds);
        }
    }

    // functions for cancel feature
    public synchronized void cancel(){
        this.isCancelled = true;
        notify(); // will wake up thread if it was paused , to cancel it
        this.isPaused = false;
    }

    public synchronized  void resetCancel(){
        isCancelled = false;
    }

    public boolean isCancelled(){
        return isCancelled;
    }
}

