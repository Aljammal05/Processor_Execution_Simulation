import java.util.LinkedList;

public class Scheduler {

    private final PriorityQueue readyQueue ;
    private LinkedList<Task> terminatedTasks ;
    private static Scheduler scheduler =null;

    private Scheduler(){
        terminatedTasks = new LinkedList<>();
        readyQueue = new PriorityQv1();
    }

    public static Scheduler getInstance(){
        if(scheduler==null){
            scheduler = new Scheduler();
        }
        return scheduler;
    }

    public void start(Processor[] processors){
        for (Processor processor : processors) {
            if (processor.isIdle()){
                if (!readyQueue.isEmpty()) {
                    processor.setTask(readyQueue.remove());
                    activate(processor);
                }
            }
            else {
                if ( ! readyQueue.isEmpty() && processor.getTask().getPriority() < readyQueue.peek().getPriority() )
                    interrupt(processor,readyQueue.remove());
                activate(processor);
            }
        }
    }

    public void activate(Processor processor){
        processor.execute();
        if(processor.getTask().getRequestedTime()==0)
            terminateTask(processor);
    }

    public void interrupt(Processor processor,Task newTask) {
        readyQueue.add(processor.getTask());
        processor.getTask().setState(TaskState.WAITING);
        System.out.println("| " + processor.getTask().getId() + " has interrupted by " + newTask.getId());
        processor.setTask(newTask);
    }

    public LinkedList<Task> getTerminatedTasks() {
        return terminatedTasks;
    }

    public void addToReadyQ(Task task){
        readyQueue.add(task);
    }

    public void terminateTask(Processor processor){
            processor.getTask().setCompletionTime(new ClockCycle("c" + ClockCycle.cycleId));
            processor.getTask().setState(TaskState.COMPLETED);
            terminatedTasks.add(processor.getTask());
            processor.setTask(null);
    }

}
