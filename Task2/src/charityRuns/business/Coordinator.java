package charityRuns.business;

import charityRuns.data.DAO;
import charityRuns.data.SimpleDAOImplementation;
import java.util.ArrayList;

public class Coordinator {

    private DAO dao;

    public Coordinator() {

        dao = new SimpleDAOImplementation();

    }

    public ArrayList<FiveKmRun> DataLoaderFiveKmRun() {

        return dao.DataLoaderFiveKmRun();

    }

    public ArrayList<HalfMarathon> DataLoaderHalfMarathon() {

        return dao.DataLoaderHalfMarathon();

    }

}
