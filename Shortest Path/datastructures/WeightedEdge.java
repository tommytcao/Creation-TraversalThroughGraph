package edu.sdsu.cs.datastructures;

/**
 * Tommy Cao cssc0229
 * Armando Ramos cssc0215
 * Ramon Leyva  cssc0210
 * Cs310
 */

public class WeightedEdge<E> implements IEdge<E> {


    private IVertex vertex;
    private IVertex edge;
    private Integer value;

    public WeightedEdge() {
    }

    public WeightedEdge(IVertex vert ,IVertex edg ,Integer val) {
        vertex = vert;
        edge = edg;
        value = val;
    }


    @Override
    public void enqueueEdge(IVertex edg) {
        edge = edg;
    }

    @Override
    public void enqueueValue(Integer val) {
        value = val;
    }

    @Override
    public IVertex getEdge() {
        return edge;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public void addVert(IVertex toAdd){
        vertex = toAdd;
    }

    @Override
    public IVertex getVert(){
        return vertex;
    }
}
