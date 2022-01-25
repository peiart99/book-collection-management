package com.peiart99.main;

import com.peiart99.enums.Topic;
import com.peiart99.interfaces.ClassName;

public class Educational extends Book implements ClassName {

    private Topic topic;

    public Educational(String name, String author, String publisher, int volume, Topic topic) {
        super(name, author, publisher, volume);
        this.topic = topic;
    }

    public Topic getTopic() {
        return topic;
    }

    @Override
    public String getClassName() {
        return "Educational";
    }
}
