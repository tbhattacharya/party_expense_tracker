package com.expense.tamal.expenseshare.pojo;

import java.io.Serializable;

/**
 * Created by tamal on 29/09/2016.
 */
public class Event implements Serializable {
    private String eventName;
    private long eventTimeStamp;
    private String imageUrl;

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public long getEventTimeStamp() {
        return eventTimeStamp;
    }

    public void setEventTimeStamp(long eventTimeStamp) {
        this.eventTimeStamp = eventTimeStamp;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
