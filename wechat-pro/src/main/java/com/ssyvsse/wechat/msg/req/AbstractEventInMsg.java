package com.ssyvsse.wechat.msg.req;


public abstract class AbstractEventInMsg extends ReqBaseMsg {
    protected String event;

    public AbstractEventInMsg(String toUserName, String fromUserName, Integer createTime, String msgType, String event) {
        super(toUserName, fromUserName, createTime, msgType);
        this.event = event;
    }
    
    public String getEvent()
    {
        return event;
    }

    public void setEvent(String event)
    {
        this.event = event;
    }
}
