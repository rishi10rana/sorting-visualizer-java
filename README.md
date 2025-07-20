# Sorting Visualizer

**Sorting Visualizer** is a Java-based desktop application that helps visualize how common sorting algorithms work in real-time.

It provides an interactive way to visualize sorting by watching the process animated through colored bars.

## Built With

- **Java** (Core Language)
- **Java Swing** (For GUI components)
- **FlatLaf** (Modern Look and Feel library for better UI)
- **Maven** (Build and dependency management)

## Sorting Algorithms Included

- **Bubble Sort**
- **Selection Sort**
- **Insertion Sort**
- **Merge Sort**
- **Quick Sort**

## Visualization

- Each element in the array is represented by a **vertical bar**.
- The **Java Graphics class** is used to draw and animate these bars.
- The height of each bar corresponds to the value of the array element.

## Features
- **Start Button** : Begin sorting the current array   
- **Pause/Resume** : Pause the current sorting and resume it anytime   
- **Reset Button** : Cancel the current sorting and reset the array
- **Shuffle Button** : Randomize the array with new random elements
- **Size Slider** : Change the size of the array (number of bars)
- **Speed Slider** :  Adjust the speed (delay) of the visualization

## Application Structure

- **Welome Screen:** Displays the Start Page , some information and a Start Button to open the Visualizer.
- **Control Panel:** Contains dropdown to select algorithm, speed & size sliders, and control buttons.
- **Sorting Panel:** Displays the bars and visualizes sorting using animation.

## Concepts Used

This project demonstrates the following Java concepts:

- **Object-Oriented Programming (OOP)**
- **Java Swing (GUI Development)**
- **Custom Painting with Graphics Class**
- **Multithreading** (for smooth animations and UI responsiveness)
- **Maven Build System** (for managing dependencies and builds)

## Development Environment

- **IDE:** IntelliJ IDEA  
- **Build Tool:** Maven  
- **External Dependency:**  
   - [FlatLaf](https://www.formdev.com/flatlaf/) – for a modern look and feel in Swing UI
   - [Apache Maven Shade Plugin](https://mvnrepository.com/artifact/org.apache.maven.plugins/maven-shade-plugin) - Repackages the project classes together with their dependencies into a single uber-jar or fat-jar
## How to Run

```bash
# Clone the Repository
git clone https://github.com/rishi10rana/sorting-visualizer-java.git

# Go inside the project folder
cd sorting-visualizer-java

# Build the Project
mvn clean install 

# Run the Application
mvn exec:java -Dexec.mainClass="com.rishi.sortvisualizer.Main"
```

## Download JAR
Download the latest release : 
[Sorting_Visualizer-1.0.jar](https://github.com/rishi10rana/sorting-visualizer-java/releases/download/v1.0/Sorting_Visualizer-1.0.jar)

You can also run the pre-built jar directly:

```bash
java -jar Sorting_Visualizer-1.0.jar
```

## License
This project is open-source and free to use for educational purposes.

## Author
Rishi Rana