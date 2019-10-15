package edu.sdsu.cs.datastructures;

import java.util.Iterator;

public interface IVertex <V> {

    /**
     * Adds a vertex to a list
     *
     * @param vertex
     */
    void addVertex(String vertex);

    /**
     * Returns Vertex given the index
     *
     * @return Vertex
     */
    String getVert();
    public void setMinDis(int x);
    public int getMinDIs();
    public void setPrev(IVertex<V> x);
    public IVertex<V> getPrev();
}

