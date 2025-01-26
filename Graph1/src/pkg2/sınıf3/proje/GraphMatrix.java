package pkg2.sınıf3.proje;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class GraphMatrix {

    int[][] edges;
    int numV;
    boolean[] marked;
    int[] edgeTo;
    int[] distTo;
    ArrayList<String> path = new ArrayList<>();
    List<String> allPaths = new ArrayList<>();


    public GraphMatrix(int V) {
        this.numV = V;
        edges = new int[V][V];
        marked = new boolean[V];
        edgeTo = new int[V];
        distTo = new int[V];
    }

    public void addEdge(int from, int to, int weight) {
        edges[from][to] = weight;
        edges[to][from] = weight;
    }

    public boolean isAdjacent(int v1, int v2) {
        return (edges[v1][v2] != 0);
    }

    public int degree(int v) {
        int degree = 0;
        for (int i = 0; i < numV; i++) {
            degree += edges[v][i];
        }
        return degree;
    }

    public List<Integer> neighbors(int hashCodeOfSource) {
        List<Integer> neighbors = new ArrayList<>();
        for (int i = 0; i < numV; i++) {
            if (edges[hashCodeOfSource][i] > 0) {
                neighbors.add(i);
            }
        }
        return neighbors;
    }

    public void resetSearch() {
        marked = new boolean[numV];
        edgeTo = new int[numV];
        distTo = new int[numV];
        path = new ArrayList<>();
        allPaths = new ArrayList<>();
    }


    public void BFSfromTo(String name1, String name2, HashTable hashTable) {
        resetSearch(); // Reset before starting
        int hashCodeOfName1 = hashTable.hash(name1);
        int hashCodeOfName2 = hashTable.hash(name2);

        if (hashTable.get(name1) == null || hashTable.get(name2) == null) {
            System.out.println("Invalid Input");
            return;
        }


        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(hashCodeOfName1);
        marked[hashCodeOfName1] = true;
        System.out.print("BFS Path: ");

        while (!queue.isEmpty()) {
            int current = queue.poll();

            System.out.print((String) hashTable.table[current] + " -> ");

            if (current == hashCodeOfName2) {
                System.out.print("Reached target\n");
                return;
            }

            List<Integer> neighbors = neighbors(current);
            //Sort neighbors by edge weight
            neighbors.sort(Comparator.comparingInt(neighbor -> edges[current][neighbor]));
            for (int neighbor : neighbors) {
                if (!marked[neighbor]) {
                    queue.add(neighbor);
                    marked[neighbor] = true;
                   }
            }

        }
        System.out.println("No path found from " + name1 + " to " + name2);

    }

    public void DFSfromTo(String name1, String name2, HashTable hashTable) {

        resetSearch();
        int hashCodeOfName1 = hashTable.hash(name1);
        int hashCodeOfName2 = hashTable.hash(name2);
         if (hashTable.get(name1) == null || hashTable.get(name2) == null) {
            System.out.println("Invalid Input");
            return;
        }

        dfsRecursive(hashCodeOfName1, hashCodeOfName2, hashTable);
        if(path.size()>0) {
            System.out.print("DFS Path: ");
            for (int i = 0; i < path.size() - 1; i++) {
                System.out.print(path.get(i));
            }
            System.out.print(path.get(path.size() - 1) + "\n");
        } else{
            System.out.println("DFS Path: No path found from "+name1+" to "+name2);
        }


    }

      private void dfsRecursive(int current, int target, HashTable hashTable) {
        marked[current] = true;
        path.add((String) hashTable.table[current] + " -> ");
        if (current == target) {
              path.set(path.size() - 1,(String) hashTable.table[current] );
            return;
        }

        List<Integer> neighbors = neighbors(current);
        for (int neighbor : neighbors) {
            if (!marked[neighbor]) {
                dfsRecursive(neighbor, target, hashTable);
                 if (path.contains((String) hashTable.table[target] )) return;
            }

        }
         path.remove(path.size() - 1);

    }

    public int minDistance(int[] dist, Boolean[] sptSet) {
        int min = Integer.MAX_VALUE, min_index = -1;
        for (int v = 0; v < numV; v++) {
            if (!sptSet[v] && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }
        }
        return min_index;
    }


    public int[] dijkstra(int src) {
        int[] dist = new int[numV];
        Boolean[] sptSet = new Boolean[numV];
        for (int i = 0; i < numV; i++) {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }
        dist[src] = 0;
        for (int count = 0; count < numV - 1; count++) {
            int u = minDistance(dist, sptSet);
            if (u == -1) break; // no more reachable nodes
            sptSet[u] = true;
            for (int v = 0; v < numV; v++) {
                if (!sptSet[v] && edges[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + edges[u][v] < dist[v]) {
                    dist[v] = dist[u] + edges[u][v];
                }
            }
        }
        return dist;
    }


    public void AllPathsShorterThanEqualTo(int pathLen, int VertexNo, String name1, HashTable hashTable) {
        resetSearch(); // Reset before each search
        int hashCodeOfName1 = hashTable.hash(name1);
         if (hashTable.get(name1) == null) {
            System.out.println("Invalid Input");
            return;
        }
        allPaths = new ArrayList<>();
        findPaths(hashCodeOfName1, name1, pathLen, VertexNo, hashTable, new ArrayList<>());
        System.out.println("All Paths with length <= " + pathLen + " and at least " + VertexNo + " vertices from " + name1 + ":");
        if(allPaths.isEmpty()){
            System.out.println("No such paths found!");
        } else {
            for (String path : allPaths) {
                System.out.println(path);
            }
        }

    }

    private void findPaths(int currentVertex,String currentPath, int pathLen, int VertexNo, HashTable hashTable, List<String> current) {
        marked[currentVertex] = true;
        current.add((String) hashTable.table[currentVertex]);
        if (current.size() > 1 && current.size() >= VertexNo ) {
             allPaths.add(String.join(" -> ", current));
        }


        if (current.size() <= pathLen) {
            List<Integer> neighbors = neighbors(currentVertex);
            for (int neighbor : neighbors) {
                if (!marked[neighbor]) {
                    findPaths(neighbor,currentPath, pathLen, VertexNo, hashTable,current );
                }
            }
        }
       current.remove(current.size() -1);
       marked[currentVertex] = false;

    }


    public int NoOfPathsFromTo(String name1, String name2, HashTable hashTable) {
        resetSearch();
        int hashCodeOfName1 = hashTable.hash(name1);
        int hashCodeOfName2 = hashTable.hash(name2);

        if (hashTable.get(name1) == null || hashTable.get(name2) == null) {
            System.out.println("Invalid Input");
            return -1;
        }
        return countAllPaths(hashCodeOfName1, hashCodeOfName2, hashTable, new boolean[numV]);
    }

   private int countAllPaths(int current, int destination, HashTable hashTable, boolean[] visited) {
       if (current == destination) {
           return 1;
       }
       visited[current] = true;
       int pathCount = 0;
       List<Integer> neighbors = neighbors(current);
       for (int neighbor : neighbors) {
           if (!visited[neighbor]) {
               pathCount += countAllPaths(neighbor, destination, hashTable, visited);
           }
       }
       visited[current] = false;
       return pathCount;
   }



    public int NoOfVerticesInComponent(String name1, HashTable hashTable) {

       resetSearch();
        int hashCodeOfName1 = hashTable.hash(name1);

         if (hashTable.get(name1) == null) {
            System.out.println("Invalid Input");
            return -1;
        }
       
        return countVerticesInComponent(hashCodeOfName1);


    }
       private int countVerticesInComponent(int startVertex) {
           Queue<Integer> queue = new LinkedList<>();
        queue.add(startVertex);
        marked[startVertex] = true;
        int count = 0;

        while (!queue.isEmpty()) {
            int v = queue.poll();
            count++;

            List<Integer> neighbors = neighbors(v);
            for(int neighbor : neighbors) {
                  if (!marked[neighbor]) {
                    queue.add(neighbor);
                    marked[neighbor] = true;
                }
            }
        }

        return count;
    }


    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("");
        for (int i = 0; i < numV; i++) {
            for (int j = 0; j < numV; j++) {
                s.append(edges[i][j] + " ");
            }
            s.append("\n");
        }
        return s.toString();
    }
}