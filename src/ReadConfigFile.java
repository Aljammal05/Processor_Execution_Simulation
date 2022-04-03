import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class ReadConfigFile {

    private  int numberOfProcessors , numberOfTasks;
    private final Queue<Task> tasks = new LinkedList<>();
    private BufferedReader reader;

    public ReadConfigFile (File file){
        try {
            reader = new BufferedReader(new FileReader(file));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readData(){
        try {
            numberOfProcessors = Integer.parseInt(reader.readLine().substring(23).trim());
            numberOfTasks = Integer.parseInt(reader.readLine().substring(18).trim());
            reader.readLine();
            for(int i = 0; i< numberOfTasks; i++){
                int creationCycle , requestedTime , priority ;
                String line = reader.readLine();
                creationCycle = Integer.parseInt(line.split(",")[0].trim());
                ClockCycle creationTime = new ClockCycle("c"+creationCycle);
                requestedTime = Integer.parseInt(line.split(",")[1].trim());
                priority = Integer.parseInt(line.split(",")[2].trim());
                tasks.add(new Task(creationTime,requestedTime,priority));
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (reader!=null)
                    reader.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public int getNumberOfProcessors() {
        return numberOfProcessors;
    }

    public Queue<Task> getTasks() {
        return tasks;
    }

}
