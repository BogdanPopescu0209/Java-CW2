package charityRuns.data;

import charityRuns.business.Competitor;
import charityRuns.business.FiveKmRun;
import charityRuns.business.HalfMarathon;
import charityRuns.business.Park;
import charityRuns.business.RunEntry;
import charityRuns.business.Town;
import java.util.ArrayList;

public class SimpleDAOImplementation implements DAO {

    private ArrayList<FiveKmRun> fiveKmRun;
    private ArrayList<HalfMarathon> halfMarathon;

    public ArrayList<FiveKmRun> DataLoaderFiveKmRun() {

        fiveKmRun = new ArrayList<>();

        loadFiveKmRun();

        return fiveKmRun;
    }

    public ArrayList<HalfMarathon> DataLoaderHalfMarathon() {

        halfMarathon = new ArrayList<>();

        loadHalfMarathon();

        return halfMarathon;
    }

    public void loadFiveKmRun() {

        ArrayList<RunEntry> RunEntryEvent1 = new ArrayList<>();
        ArrayList<RunEntry> RunEntryEvent2 = new ArrayList<>();
        ArrayList<RunEntry> RunEntryEvent3 = new ArrayList<>();
        ArrayList<RunEntry> RunEntryEvent7 = new ArrayList<>();

        //Load park, number of changing facilities, date and time
        FiveKmRun fiveKmRunObj1 = new FiveKmRun(new Park("Battersea Park", 100), "01/05/2021", "12:00", RunEntryEvent1);
        fiveKmRun.add(fiveKmRunObj1);
        FiveKmRun fiveKmRunObj2 = new FiveKmRun(new Park("Bushy Park", 100), "08/05/2021", "12:00", RunEntryEvent2);
        fiveKmRun.add(fiveKmRunObj2);
        FiveKmRun fiveKmRunObj3 = new FiveKmRun(new Park("Hampstead Heath", 100), "15/05/2021", "12:00", RunEntryEvent3);
        fiveKmRun.add(fiveKmRunObj3);
        FiveKmRun fiveKmRunObj4 = new FiveKmRun(new Park("Hyde Pak", 0), "", "", RunEntryEvent7);
        fiveKmRun.add(fiveKmRunObj4);
        
        //Five km fun run
        RunEntry runEntryObj1 = new RunEntry(new Competitor("Annalise Nixon", 25), 1);
        RunEntryEvent1.add(runEntryObj1);

        RunEntry runEntryObj2 = new RunEntry(new Competitor("Karen Mcmillan", 14), 2);
        RunEntryEvent1.add(runEntryObj2);

        RunEntry runEntryObj3 = new RunEntry(new Competitor("Anabelle Dunkley", 39), 3);
        RunEntryEvent1.add(runEntryObj3);

        RunEntry runEntryObj4 = new RunEntry(new Competitor("Dominic Leech", 18), 8);
        RunEntryEvent2.add(runEntryObj4);

        RunEntry runEntryObj5 = new RunEntry(new Competitor("Adelaide Bennett", 60), 9);
        RunEntryEvent2.add(runEntryObj5);

        RunEntry runEntryObj6 = new RunEntry(new Competitor("Tea Greaves", 41), 10);
        RunEntryEvent2.add(runEntryObj6);

        RunEntry runEntryObj7 = new RunEntry(new Competitor("Roma Morton", 15), 109);
        RunEntryEvent3.add(runEntryObj7);

        RunEntry runEntryObj8 = new RunEntry(new Competitor("Sallie Ellwood", 12), 110);
        RunEntryEvent3.add(runEntryObj8);

        RunEntry runEntryObj9 = new RunEntry(new Competitor("Heena Oakley", 53), 111);
        RunEntryEvent3.add(runEntryObj9);

    }

    public void loadHalfMarathon() {

        ArrayList<RunEntry> RunEntryEvent4 = new ArrayList<>();
        ArrayList<RunEntry> RunEntryEvent5 = new ArrayList<>();
        ArrayList<RunEntry> RunEntryEvent6 = new ArrayList<>();
        ArrayList<RunEntry> RunEntryEvent8 = new ArrayList<>();

        //Load town, date, time and number of water stations
        HalfMarathon halfMarathonObj1 = new HalfMarathon(new Town("London"), "02/05/2021", "10:00", 6, RunEntryEvent4);
        halfMarathon.add(halfMarathonObj1);
        HalfMarathon halfMarathonObj2 = new HalfMarathon(new Town("Manchester"), "09/05/2021", "10:00", 4, RunEntryEvent5);
        halfMarathon.add(halfMarathonObj2);
        HalfMarathon halfMarathonObj3 = new HalfMarathon(new Town("Liverpool"), "16/05/2021", "10:00", 2, RunEntryEvent6);
        halfMarathon.add(halfMarathonObj3);
        HalfMarathon halfMarathonObj4 = new HalfMarathon(new Town("Battersea Park"), "23/05/2021", "10:00", 8, RunEntryEvent8);
        halfMarathon.add(halfMarathonObj4);

        //Half Marathon
        RunEntry runEntryObj10 = new RunEntry(new Competitor("Liya Alford", 18), 1);
        RunEntryEvent4.add(runEntryObj10);

        RunEntry runEntryObj11 = new RunEntry(new Competitor("Corbin Nixon", 33), 2);
        RunEntryEvent4.add(runEntryObj11);

        RunEntry runEntryObj12 = new RunEntry(new Competitor("Lawrence Pearson", 21), 3);
        RunEntryEvent4.add(runEntryObj12);

        RunEntry runEntryObj13 = new RunEntry(new Competitor("Harlen Drake", 53), 569);
        RunEntryEvent5.add(runEntryObj13);

        RunEntry runEntryObj14 = new RunEntry(new Competitor("Jesus Pugh", 32), 570);
        RunEntryEvent5.add(runEntryObj14);

        RunEntry runEntryObj15 = new RunEntry(new Competitor("Phoenix Gilmore", 44), 571);
        RunEntryEvent5.add(runEntryObj15);

        RunEntry runEntryObj16 = new RunEntry(new Competitor("Braydon Brady", 38), 652);
        RunEntryEvent6.add(runEntryObj16);

        RunEntry runEntryObj17 = new RunEntry(new Competitor("Ayub Casey", 31), 653);
        RunEntryEvent6.add(runEntryObj17);

        RunEntry runEntryObj18 = new RunEntry(new Competitor("Danyaal Harwood", 26), 654);
        RunEntryEvent6.add(runEntryObj18);
        
        RunEntry runEntryObj19 = new RunEntry(new Competitor("Annalise Nixon", 25), 655);
        RunEntryEvent6.add(runEntryObj19);

    }

    public ArrayList<FiveKmRun> getFiveKmRuns() {

        return fiveKmRun;

    }

    public ArrayList<HalfMarathon> getHalfMarathons() {

        return halfMarathon;

    }

}
