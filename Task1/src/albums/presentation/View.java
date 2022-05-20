package albums.presentation;

import albums.business.Album;
import albums.business.Coordinator;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class View {

    private Coordinator cord;

    public View(Coordinator c) {

        cord = c;

    }

    public void startUI() {

        boolean quit = false;

        while (!quit) {

            System.out.println("");
            System.out.println("List albums.......1");
            System.out.println("Select album......2");
            System.out.println("Search titles.....3");
            System.out.println("Exit..............0");
            System.out.println("");
            System.out.print("Enter choice.....:>");

            Scanner input = new Scanner(System.in);

            if (input.hasNextInt()) {

                int choice = input.nextInt();

                switch (choice) {

                    case 0:

                        quit = true;
                        break;

                    case 1:

                        System.out.println("\n");
                        System.out.println("---------------------------------------------------------------------------------------------------------------");
                        String format = "|%1$-5s|%2$-50s|%3$-30s|%4$-10s|%5$-10s|\n";
                        System.out.format(format, "Rank", "Title", "Artist", "Year", "Sales");
                        System.out.println("---------------------------------------------------------------------------------------------------------------");

                        for (Album albums : cord.DataLoader()) {

                            System.out.format(format, albums.getRank(), albums.getTitle(), albums.getArtist(), albums.getYear(), albums.getSales());
                        }
                        System.out.println("---------------------------------------------------------------------------------------------------------------");
                        System.out.println("\n");

                        break;

                    case 2:

                        System.out.println("\n");
                        System.out.print("Enter album rank from list [1-20] :> ");

                        int rank = input.nextInt();

                        if (rank >= 0 && rank <= 20) {

                            System.out.println("\n");
                            System.out.print(cord.DataLoader()[rank - 1]);
                            System.out.println("\n");

                        } else {

                            System.out.println();
                            System.out.println("Rank out of range!");
                            System.out.println();

                        }

                        break;

                    case 3:

                        System.out.println("");

                        System.out.print("Enter search word or phrase :> ");

                        String search = input.next().toLowerCase();

                        String searchString = search.substring(0, 1).toUpperCase() + search.substring(1).toLowerCase();

                        System.out.println("");
                        
                        String found = "";

                        for (Album albums : cord.DataLoader()) {

                            for (int i = 0; i < albums.getTracks().size(); i++) {

                                if (Pattern.compile(Pattern.quote(search), Pattern.CASE_INSENSITIVE).matcher(albums.getTracks().get(i)).find()) {

                                    System.out.println("-----------------------------");
                                    System.out.println("Artist" + " " + "(" + albums.getArtist() + ")" + " " + "Album" + "(" + albums.getTitle() + ")");
                                    System.out.println("Matching song title(s): ");
                                    System.out.println("-----------------------------");

                                    String text = albums.getTracks().get(i).replaceAll(searchString, search);
                                    
                                    found = albums.getTracks().get(i).replaceAll(searchString, search);
                            
                                    System.out.println("Track" + " " + ((albums.getTracks().indexOf(albums.getTracks().get(i))) + 1) + "." + " " + text.replaceAll(search, search.toUpperCase()));

                                    System.out.println("");

                                }

                            }

                        }

                        if (found.isEmpty()) {

                            System.out.println("No match was found!");

                        }

                        break;

                    default:

                        System.out.println();
                        System.out.println("Please choose one of the 4 options!");

                }
            }
        }

    }

}
