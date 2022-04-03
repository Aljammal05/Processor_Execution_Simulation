
public class Processor {

    public static int processorId = 1 ;
    private final String id ;
    private Task task;

    Processor(){
        id="p"+processorId;
        task = null;
        processorId++;
    }

    public void setTask(Task task) {
        this.task = task;
        if(task==null)
            return;
        this.task.setState(TaskState.EXECUTING) ;
    }

    public Task getTask() {
        return task;
    }

    public void execute(){
            task.setRequestedTime(task.getRequestedTime() - 1);
            System.out.println("| processor " + id + " is executing task " + task.getId());
    }

    public boolean isIdle() {
        return task == null;
    }

}
