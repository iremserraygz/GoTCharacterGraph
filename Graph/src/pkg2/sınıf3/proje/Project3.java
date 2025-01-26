package pkg2.sınıf3.proje;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Project3 {

    static GraphMatrix seriesGraph;
    static HashTable hashTable;
    static boolean[][] visited;
    static boolean[] visitedo;

    static Scanner scanner;

    public static void main(String[] args) throws IOException, NullPointerException {
         menu();

    }

    public static void menu() throws IOException, NullPointerException {
        scanner = new Scanner(System.in);
        while (true) {
            // display menu
            System.out.println("1. ReadGraphFromFile"
                    + "\n" + "2. IsThereAPath"
                    + "\n" + "3. ShortestPathLengthFromTo"
                    + "\n" + "4. BFSfromTo "
                    + "\n" + "5. DFSfromTo "
                    + "\n" + "6. Exit"
                    + "\n" + " Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // con1sume newline
            switch (choice) {
                case 1:
                    ReadGraphFromFile();
                    System.out.println("The graph is read by system successfully");
                    break;

                case 2:
                    System.out.print("Enter name1: ");
                    String name1 = scanner.nextLine();
                    System.out.print("Enter name2: ");
                    String name2 = scanner.nextLine();
                    isThereAPath(name1, name2);
                    break;

                case 3:
                    System.out.print("Enter name1: ");
                    name1 = scanner.nextLine();
                    System.out.print("Enter name2: ");
                    name2 = scanner.nextLine();
                    ShortestPathLengthFromTo(name1, name2, hashTable);
                    break;
                case 4:
                    System.out.print("Enter name1: ");
                    name1 = scanner.nextLine();
                    System.out.print("Enter name2: ");
                    name2 = scanner.nextLine();
                    seriesGraph.BFSfromTo(name1, name2, hashTable);
                    break;
                case 5:
                    System.out.print("Enter name1: ");
                    name1 = scanner.nextLine();
                    System.out.print("Enter name2: ");
                    name2 = scanner.nextLine();
                    seriesGraph.DFSfromTo(name1, name2, hashTable);
                    for (int i = 0; i < seriesGraph.path.size(); i++) {
                        System.out.print(seriesGraph.path.get(i));
                    }
                    System.out.println("");
                    break;
                case 6:
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    public static void ReadGraphFromFile() throws FileNotFoundException, IOException {

        seriesGraph = new GraphMatrix(107);
        hashTable = new HashTable(107);

        File seriesCharacter = new File("seriesCharacters.txt");
        FileReader readFile = new FileReader(seriesCharacter);
        BufferedReader readCharacter = new BufferedReader(readFile);

        String currentLine;

        while ((currentLine = readCharacter.readLine()) != null) {

            String[] token = currentLine.split(",");
            hashTable.insert(token[0]);
            hashTable.insert(token[1]);
            seriesGraph.addEdge(hashTable.hash(token[0]), hashTable.hash(token[1]), Integer.parseInt(token[2]));
            currentLine = readCharacter.readLine();

        }

    }

    public static void isThereAPath(String name1, String name2) {

        seriesGraph.DFSfromTo(name1, name2, hashTable);
        boolean pathExist = seriesGraph.path.contains(name2);

        if (pathExist) {

            System.out.println("There is a path");
            for (int i = 0; i < seriesGraph.path.size(); i++) {
                System.out.print(seriesGraph.path.get(i));
            }
            System.out.println("");;
        } else {
            System.out.println("No Path Found");
        }

    }

    public static void ShortestPathLengthFromTo(String name1, String name2, HashTable hashTable) {

        int src = hashTable.hash(name1);
        int dest = hashTable.hash(name2);
        int[] dist = seriesGraph.dijkstra(seriesGraph.edges, src);
        if (dist[dest] == Integer.MAX_VALUE) {
            System.out.println("infinity");
        } else {
            System.out.println("The length of shortest path from " + name1 + " to " + name2 + " is " + dist[dest]);

        }
    }
}
