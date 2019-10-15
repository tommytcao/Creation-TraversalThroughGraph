/**
 * Tommy Cao cssc0229
 * Ramon Leyva cssc0210
 * Armando Ramos cssc0215
 */

package edu.sdsu.cs.datastructures;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class WDGraph<V,E extends IVertex<V>> implements IGraph<V,E> {
    private Map<V, List<IEdge<E>>> myGraph =  new HashMap<>();
    private List<IVertex<V>> keyList = new ArrayList<>();
    private List<IEdge<E>> allEdges = new ArrayList<>();
    int cost = 0;

    @Override
    public Iterable<IVertex<V>> vertices() {
        return keyList;
    }

    @Override
    public Iterable<IEdge<E>> edges() {
        return allEdges;
    }

    @Override
    public int numEdges() {
        int count = 0;
        for(Map.Entry<V, List<IEdge<E>>> entry: myGraph.entrySet()){
            List<IEdge<E>> value = entry.getValue();
            for(int i =0; i <value.size();i ++) {
                count++;
            }
        }
        return count;
    }

    @Override
    public int numVertices() {
        return myGraph.keySet().size();
    }

    @Override
    public int minimumDistance(IVertex<V> start, IVertex<V> end) {
        int path = 0;
        shortestPath(start, end);

        return cost;
    }

    @Override
    public Iterable<IEdge<E>> shortestPath(IVertex<V> start, IVertex<V> end) {

        Dijkstra dijkstra = new Dijkstra(myGraph, myGraph.size()); // I had to make a private class.

        dijkstra.begin(start, end);
        cost = dijkstra.pathCost(end);
        return dijkstra.returnTheList(end);
    }

    @Override
    public void connectVertices(IVertex<V> start, IVertex<V> end, int weight) { //this is very important
        allEdges.add(new WeightedEdge<>(start, end, weight));
        myGraph.get(start.getVert()).add(new WeightedEdge<>(start, end, weight));

    }

    @Override
    public void addVertex(IVertex<V> toAdd) {
        keyList.add(toAdd);
        myGraph.put(toAdd.getVert(),new LinkedList<>());
    }

    @Override
    public void addEdge(IEdge<E> toAdd) {
        allEdges.add(toAdd);
        myGraph.get(toAdd.getVert().getVert()).add(toAdd);
        //connectVertices(toAdd.getVert(),toAdd.getEdge(),toAdd.getValue().intValue());
    }
    @Override
    public String toString(){
        String ret = "\n Graph:\n";
        ret +=numVertices() + "verticies\n" + numEdges() + "edges \n\n";
        ret +="Connection List: \n";
        for(Map.Entry<V, List<IEdge<E>>> entry: myGraph.entrySet()){
            String key = entry.getKey().toString();
            List<IEdge<E>> value = entry.getValue();
            ret += key + ": ";
            for(int i =0; i <value.size();i ++){
                String destName = value.get(i).getEdge().getVert().toString();
                int weight = value.get(i).getValue();
                if(i == value.size()) {
                    ret += destName + "[" + weight + "] ";
                }
                ret+= destName + "[" + weight + "], ";
            }
            ret += "\n";
        }
        return ret;

    }

    private class Dijkstra {
        private Map<V, List<IEdge<E>>> adjacency;
        private Map<V, Integer> vertexPathCount;
        private List<IEdge<E>> storedEdge;
        private IPriorityQueue<IEdge<E>> priQueue;
        private List<V> visited;

        Dijkstra(Map<V, List<IEdge<E>>> theGraph, int keySize){
            priQueue = new ArrayPriQueue<>();
            visited = new ArrayList<>();
            storedEdge = new ArrayList<>();
            vertexPathCount = new HashMap<>((4*keySize) / 3);
            adjacency = theGraph;
            readyVertexCounter();
        }

        private void readyVertexCounter(){
            for(V vertexNames : adjacency.keySet())
                vertexPathCount.put(vertexNames, -1);
        }

        void begin(IVertex<V> start, IVertex<V> end){
            IEdge<E> currentEdge;
            int cost = 0;
            int value;

            vertexPathCount.replace(start.getVert(), 0);
            visited.add(start.getVert());


            for(IEdge<E> edges : adjacency.get(start.getVert())) {
                priQueue.enqueue(edges);

                // I already inserted them in the hashmap, and this strategy may be necessary.
                vertexPathCount.replace((V) edges.getEdge().getVert(), edges.getValue());
            }
            if(start.getVert().toString().equals(end.getVert().toString()))
                return;

            while(priQueue.size() !=0 ){
                currentEdge = priQueue.poll();
                storedEdge.add(currentEdge);

                if(currentEdge.getEdge().getVert().toString().equals(end.getVert().toString()))
                    return;

                for(IEdge<E> edges : adjacency.get(currentEdge.getEdge().getVert())){//we are at vertex's neighbors.

                    //get the cost that has been measured from the 'start' to the current vertex.
                    value = vertexPathCount.get(edges.getVert().getVert());

                    //This will add the new value with the total cost that was in the counter.
                    cost = value + edges.getValue();

                    if(cost > vertexPathCount.get(edges.getEdge().getVert()) &&
                            vertexPathCount.get(edges.getEdge().getVert()) != -1)
                        continue;

                    vertexPathCount.replace((V) edges.getEdge().getVert(), cost);

                    storedEdge.add(edges);
                    priQueue.enqueue(edges);

                    if(edges.getEdge().getVert().toString().equals(end.getVert().toString()))
                        return;
                }

                //Now I add the vertex I explored in the 'visited' list
                visited.add((V) currentEdge.getEdge().getVert());

                if(visited.size() == adjacency.size())
                    break;
            }
        }

        List<IEdge<E>> returnTheList(IVertex dest){
            int count;
            IEdge<E> tracker;
            List<IEdge<E>> finalSortList = new LinkedList<>();

            if(vertexPathCount.get(dest.getVert()) == -1 || vertexPathCount.get(dest.getVert()) == 0)
                return finalSortList;

            // getting checker ready.
            tracker = storedEdge.get(storedEdge.size() - 1);
            count = storedEdge.size();
            finalSortList.add(storedEdge.remove(count - 1));

            for(int i = storedEdge.size(); i > 0 ; i--) {

                // this thing is getting the vertex at the end of the list.
                if (storedEdge.get(i - 1).getEdge().getVert().toString()
                        .equals(tracker.getVert().getVert().toString())) {

                    ((LinkedList<IEdge<E>>) finalSortList).addFirst(storedEdge.get(i - 1));

                    tracker = storedEdge.get(i - 1);
                }

            }
            return finalSortList;
        }

        int pathCost(IVertex<V> dest){
            return vertexPathCount.get(dest.getVert());
        }

    }
}
