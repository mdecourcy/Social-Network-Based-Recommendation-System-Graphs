package main.java.objects;

public class Artist implements Comparable<Artist>{
    private int id;
    private String name, url, pictureURL;
    private double weight;

    @Override
    public String toString() {
        return "Artist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", pictureURL='" + pictureURL + '\'' +
                '}';
    }

    public Artist()
    {
        this.id = -1;
        this.name = "";
        this.weight = -1.0;
    }

    public Artist(int id, String name, String url, String pictureURL) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.pictureURL = pictureURL;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }

    public double getWeight() {
        return weight;
    }
    public void setWeight(double weight){
        this.weight = weight;
    }

    // Compares the weight of two artists.
    @Override
    public int compareTo(Artist artist)
    {
        Double weight1 = this.weight;
        Double weight2 = artist.weight;
        return weight1.compareTo(weight2);
    }
}

