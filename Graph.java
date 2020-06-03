import java.util.Vector;

public class Graph {

    //Krastine
    private int[][] edge;
    //Virsune
    private Vector<String> vertexName;
    private Vector<Node> nodeVector;

    public Graph() {}

    //Nustatomas grafo "dydis" - kiek virsuniu jis tures
    public void setSize(int n){
        edge=new int[n][n];
        vertexName = new Vector<>(n);
        nodeVector = new Vector<>(n);
    }

    public Vector<Node> getNodeVector(){
        return nodeVector;
    }

    public void createNode(int p){
        Node node = new Node(p);
        nodeVector.add(node);
    }

    public Packet createPacket(Node node, String txt){
        return new Packet (node.ID,node.destID,txt);
    }

    public void sendPacket(int[] route, Node node, String msg){
        Packet pkt = createPacket(node,msg);
        for(int i = route[0];i<route.length;i++){
            if(pkt.getDestinationID()==nodeVector.get(i).ID){
                pkt.setArrived(true);
            }
            if(pkt.isArrived()){
                nodeVector.get(i).text=msg;
            }
        }
    }

    public Node getNode(int port){
        return nodeVector.get(vertexName.indexOf(String.valueOf(port)));
    }

    public int size() {
        return vertexName.size();
    }


    public void setVertexName(int vert, String name) {
        vertexName.add(vert,name);
    }

    public String getVertexName(int vert) {
        return vertexName.get(vert);
    }

    public void addEdge(int src, int targ, int weight) {
        edge[src][targ] = weight;
        edge[targ][src] = weight;
    }

    public void removeEdge(int src, int targ){
        edge[src][targ] = 0;
        edge[targ][src] = 0;
    }

    public int getWeight(int src, int targ) {
        return edge[src][targ];
    }

    public int[] neighbors(int vert) {
        int count = 0;
        for (int i = 0; i < edge[vert].length; i++) {
            if (edge[vert][i] > 0)
                count++;
        }
        final int[] answer = new int[count];
        count = 0;
        for (int i = 0; i < edge[vert].length; i++) {
            if (edge[vert][i] > 0)
                answer[count++] = i;
        }
        return answer;
    }

    public void print() {
        for (int j = 0; j < edge.length; j++) {
            System.out.print(vertexName.get(j) + ": ");
            for (int i = 0; i < edge[j].length; i++) {
                if (edge[j][i] > 0)
                    System.out.print(vertexName.get(i) + "-|" + edge[j][i] + "|  ");
            }
            System.out.println();
        }
    }

}
