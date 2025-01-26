package pkg2.sınıf3.proje;

public class HashTable<Key> {

    Key[] table;
    int M;
    int N;

    public HashTable(int M) {
        table = (Key[]) new Object[M];
        this.M = M;
    }

    public int hash(Key t) {
        return ((t.hashCode() & 0x7fffffff) % M);
    }

    public boolean contains(Key key) {
        int ix = hash(key);
        int startIx = ix;

        while (table[ix] != null) {
            if (table[ix].equals(key)) {
                return true; //found
            }
            ix = (ix + 1) % M;
            if(ix == startIx) return false;
        }
        return false;
    }

      public Key get(Key key) {
        int ix = hash(key);
        int startIx = ix;
        while (table[ix] != null) {
           if (table[ix].equals(key)) return table[ix];
           ix = (ix+1)%M;
           if(ix==startIx) return null;

        }
        return null; //Not Found

    }


    public boolean insert(Key key) {
        if (N == M) {
            System.out.println(" : The table is full! \n");
            return false;
        }
        int ix = hash(key);
        int startIx = ix;
        while (table[ix] != null) {
            if (table[ix].equals(key)) {
                return false;
            }
            ix = (ix + 1) % M;
             if(ix == startIx) return false;
        }
        table[ix] = key;
        N++;
        return true;
    }

    @Override
    public String toString() {
        String s = "[";
        for (int i = 0; i < M; i++) {
            if (table[i] != null) {
                s += table[i] + ",";
            }
        }
        return s + "]";
    }
}