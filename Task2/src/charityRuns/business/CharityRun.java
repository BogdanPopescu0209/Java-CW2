package charityRuns.business;

public abstract class CharityRun {

    private final String date;
    private final String startTime;

    public CharityRun(String date, String startTime) {

        this.date = date;
        this.startTime = startTime;

    }

    public String getDate() {

        return date;

    }

    public String getStartTime() {

        return startTime;

    }

}
