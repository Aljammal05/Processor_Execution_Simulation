import java.util.NoSuchElementException;

public class PriorityQv1 implements PriorityQueue {

    private int n;
    private final MinHeap<Task> lowPQue;
    private final MinHeap<Task> highPQue;

    public PriorityQv1(){
        n=0;
        lowPQue = new MinHeap<>();
        highPQue = new MinHeap<>();
    }

    @Override
    public void add(Task task) {
        if (task == null)
            throw new NullPointerException();
        if (task.getPriority()==0){
            lowPQue.insert(task);
        }else if(task.getPriority()==1)
            highPQue.insert(task);
        n++;

    }

    @Override
    public Task remove() {
        if(n==0)
            throw new NoSuchElementException();
        n--;
        if(highPQue.isEmpty())
            return lowPQue.delMax();
        return highPQue.delMax();

    }

    @Override
    public boolean isEmpty(){
        return n == 0;
    }

    @Override
    public Task peek(){
        if(n==0)
            throw new NullPointerException();
        if(highPQue.isEmpty())
            return lowPQue.peek();
        return highPQue.peek();
    }

}
