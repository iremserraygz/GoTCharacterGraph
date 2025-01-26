package pkg2.sınıf3.proje;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.lang.Exception;

public class GraphMatrix {

    int edges[][]; // can be anything, but int vertices handy
    // can be double if there are double weigths
    int numV;
    int numE;
    int dist[];
    int[] distTo;
    int[] edgeTo;
    boolean[] marked;
    ArrayList<String> path = new ArrayList<>();

    public GraphMatrix(int V) {
        this.numV = V;
        edges = new int[V][V];
        marked = new boolean[V];
        edgeTo = new int[V];
        distTo = new int[V];
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                edges[i][j] = 0;
            }
        }
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

    public Integer[] neighborsArray(int hashCodeOfSource) {
        Integer[] array = new Integer[edges[hashCodeOfSource].length];
        int index = 0;

        for (int i = 0; i < numV; i++) {
            if (edges[hashCodeOfSource][i] > 0) {
                array[index++] = i;
            }
        }
        return array;
    }

    public void BFSfromTo(String name1, String name2, HashTable hashTable) throws IOException, NullPointerException {//bfs yapıp her adımında print yapması lazım 

        int hashCodeOfName1 = hashTable.hash(name1);
        int hashCodeOfName2 = hashTable.hash(name2);
        marked[hashCodeOfName1] = true;
        Integer[] a = (Integer[]) neighborsArray(hashCodeOfName1);

        if (a.length == 0) {
            return;
        }

        LinkedList<Integer> q = new LinkedList<>();
        System.out.print(name1 + " -> ");
        q.addLast(hashCodeOfName1);
        while (!q.isEmpty()) {
            hashCodeOfName1 = q.removeFirst();
            a = (Integer[]) neighborsArray(hashCodeOfName1);
            for (int i = 0; i < a.length; i++) {
                int w = a[i];
                if (!marked[w] && a[i] != null) {
                    System.out.print((String) hashTable.table[w] + " -> ");
                    q.addLast(w);
                    marked[w] = true;
                    edgeTo[w] = hashCodeOfName1;
                    distTo[w] = distTo[hashCodeOfName1] + 1;
                    if (hashCodeOfName2 == w) {
                        System.out.print((String) hashTable.table[w]);

                    }
                    break;
                }
                if (hashCodeOfName2 == w) {
                    System.out.print((String) hashTable.table[w]);
                    break;
                }

            }
        }

    }

    public void DFSfromTo(String name1, String name2, HashTable hashTable) {// dfs yapıp her adımında printlemesi lazım
        path.add(name1 + " -> ");
        if (name1.equals(name2)) {
            path.add(name2);
        }

        int hashCodeOfName1 = hashTable.hash(name1);
        int hashCodeOfName2 = hashTable.hash(name2);

        this.marked[hashCodeOfName1] = true;
        for (int i = 0; i < this.edges[hashCodeOfName1].length; i++) {
            if (this.edges[hashCodeOfName1][i] > 0 && (!this.marked[i])) {
                if (i == hashCodeOfName2) {
                    path.add(name2);
                    break;
                }
                if (name1 != name2) {
                    name1 = (String) hashTable.table[i];
                    DFSfromTo(name1, name2, hashTable);
                }
                break;
            }
        }
    }

    public int minDistance(int dist[], Boolean sptSet[]) {
        int min = Integer.MAX_VALUE, min_index = -1;
        for (int v = 0; v < numV; v++) {
            if (sptSet[v] == false && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }
        }
        return min_index;
    }

    public int[] dijkstra(int graph[][], int src) {
        dist = new int[numV];
        Boolean sptSet[] = new Boolean[numV];
        for (int i = 0; i < numV; i++) {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }
        dist[src] = 0;
        for (int count = 0; count < numV - 1; count++) {
            int u = minDistance(dist, sptSet);
            sptSet[u] = true;
            for (int v = 0; v < numV; v++) {
                if (!sptSet[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }
       return dist;
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
