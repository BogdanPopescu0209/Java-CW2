package charityRuns.business;

public class RunEntry {

    private int eventNumber;
    private Competitor competitor;

    public RunEntry(Competitor competitor, int eventNumber) {

        this.competitor = competitor;
        this.eventNumber = eventNumber;

    }

    public String getName() {

        return competitor.getName();

    }

    public int getAge() {

        return competitor.getAge();

    }

    public int getEventNumber() {

        return eventNumber;

    }

}
