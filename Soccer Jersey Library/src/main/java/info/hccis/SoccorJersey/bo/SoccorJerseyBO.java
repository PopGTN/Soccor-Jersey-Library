package info.hccis.SoccorJersey.bo;

import info.hccis.SoccorJersey.jpa.entity.SoccorJerseys;
import info.hccis.SoccorJersey.repositories.SoccorJerseysRepositories;
import info.hccis.SoccorJersey.util.CisUtility;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Business functionality related to squash skills.
 *
 * @author bjmaclean
 * @since 20231019+
 *
 * @modified by Joshua Mckenna
 * @since 20231020
 */

public class SoccorJerseyBO {

    private static Path relative;
    private static SoccorJerseys _sj = new SoccorJerseys();

    public SoccorJerseyBO(){

    }


    public static void writeToFile(String reportName, ArrayList theObjects) {

        String datetime = CisUtility.getCurrentDate("yyyyMMddhhmmss");
        String fileName = reportName.replaceAll(" ", "");
        relative = Paths.get("c:/cis2232/" + fileName+datetime+".txt");

        try {
            Files.createFile(relative);
            System.out.println("JM writeToFile Created the file. " + relative);
        } catch (IOException ex) {
            System.out.println("JM writeToFile Could not create the files." + ex.getMessage());
            ex.getStackTrace();
        } catch (Exception e) {
            System.out.println("JM writeToFile Exception was caught: " + e.getMessage());
        }

        writeToFile(reportName+" "+datetime);



        if(theObjects == null ||theObjects.isEmpty()){
            writeToFile("no data found");
        }

        for (Object current : theObjects) {
            writeToFile(current.toString());
        }
    }

    public static void writeToFile(String line) {
        try {
            FileWriter myWriter = new FileWriter(relative.toString(), true);
            myWriter.write(line + System.lineSeparator());
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
    public static HashSet<SoccorJerseys> loadAssessmentsForSoccorJersey(SoccorJerseysRepositories sjr, SoccorJerseys sj) {

        List<SoccorJerseys> theListCountryName = new ArrayList();
        List<SoccorJerseys> theListPlayerNames = new ArrayList();


        theListCountryName = sjr.findByNameCountry(sj.getNameCountry());
        theListPlayerNames = sjr.findByNamePlayer(sj.getNamePlayer());

        HashSet<SoccorJerseys> theSet = new HashSet();

        for (SoccorJerseys soccorJerseys : theListCountryName) {
            if (theListPlayerNames.contains(soccorJerseys)) {
                theSet.add(soccorJerseys);
            }
        }

        return theSet;

    }

/*    public static HashSet<SoccorJerseys> loadAssessmentsForSoccorJersey(SoccorJerseysRepositories sjr, String searchTerm) {

        List<SoccorJerseys> theListCountryName = new ArrayList();
        List<SoccorJerseys> theListPlayerNames = new ArrayList();


        theListCountryName = sjr.findByNameCountry(searchTerm);
        theListPlayerNames = sjr.findByNamePlayer(searchTerm);


        HashSet<SoccorJerseys> theSet = new HashSet();

        for (SoccorJerseys soccorJerseys : theListCountryName) {
            if (theListPlayerNames.contains(soccorJerseys)) {
                theSet.add(soccorJerseys);
            }
        }

        return theSet;

    }*/

}
