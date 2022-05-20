package charityRuns.presentation;

import charityRuns.business.RunEntry;
import charityRuns.business.Coordinator;
import charityRuns.business.FiveKmRun;
import charityRuns.business.HalfMarathon;
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

            System.out.println("Please choose one of the follwoing options:");
            System.out.println("-------------------------------------------");
            System.out.println("1. List Event Infortmation");
            System.out.println("2. List Venue Details");
            System.out.println("3. List Competitor's Event Entires");
            System.out.println("0. Exit");
            System.out.println("-------------------------------------------");
            System.out.print("Enter choice :>");

            Scanner input = new Scanner(System.in);

            if (input.hasNextInt()) {

                int choice = input.nextInt();

                switch (choice) {

                    case 0:

                        quit = true;
                        break;

                    case 1:

                        System.out.println("Please choose one of the follwoing options:");
                        System.out.println("-------------------------------------------");
                        System.out.println("1. Five km fun run");
                        System.out.println("2. Half marathon");
                        System.out.println("-------------------------------------------");
                        System.out.print("Enter choice :>");

                        int choice1 = input.nextInt();

                        //Print five km fun run
                        if (choice1 == 1) {

                            for (int i = 0; i < cord.DataLoaderFiveKmRun().size(); i++) {

                                System.out.println("");
                                System.out.println("Name of venue : " + cord.DataLoaderFiveKmRun().get(i).getParkName());
                                System.out.println("Current entries: " + cord.DataLoaderFiveKmRun().get(i).getSize());
                                System.out.println("");

                            }
                        }

                        //Print half marathon
                        if (choice1 == 2) {

                            for (int i = 0; i < cord.DataLoaderHalfMarathon().size(); i++) {

                                System.out.println("");
                                System.out.println("Name of venue : " + cord.DataLoaderHalfMarathon().get(i).getTownName());
                                System.out.println("Current entries: " + cord.DataLoaderHalfMarathon().get(i).getSize());
                                System.out.println("Number of water stations: " + cord.DataLoaderHalfMarathon().get(i).getNumWaterStations());
                                System.out.println("");

                            }
                        }

                        break;

                    case 2:

                        System.out.println("Please choose one of the follwoing options:");
                        System.out.println("-------------------------------------------");

                        ArrayList<String> venues = new ArrayList<>();
                        String found = "";

                        //Add park names to array
                        for (int i = 0; i < cord.DataLoaderFiveKmRun().size(); i++) {

                            venues.add(cord.DataLoaderFiveKmRun().get(i).getParkName());

                        }

                        //Add town names to array
                        for (int i = 0; i < cord.DataLoaderHalfMarathon().size(); i++) {
                                
                                if (!venues.contains(cord.DataLoaderHalfMarathon().get(i).getTownName())) {

                                    venues.add(cord.DataLoaderHalfMarathon().get(i).getTownName());

                                }
                        }

                        //Print venues
                        for (int i = 0; i < venues.size(); i++) {

                            System.out.println((i + 1) + "." + venues.get(i));

                        }

                        System.out.println("-------------------------------------------");
                        System.out.print("Enter choice :>");

                        int choice2 = input.nextInt();

                        if (choice2 <= venues.size() && choice2 >= 1) {

                            //Add to array choice 
                            for (int i = 0; i < venues.size(); i++) {

                                found = venues.get(choice2 - 1);

                            }

                            //Check choice and output result
                            for (int i = 0; i < cord.DataLoaderFiveKmRun().size(); i++) {

                                if (found.equals(cord.DataLoaderFiveKmRun().get(i).getParkName())) {

                                    if ("".equals(cord.DataLoaderFiveKmRun().get(i).getDate())) {

                                        System.out.println("");
                                        System.out.println("No events taking place at the venue!");
                                        System.out.println("");

                                    } else {

                                        System.out.println("");
                                        System.out.println("Event type : five km fun run");
                                        System.out.println("Date: " + cord.DataLoaderFiveKmRun().get(i).getDate());
                                        System.out.println("Start time: " + cord.DataLoaderFiveKmRun().get(i).getStartTime());
                                        System.out.println("Number of changing facilities: " + cord.DataLoaderFiveKmRun().get(i).getNumChangingFacilities());
                                        System.out.println("");

                                    }
                                }
                            }

                            //Check choice and output result
                            for (int i = 0; i < cord.DataLoaderHalfMarathon().size(); i++) {

                                if (found.equals(cord.DataLoaderHalfMarathon().get(i).getTownName())) {

                                    if ("".equals(cord.DataLoaderHalfMarathon().get(i).getDate())) {

                                        System.out.println("");
                                        System.out.println("No events taking place at the venue!");
                                        System.out.println("");

                                    } else {

                                        System.out.println("");
                                        System.out.println("Event type : half maratthon");
                                        System.out.println("Date: " + cord.DataLoaderHalfMarathon().get(i).getDate());
                                        System.out.println("Start time: " + cord.DataLoaderHalfMarathon().get(i).getStartTime());
                                        System.out.println("");

                                    }
                                }
                            }

                        } else {

                            System.out.println("");
                            System.out.println("Please try again!");
                            System.out.println("");

                        }

                        break;

                    case 3:

                        System.out.println("");
                        System.out.print("Enter competitor name :> ");
                        String choice3 = input.next();

                        ArrayList<RunEntry> foundComp = new ArrayList<>();
                        String notFound = "";

                        for (FiveKmRun run : cord.DataLoaderFiveKmRun()) {

                            foundComp = run.getArray();

                            for (RunEntry comp : foundComp) {

                                if (Pattern.compile(Pattern.quote(choice3), Pattern.CASE_INSENSITIVE).matcher(comp.getName()).find()) {

                                    System.out.println("");
                                    System.out.println("Name: " + comp.getName());
                                    System.out.println("Age: " + comp.getAge());
                                    System.out.println("Event date: " + run.getDate());
                                    System.out.println("Event number: " + comp.getEventNumber());
                                    System.out.println("Event type: five km fun run");
                                    System.out.println("");
                                    notFound = comp.getName();

                                }
                            }
                        }

                        for (HalfMarathon run : cord.DataLoaderHalfMarathon()) {

                            foundComp = run.getArray();

                            for (RunEntry comp : foundComp) {

                                if (Pattern.compile(Pattern.quote(choice3), Pattern.CASE_INSENSITIVE).matcher(comp.getName()).find()) {

                                    System.out.println("");
                                    System.out.println("Name: " + comp.getName());
                                    System.out.println("Age: " + comp.getAge());
                                    System.out.println("Event date: " + run.getDate());
                                    System.out.println("Event number: " + comp.getEventNumber());
                                    System.out.println("Event type: half marathon");
                                    System.out.println("");
                                    notFound = comp.getName();

                                }
                            }
                        }

                        if (notFound.isEmpty()) {

                            System.out.println("");
                            System.out.println("No match was found!");
                            System.out.println("");

                        }

                        break;

                    default:

                        System.out.println("");
                        System.out.println("Please choose one of the 4 options!");
                        System.out.println("");

                }
            }
        }

    }
}
