package edu.sdsu.cs.datastructures;
/**
 * Tommy Cao cssc0229
 * Armando Ramos cssc0215
 * Ramon Leyva  cssc0210
 * Cs310
 */

public class SimpleVertex<V>  implements IVertex<V> {

    private String vertex;
    public IVertex<V> previous;
    public int minDistance = Integer.MAX_VALUE;


    public SimpleVertex() {
    }

    public SimpleVertex(String vertex){
        this.vertex = vertex;
    }

    @Override
    public void addVertex(String vertex) {
        this.vertex = vertex;
    }

    @Override
    public String getVert() {
        return vertex;
    }

    public void setMinDis(int x){
        minDistance=x;
    }
    public int getMinDIs(){
        return minDistance;
    }
    public void setPrev(IVertex<V> x){
        previous = x;
    }
    public IVertex<V> getPrev(){
        return previous;
    }
}