package caixeiroviajante;

import java.util.ArrayList;

/**
 *
 * @author Cindel
 */
public interface Graph {
    
    public void setEdge (int ori, int target, int weight);
    
    public ArrayList<Integer> getAdjVertex (int node);
    
    public int getVertexSize ();
    
    public void printGraph ();

    public int getPeso(int ori, int target);
}
