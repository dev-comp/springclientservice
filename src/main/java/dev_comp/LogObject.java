package dev_comp;

import java.util.Date;

/**
 * Created by a.kutakov on 24.09.2016.
 *
 */
public class LogObject {

  private MsgObject msgObject;

  private Date msgTime;

  public MsgObject getMsgObject() {
    return msgObject;
  }

  public void setMsgObject(MsgObject msgObject) {
    this.msgObject = msgObject;
  }

  public Date getMsgTime() {
    return msgTime;
  }

  public void setMsgTime(Date msgTime) {
    this.msgTime = msgTime;
  }

}
