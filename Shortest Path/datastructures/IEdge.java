package edu.sdsu.cs.datastructures;

public interface IEdge<E> {

    /**
     * Sets the orginal vertex or starting point
     * @param vertString
     */
    void addVert(IVertex vertString);

    /**
     * enqueue an edge or ending point
     * @param edge
     */
    void enqueueEdge(IVertex edge);

    /**
     * Sets the value corresponding to an edge.
     * The value is located at the same index in the array the edge
     * @param value
     */
    void enqueueValue(Integer value);

    /**
     * @return  Vertex
     */
    IVertex getVert();

    /**
     * provides the edge or a end vertex given an index
     * @return edge
     */
    IVertex getEdge();

    /**
     * provides the edge or a end vertex given an index
     * @return Integer of value/Cost
     */
    Integer getValue();



}
