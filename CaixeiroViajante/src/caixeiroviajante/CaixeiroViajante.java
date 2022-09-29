package caixeiroviajante;

import java.util.ArrayList;

/**
 *
 * @author Cindel
 */
public class CaixeiroViajante {

    public static void main(String[] args) {

        FileManager fileManager = new FileManager();
        ArrayList<String> text = fileManager.stringReader(
                "C:/Users/cinde/Documents/facul/8º PERIODO/TSI/trab 1/CaixeiroViajante/src/caixeiroviajante/Teste.txt");

        Graph grafo = null;

        int nVertex = 0;

        for (int i = 0; i < text.size(); i++) {
            String line = text.get(i);
            if (i == 0) {
                nVertex = Integer.parseInt(line.trim());
                grafo = new AdjMatrix(nVertex);
            } else {
                int oriVertex = Integer.parseInt(line.split(" ")[0]);
                String splits[] = line.substring(line.indexOf(" "), line.length()).split(";");
                for (String part : splits) {
                    String edgeData[] = part.split("-");
                    int targetVertex = Integer.parseInt(edgeData[0].trim());
                    int weight = Integer.parseInt(edgeData[1]);

                    /*
                     * ADICIONAR A ARESTA À REPRESENTAÇÃO
                     */
                    grafo.setEdge(oriVertex, targetVertex, weight);
                    grafo.setEdge(targetVertex, oriVertex, weight);
                }
            }
        }

        grafo.printGraph();

        int vetorCaminho[] = new int[nVertex];

        boolean[] av = new boolean[nVertex];
        for (int i = 0; i < nVertex; i++) {
            av[i] = true;
        }

        backTracking(grafo, av, 0, vetorCaminho, 0);
    }

    public static void backTracking(Graph grafo, boolean av[], int node, int vetorCaminho[], int prof) {
        if (prof >= grafo.getVertexSize()) {
            for (int i = 0; i < prof; i++) {
                System.out.print(vetorCaminho[i] + "\t");
            }
            System.out.println("");
        } else {
            ArrayList<Integer> adj = grafo.getAdjVertex(node);
            for (int i : adj) {
                if (av[i] == true) {
                    av[i] = false;
                    vetorCaminho[prof] = node;
                    prof++;
                    backTracking(grafo, av, i, vetorCaminho, prof);
                    prof--;
                    av[i] = true;
                }
            }
        }
    }
}
