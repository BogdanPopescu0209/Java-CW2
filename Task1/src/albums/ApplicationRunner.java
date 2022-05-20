package albums;

import albums.business.Coordinator;
import albums.presentation.View;

public class ApplicationRunner {

    public static void main(String[] args) {

        Coordinator cord = new Coordinator();

        View v = new View(cord);

        v.startUI();

    }
}
