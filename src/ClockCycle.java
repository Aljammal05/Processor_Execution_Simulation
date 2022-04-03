public class ClockCycle {

    public static int cycleId =0 ;
    private final String id ;

    public ClockCycle(){
        id = "c"+cycleId;
        cycleId++;
    }

    public ClockCycle(String id){
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
