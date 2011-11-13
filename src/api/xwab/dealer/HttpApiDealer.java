/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package api.xwab.dealer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author manyrus
 */
public class HttpApiDealer {

    public static String URL = "http://xwab.ru/forum/api.php";
    
    public static String COMMAND = "inf";
    
    public static String USER_ID = "id";
    public static String USER_PASS = "pass";
    public static String USERS_ONLINE = "online";
    public static String POSTS_COUNT = "all_messages";

    protected String execCommand(Map<String, String> parameters){
        String stringParameters = "?", result = "";
        for(Map.Entry<String, String> parameter : parameters.entrySet()) {
            stringParameters += parameter.getKey()+"="+parameter.getValue()+"&";
        }
        try {
            URLConnection conn = new URL(URL+stringParameters).openConnection();
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            rd.close();
            result = sb.toString();
        } catch(MalformedURLException e) {
            Logger.getLogger(HttpApiDealer.class.getName()).log(Level.SEVERE, null, e);
        } catch(IOException e) {
            Logger.getLogger(HttpApiDealer.class.getName()).log(Level.SEVERE, null, e);
        }

        return result;
    }

    protected String getOnlineUsers() {
        HashMap<String, String> parameters = new HashMap<String, String>();
        parameters.put(HttpApiDealer.COMMAND, HttpApiDealer.USERS_ONLINE);
        return this.execCommand(parameters);
    }
    
    protected String getNewPostsCountResponse(int id, String pass) {
        HashMap<String, String> parameters = new HashMap<String, String>();
        parameters.put(HttpApiDealer.USER_ID, new Integer(id).toString());
        parameters.put(HttpApiDealer.USER_PASS, pass);
        return this.execCommand(parameters);
    }
    
    protected String getPostsCount() {
        HashMap<String, String> parameters = new HashMap<String, String>();
        parameters.put(HttpApiDealer.COMMAND, HttpApiDealer.POSTS_COUNT);
        return this.execCommand(parameters);
    }
}
