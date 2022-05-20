package charityRuns.business;

public class Park extends Venue {

    private final int numChangingFacilities;

    public Park(String name, int numChangingFacilities) {

        super(name);
        this.numChangingFacilities = numChangingFacilities;

    }

    public String getPark() {

        return super.getName();

    }

    public int getNumChangingFacilities() {

        return numChangingFacilities;

    }

}
