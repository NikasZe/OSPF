public class Node {
    int ID;
    int destID;
    String text;

    public Node(int ID) {
        this.ID = ID;
    }

    public void setDestPort(int destinationID){
        destID= destinationID;
    }

}
