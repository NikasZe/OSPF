import java.util.Arrays;
import java.util.ArrayList;

public class Dijkstra_Alg {

    // Dijkstra's algorithm to find shortest path from startNode to all other nodes
    public static int[] find(Graph graph, int startNode) {
        // Trumpiausias zinomas kelias is tam tikros virsunes
        final int[] sDist = new int[graph.size()];
        // Masyvas sudarytas is iki siol aplankytu virsuniu eiles tvarka
        final int[] route = new int[graph.size()];
        //Masyvas parodantis ar virsune jau "aplankyta"
        final boolean[] visited = new boolean[graph.size()];

        Arrays.fill(sDist, Integer.MAX_VALUE);
        sDist[startNode] = 0;

        for (int i = 0; i < sDist.length; i++) {
            final int next = minVert(sDist, visited);
            visited[next] = true;

            final int[] n = graph.neighbors(next);
            for (final int v : n) {
                final int d = sDist[next] + graph.getWeight(next, v);
                if (sDist[v] > d) {
                    sDist[v] = d;
                    route[v] = next;
                }
            }
        }
        return route;
    }


    private static int minVert(int[] sDist, boolean[] v) {
        int x = Integer.MAX_VALUE;
        int y = -1;
        for (int i = 0; i < sDist.length; i++) {
            if (!v[i] && sDist[i] < x) {
                y = i;
                x = sDist[i];
            }
        }
        return y;
    }

    //Atspausdinami trumpiausias kelias is parinktos pradines virsunes i kita virsune
    public static void printPath(Graph graph, int[] route, int s, int e) {
        ArrayList<String> path = new ArrayList<>();
        int x = e;
        while (x != s) {
            path.add(0, graph.getVertexName(x));
            x = route[x];
        }
        path.add(0, graph.getVertexName(s));
        System.out.println(path);
    }



}
