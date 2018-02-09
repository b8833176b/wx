package com.gyw.wx.resume.service.entity;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by guoyouwei on 2018/2/5.
 */
@Entity
@Component
public class Resume {

    @Id
    private long id;

    private String type;

    private String content;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Resume{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
