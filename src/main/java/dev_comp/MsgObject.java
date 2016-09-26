package dev_comp;

/**
 * Created by a.kutakov on 20.09.2016.
 *
 */

public class MsgObject {
  private UserObject userObject;
  private String msgBody;
  private String directionType;

  public UserObject getUserObject() {
    return userObject;
  }

  public void setUserObject(UserObject userObject) {
    this.userObject = userObject;
  }

  public String getMsgBody() {
    return msgBody;
  }

  public void setMsgBody(String msgBody) {
    this.msgBody = msgBody;
  }

  public String getDirectionType() {
    return directionType;
  }

  public void setDirectionType(String directionType) {
    this.directionType = directionType;
  }

}
