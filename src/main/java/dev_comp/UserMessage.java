package dev_comp;

/**
 * Created by a.kutakov on 21.09.2016.
 *
 */
public class UserMessage {

  private long[] userIds;
  private String msgBody;

  /*public UserMessage(long[] userIds, String msgBody) {
    this.userIds = userIds;
    this.msgBody = msgBody;
  }*/

  public long[] getUserIds() {
    return userIds;
  }

  public String getMsgBody() {
    return msgBody;
  }

  public void setUserIds(long[] userIds) {
    this.userIds = userIds;
  }

  public void setMsgBody(String msgBody) {
    this.msgBody = msgBody;
  }
}
