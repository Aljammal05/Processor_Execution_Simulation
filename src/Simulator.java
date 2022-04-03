import java.io.File;

public class Simulator {

    public static void main (String []args){
        File file = new File("input.txt");
        ReadConfigFile reader = new ReadConfigFile(file);
        reader.readData();
        CPU cpu = new CPU(reader.getNumberOfProcessors(), reader.getTasks());
        cpu.run();
        File file1 = new File("output.txt");
        cpu.writeOutput(file1);
    }

}
