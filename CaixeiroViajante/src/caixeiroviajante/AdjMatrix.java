/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caixeiroviajante;

import java.util.ArrayList;

/**
 *
 * @author Cindel
 */
public class AdjMatrix implements Graph {

    private int[][] matrix;

    public AdjMatrix(int nVertex) {
        this.matrix = new int[nVertex][nVertex];
    }

    @Override
    public ArrayList<Integer> getAdjVertex(int node) {
        ArrayList<Integer> adj = new ArrayList<>();
        for (int j = 0; j < this.matrix.length; j++) {
            if (node != j && this.matrix[node][j] != 0) {
                adj.add(j);
            }
        }
        return adj;
    }

    @Override
    public void setEdge(int ori, int target, int weight) {
        this.matrix[ori][target] = weight;
    }

    @Override
    public int getPeso(int ori, int target) {
        return this.matrix[ori][target];
    }

    @Override
    public void printGraph() {
        for (int i = 0; i < this.matrix.length; i++) {
            for (int j = 0; j < this.matrix.length; j++) {
                System.out.printf("%d\t", this.matrix[i][j]);
            }
            System.out.println("");
        }
    }

    public int getVertexSize() {
        return this.matrix.length;
    }

}
