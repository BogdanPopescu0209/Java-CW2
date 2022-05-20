package charityRuns.data;

import charityRuns.business.FiveKmRun;
import charityRuns.business.HalfMarathon;
import java.util.ArrayList;

public interface DAO {

    public ArrayList<FiveKmRun> DataLoaderFiveKmRun();

    public ArrayList<HalfMarathon> DataLoaderHalfMarathon();

}
