package main.java.objects;

// Algs-4 Imports.
import edu.princeton.cs.algs4.*;

// Java Imports.
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class UserListens
{
    // Creates edge weighted directed graph.
    EdgeWeightedDigraph edgeWeightedDigraph = new EdgeWeightedDigraph(18746);

    public EdgeWeightedDigraph createUserArtistsGraph() throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(Directories.USER_ARTISTS_LIST.getString()));
        String line = "";

        while ((line = reader.readLine()) != null){
            String[] spltLine = line.split("\\t");
            if(spltLine[0].equals("userID"))
                continue;
            int userID = Integer.parseInt(spltLine[0]);
            int artistID = Integer.parseInt(spltLine[1]);
            int weight = Integer.parseInt(spltLine[2]);
            DirectedEdge e = new DirectedEdge(userID, artistID, weight);
            edgeWeightedDigraph.addEdge(e);
        }
        return edgeWeightedDigraph;
    }
}
