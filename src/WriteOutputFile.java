import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class WriteOutputFile {

    private BufferedWriter writer = null ;

    WriteOutputFile(File file){
        try {
            writer =new BufferedWriter(new FileWriter(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeTasksInfo(LinkedList<Task> tasks){
        try {
            writer.write("task id | creation time | completion time ");
            writer.newLine();
            writer.write("------------------------------------------\n");
            for (Task task : tasks) {
                String myContent = task.getId() + "\t|\t" + task.getCreationTime().getId() + "\t|\t" + task.getCompletionTime().getId();
                writer.write(myContent);
                writer.newLine();
                writer.write("------------------------------------------\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void writeLine(String text){
        try {
            writer.write(text);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            if(writer!=null)
                writer.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
