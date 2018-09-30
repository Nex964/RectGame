package tools.network;

import jdk.nashorn.internal.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpClient {

    //Singlton Stuff
    private static HttpClient instance;

    public static HttpClient getInstance(){
        if(instance == null)
            instance = new HttpClient();
        return instance;
    }

    private String url = "https://rect-game.herokuapp.com/";
    private final String USER_AGENT = "Mozilla/5.0";

    public void sendGet(){
        try {
            URL urlObj = new URL(url);

            HttpURLConnection client = (HttpURLConnection) urlObj.openConnection();
            client.setRequestMethod("GET");
            client.setRequestProperty("User-Agent", USER_AGENT);

            int responseCode = client.getResponseCode();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(client.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            System.out.println(response.toString());

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to create URL OBJECT");
        }
    }

    public void sendPost(){

    }

}
