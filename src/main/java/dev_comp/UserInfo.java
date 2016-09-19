package dev_comp;

/**
 * Created by diver_000 on 10.09.2016.
 */
public class UserInfo {
    private long id;
    private String userName;
    private String messengerName;

    public UserInfo(long id, String userName, String messengerName) {
        this.id = id;
        this.userName = userName;
        this.messengerName = messengerName;
    }

    public long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getMessengerName() {
        return messengerName;
    }
}
