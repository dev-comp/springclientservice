package dev_comp;

/**
 * Created by a.kutakov on 21.09.2016.
 *
 */
public class UserObjectToClient {
  private long id;
  private String userName;

  private String botName;

  public UserObjectToClient(long id, String userName, String botName) {
    this.id = id;
    this.userName = userName;
    this.botName = botName;
  }

  public UserObjectToClient() {
  }

  public long getId() {
    return id;
  }

  public String getUserName() {
    return userName;
  }

  public void setId(long id) {
    this.id = id;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getBotName() {
    return botName;
  }

  public void setBotName(String botName) {
    this.botName = botName;
  }
}
