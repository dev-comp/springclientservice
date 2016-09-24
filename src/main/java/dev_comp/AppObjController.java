package dev_comp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.lang.reflect.Type;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.util.Random;

/**
 * Created by a.kutakov on 10.09.2016.
 *
 */

@RestController
@CrossOrigin()
public class AppObjController {

    public static final String CMD_JOKE = "/JOKE";
    public static String GET_USERS_FROM_MAIN_SERVICE_URL = "get_users_from_main_service_url";
    public static String SEND_MESSAGE_TO_MAIN_SERVICE_URL = "send_message_to_main_service_url";
    public static String GET_JOKES_FROM_FOREIGN_SERVICE = "get_jokes_from_foreign_service";

    public static final String UTF_8 = "Utf-8";
    public static int JOKE_COUNT_IN_PAGE = 10;

    private static HashMap<String, String> mapUrls = new HashMap<>();
    private JokeObject jokes;
    private int jokeCount = 0;


    public static final String APP_SETTINGS_JSON_FILE_PATH = "/static/app_settings.json";

    static {
        String proxy = "proxy.bftcom.com";
        String port = "8080";
        Properties systemProperties = System.getProperties();
        systemProperties.setProperty("http.proxyHost", proxy);
        systemProperties.setProperty("http.proxyPort", port);

        BufferedReader buff = null;
        try {
            buff = new BufferedReader(
                new InputStreamReader(Application.class.getResourceAsStream(APP_SETTINGS_JSON_FILE_PATH), UTF_8)
            );
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Gson g = new GsonBuilder().create();
        Type itemsArrType = new TypeToken<HashMap<String, String>>() {}.getType();
        if (buff != null) {
            mapUrls = g.fromJson(buff, itemsArrType);
        }
    }

    HashMap<Long, UserObjectToClient> users = new HashMap<>();

    @RequestMapping("/userlist")
    public ArrayList<UserObjectToClient> getAppObj() {
        long num = 0;

        ArrayList<UserObjectToClient> usrList = getUsersFromService();
        if (usrList != null) {
            for (UserObjectToClient obj : usrList) {
                num++;
                users.put(num, obj);
                obj.setId(num);
            }
        }
        return usrList;
    }

    @RequestMapping(value = "/msg", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public MsgObject msg(@RequestBody MsgObject msg) {
        if (CMD_JOKE.equals(msg.getMsgBody().trim().toUpperCase())) {
            msg.setMsgBody(getJoke());
        }
        msg.setMsgBody("Bot answer: \n" + msg.getMsgBody());
        return msg;
    }

    @RequestMapping(value = "/sendMessage", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean sendMessage(@RequestBody UserMessage obj) {
        boolean isOkAll = true;
        for (long id : obj.getUserIds()) {
            MsgObject mo = new MsgObject();
            UserObjectToClient uot = users.get(id);
            UserObject uo = new UserObject();
            uo.setBotName(uot.getBotName());
            uo.setUserName(uot.getUserName());
            mo.setUserObject(uo);
            mo.setMsgBody(obj.getMsgBody());
            isOkAll = isOkAll && sendMessageToService(mo);
        }
        return isOkAll;
    }

    private boolean sendMessageToService(MsgObject messObj) {
        try {
            URL url = new URL(mapUrls.get(SEND_MESSAGE_TO_MAIN_SERVICE_URL));
            try {
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setDoOutput(true);
                conn.setRequestProperty("Accept-Charset", UTF_8);
                conn.setRequestProperty("Content-Type", "application/json; charset=" + UTF_8);
                    conn.setRequestMethod("POST");

                Gson gson = new GsonBuilder().create();
                OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), UTF_8);
                out.write(gson.toJson(messObj));
                out.close();

                int HttpResult = conn.getResponseCode();
                return HttpResult == HttpURLConnection.HTTP_OK;

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return false;
    }


    private ArrayList<UserObjectToClient> getUsersFromService() {
        URL url;
        HttpURLConnection conn;
        ArrayList<UserObjectToClient> items = null;
        try {
            url = new URL(mapUrls.get(GET_USERS_FROM_MAIN_SERVICE_URL));
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader buff = new BufferedReader(new InputStreamReader(conn.getInputStream(), UTF_8));
            Gson gson = new GsonBuilder().create();
            Type itemsArrType = new TypeToken<ArrayList<UserObjectToClient>>() {}.getType();

            /*StringBuilder sb = new StringBuilder("");
            String line;
            try {
                while ((line = buff.readLine()) != null) {
                    sb.append(line).append("\n");
                }
            } catch (IOException ex){
                System.out.println(ex);
            }*/

            items = gson.fromJson(buff, itemsArrType);
            buff.close();
        } catch (ConnectException e) {
            throw new RuntimeException("Connection Error");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return items;
    }


    @RequestMapping("/userKeyLog")
    public String userKeyLog(@RequestParam(value="id") Long id) {
        UserObjectToClient uot = users.get(id);
        UserObject uo = new UserObject();
        uo.setBotName(uot.getBotName());
        uo.setUserName(uot.getUserName());
        return getMessageFromService(uo);
    }



    private String getMessageFromService(UserObject userObj) {
        String res = "";
        try {
            URL url = new URL("http://172.21.21.249:8080/botservice/rs/api/userKeyLog");
            try {
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setDoOutput(true);
                conn.setDoInput(true);
                conn.setRequestProperty("Accept-Charset", UTF_8);
                conn.setRequestProperty("Content-Type", "application/json; charset=" + UTF_8);
                conn.setRequestMethod("POST");

                Gson gson = new GsonBuilder().create();
                OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), UTF_8);
                out.write(gson.toJson(userObj));
                out.close();

                BufferedReader buff = new BufferedReader(new InputStreamReader(conn.getInputStream(), UTF_8));
                //Type itemsArrType = new TypeToken<ArrayList<UserObjectToClient>>() {}.getType();

                StringBuilder sb = new StringBuilder("");
                String line;
                try {
                    while ((line = buff.readLine()) != null) {
                        sb.append(line).append("\n");
                    }
                } catch (IOException ex) {
                    System.out.println(ex);
                }


                res = gson.fromJson(sb.toString(), String.class);

                buff.close();
                int HttpResult = conn.getResponseCode();
                //HttpResult == HttpURLConnection.HTTP_OK;

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return res;
    }





    //////////---------------------------------

    private String getJoke() {
        if (jokeCount > 0 && jokeCount < JOKE_COUNT_IN_PAGE) {
            return jokes.getResults().get(jokeCount++).getContent();
        } else {
            //JokeObject items = null;
            URL url;
            HttpURLConnection conn;
            try {
                url = new URL(getJokeUrl());
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestProperty("Accept", "application/json");
                conn.connect();
                BufferedReader buff = new BufferedReader(new InputStreamReader(conn.getInputStream(), UTF_8));

                Gson gson = new GsonBuilder().create();
                //items = gson.fromJson(buff, JokeObject.class);
                jokes = gson.fromJson(buff, JokeObject.class);
                buff.close();
            } catch (ConnectException e) {
                throw new RuntimeException("Connection Error");
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (jokes != null) {
                Joke joke = jokes.getResults().get(jokeCount);
                jokeCount++;
                //Joke joke = items.getResults().get((new Random().nextInt(10)));
                return joke.getContent();
            }
            return "No Joke, sorry";
        }
    }

    private String getJokeUrl() {
        return mapUrls.get(GET_JOKES_FROM_FOREIGN_SERVICE).replace("{pageGen}", String.valueOf(new Random().nextInt(400) + 1));
    }

}
