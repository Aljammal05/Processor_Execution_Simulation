enum TaskState {
    WAITING,
    EXECUTING,
    COMPLETED
}

public class Task implements Comparable<Task>{

    public static int nOfTasks = 1 ;
    private final String id ;
    private final ClockCycle creationTime;
    private int requestedTime;
    private ClockCycle completionTime ;
    private final int priority ;
    private TaskState state;

    public Task(ClockCycle creationTime , int requestedTime ,int priority){
        this.creationTime = creationTime ;
        this.requestedTime = requestedTime ;
        this.priority = priority ;
        this.state = TaskState.WAITING;
        id="t"+ nOfTasks;
        nOfTasks++;
    }

    public int getPriority() {
        return priority;
    }

    public String getId() {
        return id;
    }

    public ClockCycle getCreationTime() {
        return creationTime;
    }

    public int getRequestedTime() {
        return requestedTime;
    }

    public void setRequestedTime(int requestedTime) {
        this.requestedTime = requestedTime;
    }

    public ClockCycle getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(ClockCycle completionTime) {
        this.completionTime = completionTime;
    }

    public TaskState getState() {
        return state;
    }

    public void setState(TaskState state) {
        this.state = state;
    }

    public boolean checkCreationTime(ClockCycle creationTime) {
        if(creationTime==null)
            throw new NullPointerException();
        return creationTime.getId().compareTo(this.creationTime.getId())==0;
    }

    @Override
    public int compareTo(Task task){
        if(this.requestedTime > task.getRequestedTime())
            return 1;
        else if(this.requestedTime < task.getRequestedTime())
            return -1;
        return 0;
    }

}
