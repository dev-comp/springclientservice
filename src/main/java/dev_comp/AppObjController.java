package dev_comp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Created by diver_000 on 10.09.2016.
 *
 */

@RestController
@CrossOrigin()
public class AppObjController {

    private static String HELLO = "Hello, %s";

    LinkedHashSet<UserInfo> users = new LinkedHashSet<>();

    /*
    @RequestMapping("/hey")
    public LinkedHashSet<UserInfo> getAppObj() {
        if (users.size() == 0) {
            users.add(new UserInfo((users.size() + 1), "Name_1", "Messenger_1"));
            users.add(new UserInfo((users.size() + 1), "Name_2", "Messenger_2"));
        }
        return users;
    }

    @RequestMapping("/adduser")
    public LinkedHashSet<UserInfo> getAppObj(@RequestParam(value="name", required=false, defaultValue="World") String name,
                                @RequestParam(value="messengerName", required=false, defaultValue="Default") String messengerName) {
        users.add(new UserInfo((users.size() + 1), name, messengerName));
        return users;
    }
*/



    @RequestMapping("/userlist")
    public ArrayList<UserObject> getAppObj() {
        return getUsersFromService();
    }

    private ArrayList<UserObject> getUsersFromService() {
        URL url;
        HttpURLConnection conn;
        ArrayList<UserObject> items = null;
        boolean isCool = true;
        try {
            url = new URL("http://172.21.21.249:8080/botservice/rs/api/userKeyList/client_name");
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader buff = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            Gson gson = new GsonBuilder().create();
            Type itemsArrType = new TypeToken<ArrayList<UserObject>>() {}.getType();
            items = gson.fromJson(buff, itemsArrType);
            buff.close();
        } catch (ConnectException e) {
           //throw new RuntimeException("Ошибка соединения с сервисом");
            isCool = false;
        } catch (Exception e) {
           // e.printStackTrace();
            isCool = false;
        }
        if (!isCool) {
            items = new ArrayList<>();
            items.add(new UserObject("USER_1", "BOT_1"));
            items.add(new UserObject("USER_2", "BOT_2"));
        }
        return items;
    }

    @RequestMapping(value = "/sendMessage", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void sendMessage(@RequestBody List<MsgObject> messItems) {

        try {
            URL url = new URL("http://172.21.21.249:8080/botservice/rs/api/sendMsg");
            try {
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        boolean isOk = true;
        for (MsgObject mi : messItems) {
            if (!sendMessage(mi)) {
                isOk = false;
            }
        }
    }

    private boolean sendMessage(MsgObject messObj) {

        try {
            URL url = new URL("http://172.21.21.249:8080/botservice/rs/api/sendMsg");
            try {
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setUseCaches(false);
                conn.setDoOutput(true);
                conn.setRequestProperty("charset", "utf-8");
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.connect();

                Gson gson = new GsonBuilder().create();
                OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
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

}
