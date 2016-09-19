package dev_comp;

import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashSet;

/**
 * Created by diver_000 on 10.09.2016.
 *
 */

@RestController
@CrossOrigin()
public class AppObjController {

    private static String HELLO = "Hello, %s";

    LinkedHashSet<UserInfo> users = new LinkedHashSet<>();

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
}
