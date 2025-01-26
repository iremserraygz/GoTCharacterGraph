# COMP2102 Data Structures and Algorithms - Project #3: Game of Thrones Network

## About This Project

This project, developed for the COMP2102 Data Structures and Algorithms course, aims to model the relationships between characters in the "Game of Thrones" TV series and perform network analysis. The project is based on a weighted graph that represents the strength of connections between characters. It utilizes various algorithms to analyze character paths, connections, and network structures.

## Dataset

The dataset used in this project is the `got-edges.csv` file, which can be found at the following link:



## Project Objectives

The main objectives of this project are:

*   **Hash Table Creation:** Using hash tables to map character names (strings) to graph vertices (integers).
*   **Weighted Graph Model:** Modeling character relationships using a weighted graph data structure.
*   **Network Analysis Algorithms:** Implementing and testing the following algorithms:
    *   Checking for the existence of a path.
    *   Finding all paths shorter than or equal to a given length.
    *   Finding the shortest path.
    *   Counting the number of paths between two nodes.
    *   Finding paths using Breadth-First Search (BFS).
    *   Finding paths using Depth-First Search (DFS).
    *   Finding the number of nodes in a component.

## Data Structures and Algorithms Used

*   **Hash Table:** Used to map character names to indices. Proper collision handling is implemented.
*   **Weighted Graph:** Characters and their relationships are represented with weighted edges.
*   **DFS (Depth-First Search):** Used to search for paths between two nodes.
*   **BFS (Breadth-First Search):** Used to find paths between two nodes and to find the shortest path.
*   **Other Algorithms:** Other required pathfinding algorithms and network analysis methods are implemented.

## Methods

The following methods are implemented in this project:

*   **`ReadGraphFromFile()`:** Reads data from the file and constructs the graph and hash table.
*   **`IsThereAPath(String name1, String name2)`:** Checks if there is a path between two characters.
*   **`AllPathsShorterThanEqualTo(int pathLen, int VertexNo, string name1)`:** Prints all paths starting from the specified character, with a length less than or equal to `pathLen` and with at least `VertexNo` vertices.
*   **`ShortestPathLengthFromTo(String name1, String name2)`:** Returns the length of the shortest path between two characters. Prints "infinity" if no path exists.
*   **`NoOfPathsFromTo(String name1, String name2)`:** Returns the number of different paths between two characters.
*   **`BFSfromTo(String name1, String name2)`:** Prints the sequence of vertices while starting a BFS from `name1` until reaching `name2`. (Edges are visited from the minimum weight to the maximum weight).
*   **`DFSfromTo(String name1, String name2)`:** Prints the sequence of vertices while starting a DFS from `name1` until reaching `name2`.
*   **`NoOfVerticesInComponent(String name1)`:** Prints the number of vertices that exist in the component containing `name1`.

