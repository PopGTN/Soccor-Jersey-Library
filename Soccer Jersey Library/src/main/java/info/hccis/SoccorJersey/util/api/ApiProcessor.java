package info.hccis.SoccorJersey.util.api;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @author bjmaclean
 * @since Nov 4, 2021
 *
 * Modified by Joshua Mckenna
 * since 2023/11/06
 */
public class ApiProcessor {

    public static final String URL = "https://v2.jokeapi.dev/joke/";
    public static final String BLACKLISTED = "?blacklistFlags=nsfw,religious,political,racist,sexist,explicit";


    /**
     * @param topic  the chosen topic
     * @param amount how many jokes do u want between 1 and 10
     * @author Joshua Mckenna
     * @since 2023/11/06
     */
    public static String getJokeByTopic(String topic, int amount) {
        if (!(amount < 11 && amount > 0)) {
            amount = 1;
        }
        String json;
        String input = "";
        String output = "";
        input += getTopic(topic);
        input += BLACKLISTED;
        if (1 < amount) {
            input += "&amount=" + amount;
        }

        try {

//            String encoded = URLEncoder.encode(input, StandardCharsets.UTF_8);
            String encoded = input;
//            System.out.println(encoded);
            json = UtilityRest.getJsonFromRest(URL + encoded);
//            System.out.println("json returned=" + json);
            JSONObject jsonObjectOuter = new JSONObject(json);
            return proccessJokes(jsonObjectOuter, amount);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";

    }

    /**
     * @param topic  the chosen topic
     * @param word   the word you want the jokes to contain
     * @param amount how many jokes do u want between 1 and 10
     * @author Joshua Mckenna
     * @since 2023/11/06
     */
    public static String findJokeContaining(String topic, String word, int amount) {
        if (!(amount < 11 && amount > 0)) {
            amount = 1;
        }
        String json;
        String input = "";
        String output = "";
        input += getTopic(topic);
        input += BLACKLISTED;
        //String encoded = URLEncoder.encode(word, StandardCharsets.UTF_8);
        String encoded = URLEncoder.encode(word);
        input += "&contains=" + encoded;
        if (1 < amount){
            input += "&amount=" + amount;
        }
        try {

            System.out.println(encoded);

            json = UtilityRest.getJsonFromRest(URL + input);
//            System.out.println("json returned=" + json);
            JSONObject jsonObjectOuter = new JSONObject(json);
            return proccessJokes(jsonObjectOuter, amount);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";

    }

    //this gets the topics
    private static String getTopic(String topic) {
        switch (topic.toLowerCase()) {
            case "programming":
                return "Programming";
            case "misc":
                return "Misc";
            case "dark":
                return "Dark";
            case "pun":
                return "Pun";
            case "any":
            default:
                return "Any";
            case "spooky":
                return "Spooky";
            case "christmas":
                return "Christmas";

        }
    }

    //This proccess the results of the json
    private static String proccessJokes(JSONObject jsonObject, int amount) throws Exception {
        String output = "";
        if (!jsonObject.getBoolean("error")) {
            if (!(amount > 1)) {
                switch (jsonObject.getString("type")) {
                    case "single":
                        output += jsonObject.getString("Joke");
                        break;
                    case "twopart":
                        output += "Setup: \r\n" + jsonObject.getString("setup") + "\r\n";
                        output += "Delivery: \r\n" + jsonObject.getString("delivery");
                        break;

                }
                return output;
            } else {
                JSONArray jokes = jsonObject.getJSONArray("jokes");
                for (int i = 0; i < jokes.length(); i++) {
                    JSONObject joke = jokes.getJSONObject(i);

                    output += "\r\n\r\n Joke " + (i + 1) + ": \r\n";
                    output += "Category: " + joke.getString("category") + "\r\n";

                    switch (joke.getString("type")) {
                        case "single":
                            output += joke.getString("Joke");
                            break;
                        case "twopart":
                            output += "Setup: \r\n" + joke.getString("setup") + "\r\n";
                            output += "Delivery: \r\n" + joke.getString("delivery");
                            break;
                    }

                }
                return output;
            }
        } else {
            return "Error - " + jsonObject.getInt("code") + " " + jsonObject.getString("message");
        }
    }
}
