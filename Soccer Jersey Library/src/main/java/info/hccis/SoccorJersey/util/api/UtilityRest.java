package info.hccis.SoccorJersey.util.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author bjmaclean
 * @since Nov 17, 2017
 */
public class UtilityRest {

    /**
     * This method will call the rest web service and give back the json
     *
     * @since 20171117
     * @author BJM
     */
    public static String getJsonFromRest(String urlString) throws IOException {

        String content = "";

        URLConnection conn = null;
        conn = new URL(urlString).openConnection();
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
        conn.connect();

        BufferedReader in = null;
        in = new BufferedReader(new InputStreamReader(
                conn.getInputStream()));
        String inputLine;
        String output = "";
        while ((inputLine = in.readLine()) != null) {
           // System.out.println(inputLine);
            output += inputLine;
        }
        return output;

    }

    public static String getJsonFromRest2(String urlString) throws IOException {

        String content = "";

        URLConnection conn = null;
        conn = new URL(urlString).openConnection();
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
        conn.connect();

        BufferedReader in = null;
        in = new BufferedReader(new InputStreamReader(
                conn.getInputStream()));
        String inputLine;
        String output = "";
        while ((inputLine = in.readLine()) != null) {
            // System.out.println(inputLine);
            output += inputLine;
        }
        return output;

    }

}
