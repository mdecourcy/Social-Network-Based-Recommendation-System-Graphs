package main.java.objects;

import edu.princeton.cs.algs4.Digraph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class UserFriendships
{
    // Creates directed graph object.
    Digraph directedGraph = new Digraph(2101 );

    public Digraph createUserFriendGraph() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(Directories.USER_FRIENDS_LIST.getString()));
        String line = "";

        while ((line = reader.readLine()) != null){
            String[] spltLine = line.split("\\t");
            if(spltLine[0].equals("userID"))
                continue;
            int userID = Integer.parseInt(spltLine[0]);
            int friendID = Integer.parseInt(spltLine[1]);
            directedGraph.addEdge(userID, friendID);

        }
        return directedGraph;
    }
}
