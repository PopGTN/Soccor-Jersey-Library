package info.hccis.main.threads;

import java.util.HashSet;

public class JerseySummary {
    private volatile static HashSet<String> soccorTeams = new HashSet<String>();
    public static synchronized void addTeamToSummary(String Team){
        soccorTeams.add(Team);
    }

    public static String getTeamSummmary(){
        String output = "Summary Of Added Teams \n\r";
        for (String team: soccorTeams) {
            output += " - " + team + "\n\r";
        }
        return output;
    }
}
