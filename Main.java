//Autorius Nikodemas Zeruolis, informatika 2 kursas, 1 grupe

import java.util.Random;
import java.util.Scanner;
import java.lang.String;
public class Main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        Random rand = new Random();
        String choice, txt;
        boolean running=true;
        int gSize, a, b, c, vCount=0;
        Graph nGraph = new Graph();

        System.out.println("This program implements the OSPF routing protocol using Dijkstras algorithm to find the");
        System.out.println("shortest path in a network of routers with each router having its corresponding node");
        System.out.println("from which it is possible to send packets that will travel along the determined shortest path.");
        System.out.println(" ");
        System.out.println(" ");

        //Switchas su galimomis komandomis
        while(running) {
            System.out.println("Enter 'createnetwork' to set the number of vertexes/routers.");
            System.out.println("Enter 'addlink' to link 2 routers together and set the wight of the link.");
            System.out.println("Enter 'deletelink' to delete a link between 2 routers.");
            System.out.println("Enter 'sendpacket' to send a packet from one node to another(each nodes corresponds a router).");
            System.out.println("Enter 'shownetwork' to show the network.");
            System.out.println("Enter 'showdata' to see what data each node contains.");
            System.out.println("Enter 'quit' to exit.");
            choice=scan.nextLine();
            switch (choice) {
                //Nustatoma kiek virsuniu, sutampanciu su routeriais, yra grafe
                case "createnetwork":
                    System.out.println("Enter number of routers: ");
                    gSize = scan.nextInt();
                    scan.nextLine();
                    nGraph.setSize(gSize);
                    for(int i=0; i<gSize;i++){
                        System.out.println("Added router with the ID of: "+vCount);
                        nGraph.setVertexName(vCount,String.valueOf(vCount));
                        nGraph.createNode(vCount);
                        vCount++;
                    }
                    break;
                //Pridedama grafo krastine, kuri sutampa su link'u tarp routeriu
                case "addlink":
                    System.out.println("Enter link source: ");
                    a=scan.nextInt();
                    scan.nextLine();
                    System.out.println("Enter link destination: ");
                    b=scan.nextInt();
                    scan.nextLine();
                    System.out.println("Enter link weight: ");
                    c=scan.nextInt();
                    scan.nextLine();
                    nGraph.addEdge(a,b,c);
                    break;
                //Nuimama grafo krastine, kuri sutampa su link'u tarp routeriu
                case "deletelink":
                    System.out.println("Enter link source: ");
                    a=scan.nextInt();
                    scan.nextLine();
                    System.out.println("Enter link destination: ");
                    b=scan.nextInt();
                    scan.nextLine();
                    nGraph.removeEdge(a,b);
                    break;
                //Pridedama grafo virsune, kuri sutampa su routeriu
                case "sendpacket":
                    System.out.println("Enter starting ID(same as source router ID):");
                    a=scan.nextInt();
                    scan.nextLine();
                    System.out.println("Enter destination ID(same as destination router ID):");
                    b=scan.nextInt();
                    scan.nextLine();

                    int[] route = Dijkstra_Alg.find(nGraph,a);
                    System.out.println("Packet will be sent along this path: ");
                    Dijkstra_Alg.printPath(nGraph,route,a,b);

                    nGraph.getNode(a).setDestPort(b);
                    c = rand.nextInt(1000);
                    txt=String.valueOf(c);
                    System.out.println("The following data will be sent: "+txt);
                    nGraph.sendPacket(route,nGraph.getNode(a),txt);
                    break;
                //Parodomas grafas sutampantis su tinklu, kuriame nurodomi visu routeriu id ir rysiai tarp routeriu
                case "shownetwork":
                    nGraph.print();
                    break;
                //Atspausdinama kiekviename node esanti informacija
                case "showdata":
                    for(Node tempNode : nGraph.getNodeVector()){
                        System.out.println("Node "+tempNode.ID+", data: "+ tempNode.text);
                    }
                case "quit":
                    running=false;
                    break;
            }
        }

        //Specifiskai dijkstros algoritmo veikimo pavyzdys:
        /*
        nGraph.setSize(6);
        nGraph.setVertexName(0, "000");
        nGraph.setVertexName(1, "111");
        nGraph.setVertexName(2, "222");
        nGraph.setVertexName(3, "333");
        nGraph.setVertexName(4, "444");
        nGraph.setVertexName(5, "555");

        nGraph.addEdge(0, 1, 3);

        nGraph.addEdge(0, 2, 5);

        nGraph.addEdge(1, 3, 9);

        nGraph.addEdge(1, 2, 3);

        nGraph.addEdge(1, 4, 1);

        nGraph.addEdge(2, 4, 3);

        nGraph.addEdge(2, 5, 2);

        nGraph.addEdge(3, 5, 1);

        nGraph.print();

        final int[] pred = Dijkstra_Alg.find(nGraph,0);
        for (int n = 0; n < 6; n++) {
            Dijkstra_Alg.printPath(nGraph, pred, 0, n);
        }*/

    }
}