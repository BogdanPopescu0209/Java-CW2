package charityRuns;

import charityRuns.business.Coordinator;
import charityRuns.presentation.View;

public class ApplicationRunner {

    public static void main(String[] args) {

        Coordinator cord = new Coordinator();

        View v = new View(cord);

        v.startUI();

    }

}
