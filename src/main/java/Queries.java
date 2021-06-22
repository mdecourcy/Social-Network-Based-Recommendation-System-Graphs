package main.java;

import edu.princeton.cs.algs4.*;
import main.java.objects.Artist;
import main.java.objects.ArtistMap;

import java.util.Arrays;
import java.util.HashMap;

class Queries
{
    // Prints friends list of given user
    public void listFriends(int user, Digraph directedGraph)
    {
        System.out.println("List Friends Method:\n--------------------");
        System.out.print("User "+ user + " is friends with user IDs: ");
        for (int friend : directedGraph.adj(user))
        {
            System.out.print(" " + friend);
        }
        System.out.println(".\n");
    }

    // Prints friends in common
    public void commonFriends(int user1, int user2, Digraph directedGraph)
    {
        System.out.println("Common Friends Method:\n----------------------");
        System.out.println("User "+ user1 + " and user " + user2 + " have the following friends in common.");
        for (int friend1 : directedGraph.adj(user1))
        {
            for (int friend2 : directedGraph.adj(user2))
            {
                if(friend1 == friend2)
                {
                    System.out.println( friend1 );
                }
            }
        }
        System.out.println();
    }

    // Prints mutual artists of both users
    public void listArtists(int user1, int user2, EdgeWeightedDigraph edgeWeightedDigraph, HashMap<Integer, Artist> artistMap)
    {
        System.out.println("Artists in common:\n--------------------");
        for (DirectedEdge directedEdge1 : edgeWeightedDigraph.adj(user1))
        {
            for (DirectedEdge directedEdge2 : edgeWeightedDigraph.adj(user2))
            {
                if(directedEdge1.to() == directedEdge2.to())
                {
                    System.out.println("User: "+ user1 + " and user: " + user2 + " have " + artistMap.get(directedEdge1.to()).getName() + " in common.");

                }
            }
        }
        System.out.println();
    }

    // Prints top 10 artists of all users
    public void listTop10(EdgeWeightedDigraph edgeWeightedDigraph)
    {
        System.out.println("Top 10 Method:\n--------------");

        // Creating two arrays to store artist weights
        Double[] unsortedArray = new Double[18746];
        Double[] sortedArray = new Double[18746];

        // Sets all indices to 0
        for(int i = 0; i < unsortedArray.length; i++)
        {
            unsortedArray[i] = 0.0;
            sortedArray[i] = 0.0;
        }

        // Adds up how many plays artists have
        for(int i = 0; i < 2101; i++)
        {
            for (DirectedEdge directedEdge : edgeWeightedDigraph.adj(i))
            {
                unsortedArray[directedEdge.to()] = unsortedArray[directedEdge.to()] + directedEdge.weight();
                sortedArray[directedEdge.to()] = sortedArray[directedEdge.to()] + directedEdge.weight();
            }
        }

        // Sorts array
        Arrays.sort(sortedArray);

        // Prints top 10 most listened to artists
        for(int i = sortedArray.length - 1; i >= sortedArray.length - 10; i--)
        {
            for(int j = 0; j <= unsortedArray.length - 1; j++)
            {
                if(sortedArray[i].equals(unsortedArray[j]))
                {
                    System.out.printf("%-30.30s  %-30.30s%n", ArtistMap.hashMap.get(j).getName(), "(" + sortedArray[i] + ") plays");
                }
            }
        }
        System.out.println();
    }

    // Recommends 10 most listened to artists by the user and friends.
    public void recommend10(int user, Digraph directedGraph, EdgeWeightedDigraph edgeWeightedDigraph, HashMap<Integer, Artist> artistMap)
    {
        System.out.println("Recommended 10 for user: " + user + "\n--------------------");

        // Creates hash map to store artist id and object.
        HashMap<Integer, Artist> artistHashMap = new HashMap<>();

        // Sets artist attributes for user.
        for(DirectedEdge directedEdge : edgeWeightedDigraph.adj(user))
        {
            fillMap(artistHashMap, directedEdge, artistMap);
        }

        // Sets artist attribute for users friends.
        for(int friend : directedGraph.adj(user))
        {
            for (DirectedEdge directedEdge : edgeWeightedDigraph.adj(friend))
            {
                fillMap(artistHashMap, directedEdge, artistMap);
            }
        }

        // Creates MPQ to store artists.
        MinPQ<Artist> pq = new MinPQ<>();

        // Inserts artists into priority queue.
        for(int id : artistHashMap.keySet())
        {
            pq.insert(artistHashMap.get(id));
        }

        // Delete min artist weight, until PQ at size 10
        int pqSize = pq.size();
        for(int i = 0; i < pqSize - 10; i++)
        {
            pq.delMin();
        }

        // Flips PQ using stack, will now show descending artist by weight.
        Stack<Artist> flipPQ = new Stack<>();
        for (Artist artist : pq)
        {
            flipPQ.push(artist);
        }

        // Prints most listened to artist.
        for(Artist artist : flipPQ)
        {
            System.out.println("Artist Name: " + artist.getName());
            System.out.println("Artist ID: " + artist.getID());
            System.out.println("Number of Plays: " + artist.getWeight());
            System.out.println("URL: " + artist.getUrl() + "\n--------------------");

        }
    }

    // Fills map  with artists the users listen to
    private void fillMap(HashMap<Integer, Artist> artistHashMap, DirectedEdge directedEdge, HashMap<Integer, Artist> artistMap)
    {
        // Creates artist object.
        Artist artist = new Artist();

        // Sets artist id, name and weight.
        artist.setID(directedEdge.to());
        artist.setName(artistMap.get(directedEdge.to()).getName());
        artist.setWeight(directedEdge.weight());
        artist.setUrl(artistMap.get(directedEdge.to()).getUrl());
        artist.setPictureURL(artistMap.get(directedEdge.to()).getPictureURL());

        // Checks if current artist weight is greater than artist wait already stored.
        if(artistHashMap.containsKey(directedEdge.to()) &&
                artistHashMap.get(directedEdge.to()).getWeight() < artist.getWeight())
        {
            artistHashMap.put(directedEdge.to(), artist);
        }

        // Puts the artist into the hash map if not already contained.
        else if(!artistHashMap.containsKey(directedEdge.to()))
        {
            artistHashMap.put(directedEdge.to(), artist);
        }
    }
}
