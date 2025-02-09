# 🏰 COMP2102 Data Structures and Algorithms - Game of Thrones Network

## 📌 About This Project

This project, developed for the **COMP2102 Data Structures and Algorithms** course, models the relationships between characters in the *Game of Thrones* TV series and performs **network analysis**. 🔍 The project utilizes a **weighted graph** to represent the strength of character connections and applies various algorithms to analyze paths, connections, and network structures.

## 📂 Dataset

📁 The dataset used in this project is the **`got-edges.csv`** file, which can be accessed at the following link: 🔗

## 🎯 Project Objectives

The key objectives of this project include:

✅ **Hash Table Creation:** Using hash tables to map character names (strings) to graph vertices (integers).  
✅ **Weighted Graph Model:** Representing character relationships with a weighted graph structure.  
✅ **Network Analysis Algorithms:** Implementing and testing the following algorithms:
   - 🔗 Checking for the **existence of a path**
   - 📏 Finding **all paths** shorter than or equal to a given length
   - 🏆 Finding the **shortest path**
   - 🔢 Counting the **number of paths** between two nodes
   - 🌐 Finding paths using **Breadth-First Search (BFS)**
   - 🧭 Finding paths using **Depth-First Search (DFS)**
   - 📊 Determining the **number of nodes in a component**

## 🛠️ Data Structures and Algorithms Used

🗂 **Hash Table:** Maps character names to indices with proper collision handling.  
🔗 **Weighted Graph:** Represents characters and their relationships using weighted edges.  
🕵️ **DFS (Depth-First Search):** Searches for paths between nodes.  
🚀 **BFS (Breadth-First Search):** Finds paths and computes the shortest path.  
📈 **Other Algorithms:** Implements additional network analysis techniques.

## 🔍 Methods Implemented

📝 The following methods are included in this project:

📌 **`ReadGraphFromFile()`** - Reads data from the file and constructs the graph and hash table.  
📌 **`IsThereAPath(String name1, String name2)`** - Checks if a path exists between two characters.  
📌 **`AllPathsShorterThanEqualTo(int pathLen, int VertexNo, String name1)`** - Prints all paths from a character with length ≤ `pathLen` and at least `VertexNo` vertices.  
📌 **`ShortestPathLengthFromTo(String name1, String name2)`** - Returns the shortest path length between two characters. Prints *infinity* if no path exists.  
📌 **`NoOfPathsFromTo(String name1, String name2)`** - Returns the number of different paths between two characters.  
📌 **`BFSfromTo(String name1, String name2)`** - Performs **BFS traversal** from `name1` to `name2`, visiting edges in ascending weight order.  
📌 **`DFSfromTo(String name1, String name2)`** - Performs **DFS traversal** from `name1` to `name2`.  
📌 **`NoOfVerticesInComponent(String name1)`** - Prints the number of vertices in the component containing `name1`.  

---


