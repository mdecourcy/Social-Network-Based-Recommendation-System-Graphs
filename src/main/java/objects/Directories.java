package main.java.objects;

public enum Directories {

    //putting all the directories in one spot so they're easy to manage
    ARTIST_LIST("C:\\Users\\macde\\Desktop\\Assignment 4\\src\\main\\resources\\artists.dat"),
    USER_ARTISTS_LIST("C:\\Users\\macde\\Desktop\\Assignment 4\\src\\main\\resources\\user_artists.dat"),
    USER_FRIENDS_LIST("C:\\Users\\macde\\Desktop\\Assignment 4\\src\\main\\resources\\user_friends.dat");

    private final String directory;

    Directories(String directory) { this.directory = directory; }

    public String getString() {
        return this.directory;
    }
}
