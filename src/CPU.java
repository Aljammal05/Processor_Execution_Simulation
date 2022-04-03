import java.io.File;
import java.util.Queue;

public class CPU {

    private ClockCycle cpuClock =new ClockCycle() ;
    private final Processor [] processors ;
    private final Scheduler scheduler = Scheduler.getInstance();
    private final Queue<Task> jobQueue ;
    private final int numberOfTasks ;

    public CPU(int numberOfProcessors , Queue<Task> jobQueue ){
        processors =new Processor[numberOfProcessors];
        for (int i = 0 ; i < processors.length ; i++){
            processors[i] = new Processor() ;
        }
        this.jobQueue = jobQueue;
        numberOfTasks= jobQueue.size();
    }

    public void run(){
        while (scheduler.getTerminatedTasks().size() != numberOfTasks ){
            System.out.println("|\n|=> "+ cpuClock.getId()+"\n|");
            while ( ! jobQueue.isEmpty() && jobQueue.peek().checkCreationTime(cpuClock) ) {
                scheduler.addToReadyQ(jobQueue.remove());
            }
            scheduler.start(processors);
            cpuClock = new ClockCycle();
        }
        System.out.println("| \n|=> "+ cpuClock.getId());
    }

    public void writeOutput(File file){
        WriteOutputFile writer = new WriteOutputFile(file);
        writer.writeTasksInfo(scheduler.getTerminatedTasks());
        writer.writeLine("total execution time : "+(ClockCycle.cycleId-1)+" cycle");
        writer.close();
    }

}
