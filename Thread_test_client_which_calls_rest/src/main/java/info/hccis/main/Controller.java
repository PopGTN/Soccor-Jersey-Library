package info.hccis.main;

import com.google.gson.Gson;
import info.hccis.main.threads.ConsoleRunnable;
import info.hccis.main.threads.GuiThread;
import info.hccis.model.jpa.SoccorJerseys;
import info.hccis.student.util.CisUtility;
import info.hccis.student.util.UtilityRest;
import java.util.Scanner;
import org.json.JSONArray;

public class Controller {
    
    private static final CisUtility cisUtility = new CisUtility();


    public static void main(String[] args) {
        GuiThread guiThread = new GuiThread();
        guiThread.start();
        System.out.println("Continuing to run");

        ConsoleRunnable consoleRunnable = new ConsoleRunnable();
        Thread consoleThread = new Thread(consoleRunnable);
        consoleThread.start();
    }

}
