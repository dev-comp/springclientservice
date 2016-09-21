package dev_comp;

/**
 * Created by a.kutakov on 20.09.2016.
 *
 */
public class UserObject {

  private String userName;
  private String botEntryName;

  public UserObject(String userName, String botEntryName) {
    this.userName = userName;
    this.botEntryName = botEntryName;
  }

  public String getUserName() {
    return userName;
  }

  public String getBotEntryName() {
    return botEntryName;
  }
}
