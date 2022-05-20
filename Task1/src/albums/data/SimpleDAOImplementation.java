package albums.data;

import albums.business.Album;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class SimpleDAOImplementation implements DAO {

    private Album[] albumData;

    public Album[] DataLoader() {

        albumData = new Album[20];

        load();

        return albumData;
    }

    private void load() {

        String dataFile = System.getProperty("user.dir") + File.separator + "albums.txt";

        Scanner input = null;

        try {

            input = new Scanner(new File(dataFile));

            int index = 0;

            int rank = 0;
            String title = "";
            String artist = "";
            int year = 0;
            String sales = "";
            ArrayList<String> tracks = new ArrayList<>();
            ArrayList<Integer> minutes = new ArrayList<>();
            ArrayList<Integer> seconds = new ArrayList<>();

            while (input.hasNext()) {

                String line = input.nextLine().trim();

                String[] bits = line.split("[(|:|)]");

                if (bits.length == 5) {

                    rank = Integer.parseInt(bits[0].trim());
                    title = bits[1].trim();
                    artist = bits[2].trim();
                    year = Integer.parseInt(bits[3].trim());
                    sales = bits[4].trim();

                }

                if (bits.length == 3) {

                    tracks.add(bits[0].trim());
                    minutes.add(Integer.parseInt(bits[1].trim()));
                    seconds.add(Integer.parseInt(bits[2].trim()));

                }

                if (bits.length == 1) {

                    albumData[index] = new Album(rank, title, artist, year, sales, tracks, minutes, seconds);

                    rank = 0;
                    title = "";
                    artist = "";
                    year = 0;
                    sales = "";
                    tracks = new ArrayList<>();
                    minutes = new ArrayList<>();
                    seconds = new ArrayList<>();

                    index++;

                }
            }

        } catch (FileNotFoundException fnf) {

            System.out.println("Cannot locate file!");
            System.exit(0);

        }

    }

    public Album[] getTheData() {

        return albumData;

    }
}
