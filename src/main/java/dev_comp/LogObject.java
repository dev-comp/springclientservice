package dev_comp;

/**
 * Created by a.kutakov on 24.09.2016.
 *
 */
public class LogObject {

  private MsgObject msgObject;

  private Long msgTime;

  public MsgObject getMsgObject() {
    return msgObject;
  }

  public void setMsgObject(MsgObject msgObject) {
    this.msgObject = msgObject;
  }

  public Long getMsgTime() {
    return msgTime;
  }

  public void setMsgTime(Long msgTime) {
    this.msgTime = msgTime;
  }

}
