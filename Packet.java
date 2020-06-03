

public class Packet {
    private int sourceID;
    private int destinationID;
    private String message;
    private boolean arrived=false;

    public Packet(int sourceID, int destinationID, String message){
        this.sourceID=sourceID;
        this.destinationID=destinationID;
        this.message=message;
    }

    public int getSourceID(){
        return sourceID;
    }

    public int getDestinationID(){
        return destinationID;
    }

    public String getMessage() {
        return message;
    }

    public boolean isArrived() {
        return arrived;
    }

    public void setArrived(boolean arrived) {
        this.arrived = arrived;
    }
}
