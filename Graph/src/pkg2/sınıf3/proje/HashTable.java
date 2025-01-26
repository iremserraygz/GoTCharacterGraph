package pkg2.sınıf3.proje;

public class HashTable<Key> {

    Key[] table;
    int M;
    int N;
    boolean[] full;

    public HashTable(int M) {
        table = (Key[]) new Object[M];
        this.M = M;
    }

    public int hash(Key t) {
        return ((t.hashCode() & 0x7fffffff) % M);
    }

    //contains method returns true if hash map contains the Key
    public boolean contains(Key key) {
        int ix = hash(key);
        System.out.print(" hash : " + ix);
        int startIx = ix;

        while (table[ix] != null && (ix + 1 != startIx)) {
            if (table[ix].equals(key)) {
                return true; //found
            }            //if (ix + 1 == startIx) return false; // starting point
            ix = (ix + 1) % M; // cycle through
            //System.out.print(" ix : " + ix);
        }
        return false;
    }

    public boolean insert(Key key) {
        int ix = hash(key);
        
        if (N == M) {
            System.out.println(" : The table is full! \n");
            return false;
        }
                
        while (table[ix] != null) {
            if (table[ix].equals(key)) {
                return false;
            }
            ix = (ix + 1) % M;
        }
        table[ix] = key;
        N++;
        return true;
    }
    
    @Override
    public String toString() {
        String s = "[";
        for (int i = 0; i < M; i++) {
            if (table[i]!=null) {
                s += table[i] + ",";                
            }

        }
        return s + "]";
    }

}




