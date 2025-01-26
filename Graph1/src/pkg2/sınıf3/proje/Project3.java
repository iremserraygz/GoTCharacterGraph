package pkg2.sınıf3.proje;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Project3 {

    static GraphMatrix seriesGraph;
    static HashTable hashTable;
    static Scanner scanner;

    public static void main(String[] args) throws IOException, NullPointerException {
        menu();
    }

    public static void menu() throws IOException, NullPointerException {
        scanner = new Scanner(System.in);
        while (true) {
            // display menu
            System.out.println("\n1. ReadGraphFromFile"
                    + "\n2. IsThereAPath"
                    + "\n3. ShortestPathLengthFromTo"
                    + "\n4. AllPathsShorterThanEqualTo"
                    + "\n5. NoOfPathsFromTo"
                    + "\n6. BFSfromTo"
                    + "\n7. DFSfromTo"
                    + "\n8. NoOfVerticesInComponent"
                    + "\n9. Exit"
                    + "\nEnter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            switch (choice) {
                case 1:
                    ReadGraphFromFile();
                    System.out.println("The graph is read by system successfully");
                    break;

                case 2:
                     if(seriesGraph == null || hashTable == null){
                       System.out.println("Please read the graph from the file first. Select option 1");
                       break;
                    }
                    System.out.print("Enter name1: ");
                    String name1 = scanner.nextLine();
                    System.out.print("Enter name2: ");
                    String name2 = scanner.nextLine();
                    boolean hasPath = isThereAPath(name1, name2);
                    System.out.println("Is there a path: " + hasPath);
                    break;

                case 3:
                     if(seriesGraph == null || hashTable == null){
                       System.out.println("Please read the graph from the file first. Select option 1");
                       break;
                    }
                    System.out.print("Enter name1: ");
                    name1 = scanner.nextLine();
                    System.out.print("Enter name2: ");
                    name2 = scanner.nextLine();
                    ShortestPathLengthFromTo(name1, name2, hashTable);
                    break;
                case 4:
                    if(seriesGraph == null || hashTable == null){
                       System.out.println("Please read the graph from the file first. Select option 1");
                       break;
                    }
                    System.out.print("Enter path length: ");
                    int pathLen = scanner.nextInt();
                    System.out.print("Enter min vertices: ");
                    int vertexNo = scanner.nextInt();
                    scanner.nextLine();// consume line
                    System.out.print("Enter name: ");
                    name1 = scanner.nextLine();
                    AllPathsShorterThanEqualTo(pathLen, vertexNo, name1);
                    break;
                 case 5:
                     if(seriesGraph == null || hashTable == null){
                       System.out.println("Please read the graph from the file first. Select option 1");
                       break;
                    }
                     System.out.print("Enter name1: ");
                     name1 = scanner.nextLine();
                     System.out.print("Enter name2: ");
                     name2 = scanner.nextLine();
                    NoOfPathsFromTo(name1, name2);
                     break;
                case 6:
                    if(seriesGraph == null || hashTable == null){
                       System.out.println("Please read the graph from the file first. Select option 1");
                       break;
                    }
                    System.out.print("Enter name1: ");
                    name1 = scanner.nextLine();
                    System.out.print("Enter name2: ");
                    name2 = scanner.nextLine();
                    seriesGraph.BFSfromTo(name1, name2, hashTable);
                    break;
                case 7:
                    if(seriesGraph == null || hashTable == null){
                       System.out.println("Please read the graph from the file first. Select option 1");
                       break;
                    }
                    System.out.print("Enter name1: ");
                    name1 = scanner.nextLine();
                    System.out.print("Enter name2: ");
                    name2 = scanner.nextLine();
                    seriesGraph.DFSfromTo(name1, name2, hashTable);
                    break;
                case 8:
                      if(seriesGraph == null || hashTable == null){
                       System.out.println("Please read the graph from the file first. Select option 1");
                       break;
                    }
                     System.out.print("Enter name: ");
                     name1 = scanner.nextLine();
                    NoOfVerticesInComponent(name1);
                    break;

                case 9:
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    public static void ReadGraphFromFile() throws IOException {
        seriesGraph = new GraphMatrix(107);
        hashTable = new HashTable(107);

        File seriesCharacter = new File("seriesCharacters.txt");
        FileReader readFile = new FileReader(seriesCharacter);
        BufferedReader readCharacter = new BufferedReader(readFile);

        String currentLine;
        while ((currentLine = readCharacter.readLine()) != null) {
            String[] token = currentLine.split(",");
            if (token.length == 3) {
                hashTable.insert(token[0]);
                hashTable.insert(token[1]);
                seriesGraph.addEdge(hashTable.hash(token[0]), hashTable.hash(token[1]), Integer.parseInt(token[2]));

            } else {
                System.out.println("Invalid input format: " + currentLine);
            }
        }
        readCharacter.close();
    }

      public static boolean isThereAPath(String name1, String name2) {
        if(seriesGraph == null || hashTable == null) return false;
        seriesGraph.DFSfromTo(name1, name2, hashTable);
        boolean pathExist = !seriesGraph.path.isEmpty();
        seriesGraph.resetSearch();
        return pathExist;
    }


    public static void ShortestPathLengthFromTo(String name1, String name2, HashTable hashTable) {
        if(seriesGraph == null || hashTable == null){
            System.out.println("Please read the graph from the file first. Select option 1");
            return;
        }
        int src = hashTable.hash(name1);
        int dest = hashTable.hash(name2);
        if (hashTable.get(name1) == null || hashTable.get(name2) == null) {
            System.out.println("Invalid Input");
            return;
        }
        int[] dist = seriesGraph.dijkstra(src);
        if (dist[dest] == Integer.MAX_VALUE) {
            System.out.println("infinity");
        } else {
            System.out.println("The length of shortest path from " + name1 + " to " + name2 + " is " + dist[dest]);

        }
    }

    public static void AllPathsShorterThanEqualTo(int pathLen, int VertexNo, String name1) {
       if(seriesGraph == null || hashTable == null){
           System.out.println("Please read the graph from the file first. Select option 1");
           return;
       }
        seriesGraph.AllPathsShorterThanEqualTo(pathLen, VertexNo, name1, hashTable);
    }

    public static void NoOfPathsFromTo(String name1, String name2) {
          if(seriesGraph == null || hashTable == null){
              System.out.println("Please read the graph from the file first. Select option 1");
              return;
          }
        int paths = seriesGraph.NoOfPathsFromTo(name1, name2, hashTable);
        if (paths != -1) {
            System.out.println("Number of Paths from " + name1 + " to " + name2 + " : " + paths);

        }
    }

    public static void NoOfVerticesInComponent(String name1) {
         if(seriesGraph == null || hashTable == null){
             System.out.println("Please read the graph from the file first. Select option 1");
            return;
         }
        int vertices = seriesGraph.NoOfVerticesInComponent(name1, hashTable);
        if (vertices != -1) {
            System.out.println("Number of vertices in the component of " + name1 + " : " + vertices);
        }
    }
}