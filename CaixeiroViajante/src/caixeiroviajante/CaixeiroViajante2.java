package caixeiroviajante;

import java.util.ArrayList;

/**
 *
 * @author Cindel
 */
public class CaixeiroViajante2 {

    private static int menor = Integer.MAX_VALUE;

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
                    // grafo.setEdge(targetVertex, oriVertex, weight);
                }
            }
        }

        int vetorCaminho[] = new int[nVertex];
        int caminho[] = new int[nVertex];

        boolean[] av = new boolean[nVertex];
        for (int i = 0; i < nVertex; i++) {
            av[i] = true;
            vetorCaminho[i] = -1;
        }

        vetorCaminho[0] = 0;
        av[0] = false;

        backTracking(grafo, av, 0, vetorCaminho, 1, caminho);

        System.out.print("O menor caminho é: [");
        for (int i = 0; i < nVertex; i++) {
            System.out.print(caminho[i]);
            if(i < nVertex-1)
            {
                System.out.print(" ");
            }
        }
        System.out.println("]\nSeu custo é de: " + menor);
    }

    public static void backTracking(Graph grafo, boolean av[], int node, int vetorCaminho[], int prof, int[] caminho) {

        int menorCaminhoAux = 0;

        /* operacao de parada */
        if (prof >= grafo.getVertexSize()) {
            for (int i = 0, j = 0; i < prof; i++, j++) {
                System.out.print(vetorCaminho[i] + "\t");

                if (j < prof - 1) {
                    menorCaminhoAux += grafo.getPeso(vetorCaminho[j], vetorCaminho[j + 1]);
                    // System.out.println(menorCaminhoAux);
                }

                /* operação menor caminho */
                if (j == prof - 1) {
                    menorCaminhoAux += grafo.getPeso(vetorCaminho[j], vetorCaminho[j - (prof-1)]);
                    System.out.println("custo: " + menorCaminhoAux);
                    if (menorCaminhoAux <= menor) {
                        for(int k = 0; k < prof; k++)
                        {
                            caminho[k] = vetorCaminho[k];
                        }
                        menor = menorCaminhoAux;
                    }
                }
            }
            System.out.println("");
        } else {
            ArrayList<Integer> adj = grafo.getAdjVertex(node);
            for (int i : adj) {
                if (av[i] == true) {
                    av[i] = false;
                    vetorCaminho[prof] = i;
                    prof++;
                    backTracking(grafo, av, i, vetorCaminho, prof, caminho);
                    prof--;
                    av[i] = true;
                }
            }
        }
    }
}
