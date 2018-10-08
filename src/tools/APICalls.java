package tools;

import tools.network.HttpCallBack;
import tools.network.HttpClient;

import java.io.IOException;

public class APICalls {

    public static void insertLevel(HttpCallBack listener, String name, String map){

        String post = "request=insert-level" + "&";

        post += "name=" + name +"&";
        post += "map=" + map;

        try {
            HttpClient.getInstance().sendPOST(post);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
