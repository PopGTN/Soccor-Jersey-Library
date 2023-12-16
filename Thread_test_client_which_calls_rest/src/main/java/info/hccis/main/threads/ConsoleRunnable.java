package info.hccis.main.threads;

import com.google.gson.Gson;
import info.hccis.model.jpa.SoccorJerseys;
import info.hccis.student.util.CisUtility;
import info.hccis.student.util.UtilityRest;
import org.json.JSONArray;

import java.util.Scanner;

public class ConsoleRunnable implements Runnable{

    private  final CisUtility cisUtility = new CisUtility();

    final public  String MENU = "\nMain Menu \nA) Add\n"
            + "U) Update\n"
            + "V) View\n"
            + "D) Delete\n"
            + "X) eXit";
    final  Scanner input = new Scanner(System.in);
    private  final String URL_STRING = "http://localhost:8080/api/SoccorJerseyService/";

    @Override
    public void run() {
        boolean endProgram = false;
        do {
            cisUtility.display(MENU);
            String choice = input.nextLine();
            SoccorJerseys sj;
            String url;
            switch (choice.toUpperCase()) {
                case "A":
                    sj = this.create();
                    url = URL_STRING;
                    cisUtility.display("Url=" + url);
                    SoccorJerseys soccorJerseys = (SoccorJerseys) UtilityRest.addUsingRest(url, sj);
                    cisUtility.display("Added new entity:"+soccorJerseys.toString());
                    String team = sj.getNameClub();
                    JerseySummary.addTeamToSummary(team);
                    cisUtility.display(JerseySummary.getTeamSummmary());
                    break;
                case "U":
                    int id2 = cisUtility.getInputInt("Enter id you want to edit");
                    sj = this.create();
                    sj.setId(id2);
                    url = URL_STRING;
                    cisUtility.display("Url="+url);
                    UtilityRest.updateUsingRest(url, sj);
                    break;
        /*        volatile
                synchronized*/
                case "D":
                    int id = cisUtility.getInputInt("Enter id to delete");
                    UtilityRest.deleteUsingRest(URL_STRING, id);
                    break;
                case "V":
                    String jsonReturned = UtilityRest.getJsonFromRest(URL_STRING);
                    //**************************************************************
                    //Based on the json string passed back, loop through each json
                    //object which is a json string in an array of json strings.
                    //*************************************************************
                    JSONArray jsonArray = new JSONArray(jsonReturned);
                    //**************************************************************
                    //For each json object in the array, show the first and last names
                    //**************************************************************
                    cisUtility.display("Here are the rows");
                    Gson gson = new Gson();
                    for (int currentIndex = 0; currentIndex < jsonArray.length(); currentIndex++) {
                        SoccorJerseys current = gson.fromJson(jsonArray.getJSONObject(currentIndex).toString(), SoccorJerseys.class);
                        cisUtility.display(current.toString());
                    }
                    break;

                case "X":
                    endProgram = true;
                    break;
                default:
                    cisUtility.display("INVALID OPTION");
            }
        } while (!endProgram);
    }
    /**
     * Create an object by passing asking user for input.
     *
     * @return object
     * @since 20171117
     * @author BJM
     *
     * @modified  Joshua Mckenna
     * @since 20233011
     */
    public SoccorJerseys create() {
        SoccorJerseys sj = new SoccorJerseys();
        sj.getInformation();
        return sj;
    }
}
