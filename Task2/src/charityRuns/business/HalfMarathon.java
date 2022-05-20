package charityRuns.business;

import java.util.ArrayList;

public class HalfMarathon extends CharityRun {

    private final ArrayList<RunEntry> competitors;
    Competitor competitor;
    Town town;
    Park park;

    private final int numWaterStations;

    public HalfMarathon(Town town, String d, String t, int nr, ArrayList<RunEntry> competitors) {

        super(d, t);
        this.numWaterStations = nr;
        this.town = town;
        this.competitors = competitors;

    }

    public String getTownName() {

        return town.getTown();

    }

    @Override
    public String getDate() {

        return super.getDate();

    }

    @Override
    public String getStartTime() {

        return super.getStartTime();

    }

    public int getNumWaterStations() {

        return numWaterStations;

    }

    public String getName() {

        return competitor.getName();

    }

    public int getAge() {

        return competitor.getAge();

    }

    public int getSize() {

        return competitors.size();

    }

    public ArrayList<RunEntry> getArray() {

        return competitors;

    }

}
