/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package ca.joshuamc.jerseysoapapp;

import info.hccis.soccorjersey.soap.SoccorJerseyLibrary;
import info.hccis.soccorjersey.soap.SoccorJerseyLibraryImplService;
import info.hccis.soccorjersey.soap.SoccorJerseys;
import java.util.List;
import java.util.Scanner;
import ca.joshuamc.util.CisUtility;
import java.util.ArrayList;

/**
 *
 * @author jmckenna124706
 */
public class JerseySoapApp {

    public static void main(String[] args) { 
        
       System.out.println("Hello World!");
       SoccorJerseyLibraryImplService sjls = new SoccorJerseyLibraryImplService() {};
       SoccorJerseyLibrary soccorJerseyLibrary = sjls.getSoccorJerseyLibraryImplPort();
       
       System.out.println("There are " + soccorJerseyLibrary.getCount() + " rows in the database");
       String option = "";
        do {
            Scanner input = new Scanner(System.in);
            option = CisUtility.getInputString("A country name to find a view a Jersesy or -1 to exit");
            if (!option.equals("-1")) {
                try {
                    List<SoccorJerseys> sjResults = soccorJerseyLibrary.getByCountry(option);
                    if (sjResults == null) {
                        System.out.println("Not found");
                    } else {
                        System.out.println("------------------------Results------------------------");
                        for (int i = 0; i < sjResults.size(); i++) {
                            System.out.println("------------------------");
                            System.out.println(sjResults.get(i).toString());
                            System.out.println("------------------------");
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Something when wrong. Please try again");
                }
            }

        } while (!option.equalsIgnoreCase("-1"));
       
    }
}
