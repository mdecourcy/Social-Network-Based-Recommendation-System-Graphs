package main.java;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;

import java.io.IOException;
import java.util.HashMap;

import main.java.objects.Artist;
import main.java.objects.ArtistMap;
import main.java.objects.UserFriendships;
import main.java.objects.UserListens;

public class Client
{
    private static Queries queries = new Queries();
    private static UserFriendships userFriendGraph = new UserFriendships();
    private static UserListens userArtistGraph = new UserListens();
    private static ArtistMap artistsMap = new ArtistMap();

    // Main method.
    public static void main(String[] args) throws IOException {

        //build graphs and map
        Digraph directedGraph = userFriendGraph.createUserFriendGraph();

        EdgeWeightedDigraph edgeWeightedDigraph = userArtistGraph.createUserArtistsGraph();

        HashMap<Integer, Artist> artistMap = artistsMap.readArtists();

        // declare users
        int user = 2;
        int user1 = 4;
        int user2 = 31;

        // Calls queries.
        queries.listFriends(user, directedGraph);
        queries.commonFriends(user1, user2, directedGraph);
        queries.listArtists(user1, user2, edgeWeightedDigraph, artistMap);
        queries.listTop10(edgeWeightedDigraph);
        queries.recommend10(user, directedGraph, edgeWeightedDigraph, artistMap);
    }
}