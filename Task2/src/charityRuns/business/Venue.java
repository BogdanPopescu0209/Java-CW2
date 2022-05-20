package charityRuns.business;

public abstract class Venue {

    private final String name;

    public Venue(String name) {

        this.name = name;

    }

    public String getName() {

        return name;

    }

}
