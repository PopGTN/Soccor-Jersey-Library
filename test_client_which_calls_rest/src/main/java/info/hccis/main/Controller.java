package info.hccis.main;

import com.google.gson.Gson;
import info.hccis.model.jpa.SoccorJerseys;
import info.hccis.student.util.CisUtility;
import info.hccis.student.util.UtilityRest;
import java.util.Scanner;
import org.json.JSONArray;

public class Controller {

    final public static String MENU = "\nMain Menu \nA) Add\n"
            + "U) Update\n"
            + "V) View\n"
            + "D) Delete\n"
            + "X) eXit";
    final static Scanner input = new Scanner(System.in);
    private static final String URL_STRING = "http://localhost:8080/api/SoccorJerseyService/";

    public static void main(String[] args) {
        boolean endProgram = false;
        do {
            System.out.println(MENU);
            String choice = input.nextLine();
            SoccorJerseys sj;
            String url;
            switch (choice.toUpperCase()) {
                case "A":
                    sj = create();
                    url = URL_STRING;
                    System.out.println("Url=" + url);
                    SoccorJerseys soccorJerseys = (SoccorJerseys) UtilityRest.addUsingRest(url, sj);
                    System.out.println("Added new entity:"+soccorJerseys.toString());
                    break;
                case "U":
                    int id2 = CisUtility.getInputInt("Enter id you want to edit");
                    sj = create();
                    sj.setId(id2);
                    url = URL_STRING;
                    System.out.println("Url="+url);
                    UtilityRest.updateUsingRest(url, sj);
                    break;
                case "D":
                    int id = CisUtility.getInputInt("Enter id to delete");
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
                    System.out.println("Here are the rows");
                    Gson gson = new Gson();
                    for (int currentIndex = 0; currentIndex < jsonArray.length(); currentIndex++) {
                        SoccorJerseys current = gson.fromJson(jsonArray.getJSONObject(currentIndex).toString(), SoccorJerseys.class);
                        System.out.println(current.toString());
                    }
                    break;

                case "X":
                    endProgram = true;
                    break;
                default:
                    System.out.println("INVALID OPTION");
            }
        } while (!endProgram);
    }

    /**
     * Create an object by passing asking user for input.
     *
     * @return object
     * @since 20171117
     * @author BJM
     */
    public static SoccorJerseys create() {
        SoccorJerseys sj = new SoccorJerseys();
        sj.getInformation();
        return sj;
    }

}
