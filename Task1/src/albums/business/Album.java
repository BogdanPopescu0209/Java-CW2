package albums.business;

import java.util.ArrayList;

public class Album {

    private int rank;
    private String title;
    private String artist;
    private int year;
    private String sales;
    private ArrayList<String> tracks;
    private ArrayList<Integer> minutes;
    private ArrayList<Integer> seconds;

    public Album(int r, String t, String a, int y, String s, ArrayList<String> tra, ArrayList<Integer> min, ArrayList<Integer> sec) {

        rank = r;
        title = t;
        artist = a;
        year = y;
        sales = s;
        tracks = tra;
        minutes = min;
        seconds = sec;

    }

    public int getRank() {

        return rank;
    }

    public String getTitle() {

        return title;

    }

    public String getArtist() {

        return artist;

    }

    public int getYear() {

        return year;

    }

    public String getSales() {

        return sales;

    }

    public ArrayList<String> getTracks() {

        return tracks;

    }

    public ArrayList<Integer> getMinutes() {

        return minutes;

    }

    public ArrayList<Integer> getSeconds() {

        return seconds;

    }

    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append("Album title:     " + this.getTitle() + "\n");
        sb.append("Artist:          " + this.getArtist() + "\n");
        sb.append("Year of release: " + this.getYear() + "\n");
        sb.append("Sales to date:   " + this.getSales() + "\n");
        sb.append("\n");
        sb.append("Track list:\n");

        sb.append("----------------------------------------------------------------------------------------------------\n");
        String format = "|%1$-5s|%2$-80s|%3$-5s|%4$-5s|\n";
        sb.append(String.format(format, "No.", "Title", "Mins", "Secs"));
        sb.append("----------------------------------------------------------------------------------------------------\n");

        for (int i = 0; i < this.getTracks().size(); i++) {

            sb.append(String.format(format, i + 1, this.getTracks().get(i), this.getMinutes().get(i), this.getSeconds().get(i)));

        }

        sb.append("----------------------------------------------------------------------------------------------------\n");

        return sb.toString();
    }

}
