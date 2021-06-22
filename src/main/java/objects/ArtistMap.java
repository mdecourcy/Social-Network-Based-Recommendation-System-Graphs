package main.java.objects;

// Algs-4 Imports.
import edu.princeton.cs.algs4.In;

// Java Imports.
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class ArtistMap
{

    // Creates hash map object.
    public static HashMap<Integer, Artist> hashMap = new HashMap<>();

    // Reads the artist.dat file and parses each line to get the artist id and name.
    // Then stores these variables into a hash map.
    public HashMap<Integer, Artist> readArtists() throws IOException {
        HashMap<Integer, Artist> artists = new HashMap<>();

        BufferedReader reader = new BufferedReader(new FileReader(Directories.ARTIST_LIST.getString()));
        String line = "";

        while ((line = reader.readLine()) != null){
            String[] spltLine = line.split("\\t", -1);
            if(spltLine[0].equals("id"))
                continue;
            int id = Integer.parseInt(spltLine[0]);
            String name = spltLine[1];
            String url = spltLine[2];

            String picURL = spltLine[3];

            Artist a = new Artist(id, name, url, picURL);
            artists.put(id, a);
            hashMap.put(id, a);
        }
        return artists;
    }
}
