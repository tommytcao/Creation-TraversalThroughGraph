package edu.sdsu.cs;

import edu.sdsu.cs.datastructures.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


/**
 * Tommy Cao cssc0229
 * Armando Ramos cssc0215
 * Ramon Leyva  cssc0210
 * Cs310
 */
public class App
{
    static WDGraph myGraph = new WDGraph();

    public static void main (String[] args) {
        createVertexes(); //creates vertex
        createEdges(); // creates edges
        System.out.print(myGraph);
    }

    private static void createEdges(){ //creates edges using your method
        String[] tmp;
        String file = "./edges.csv";
        Path dest = Paths.get(file);
        try {
            List<String> cities = Files.readAllLines(dest,Charset.defaultCharset());
            Iterator<String> ci = cities.iterator();
            while(ci.hasNext()) {
                tmp = ci.next().split(",");
                if(tmp.length == 3) {
                    myGraph.addEdge(new WeightedEdge(new SimpleVertex<>(tmp[0]), new SimpleVertex<>(tmp[1]), Integer.parseInt(tmp[2]))); }
            }
        } catch (FileNotFoundException e) {
            System.out.println("The file was not found. Please check file name is edges.csv");
        } catch (IOException e) {

        } catch (NumberFormatException e){

        }
        }
        private static void createVertexes(){ //creates vertexes using your method
        String[] tmp;
        String file = "./cities.csv";
        Path dest = Paths.get(file);
        try {
            List<String> cities = Files.readAllLines(dest,Charset.defaultCharset());
            Iterator<String> ci = cities.iterator();
            while(ci.hasNext()){
                SimpleVertex vert = new SimpleVertex();
                tmp = ci.next().split(",");
                vert.addVertex(tmp[0]);
                myGraph.addVertex(vert);
            }
        } catch (FileNotFoundException e) {
            System.out.println("The file was not found. Please check file name is cities.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
