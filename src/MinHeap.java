import java.util.NoSuchElementException;

public class MinHeap<E extends Comparable<E>>{

    private E[] array;
    private int n;

    public MinHeap(){
        n = 0;
        array = (E[]) new Comparable[2];
    }

    public E peek(){
        return array[1];
    }

    public boolean isEmpty(){
        return n==0;
    }

    public void insert(E item) {
        if(item==null)
            throw new IllegalArgumentException();
        if (n == array.length - 1)
            resize(array.length * 2);
        n++;
        array[n] = item;
        swim(n);
    }

    private void swim(int k) {
        while (k > 1 && more(k/2, k)) {
            exch(k, k/2);
            k = k/2;
        }
    }


    public E delMax() {
        if(n==0)
            throw new NoSuchElementException();
        E tmp = array[1];
        exch(1,n);
        array[n] = null;
        n--;
        sink(1);
        if ((n > 2) && (n == (array.length - 1) / 4))
            resize(array.length / 2);
        return tmp;
    }

    private void sink(int k){
        while(2*k <= n){
            int j = 2*k;
            if(2*k+1 <= n && more(2*k,2*k+1))
                j = 2*k+1;
            if(!more(k,j)) break;
            exch(k,j);
            k = j;
        }
    }


    private boolean more(int i, int j) {
        return array[i].compareTo(array[j]) > 0;
    }

    private void exch(int i, int j) {
        E tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    private void resize(int capacity){
        E[] temp = (E[]) new Comparable[capacity];
        for (int i = 1; i <= n; i++)
            temp[i] = array[i];
        array = temp;
    }
}

