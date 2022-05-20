package charityRuns.business;

import java.util.ArrayList;

public class FiveKmRun extends CharityRun {

    private final ArrayList<RunEntry> competitors;
    Park park;
    RunEntry runEntry;

    public FiveKmRun(Park park, String date, String startTime, ArrayList<RunEntry> competitors) {

        super(date, startTime);
        this.park = park;
        this.competitors = competitors;

    }

    public String getParkName() {

        return park.getPark();

    }

    @Override
    public String getDate() {

        return super.getDate();

    }

    @Override
    public String getStartTime() {

        return super.getStartTime();

    }

    public String getName() {

        return runEntry.getName();

    }

    public int getAge() {

        return runEntry.getAge();

    }

    public int getSize() {

        return competitors.size();

    }

    public int getNumChangingFacilities() {

        return park.getNumChangingFacilities();

    }

    public ArrayList<RunEntry> getArray() {

        return competitors;

    }

}
