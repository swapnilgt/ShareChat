package com.example.swapnilgupta.sharechat.retrofit.models;

import com.example.swapnilgupta.sharechat.models.FeedItem;

/**
 * Created by swapnilgupta on 04/09/2017.
 */

public class FeedItemUpdateReq {

    private String type;
    private int id;
    private String author_name;

    private String author_contact;
    private String author_dob;
    private String author_status;
    private String author_gender;
    private String profile_url;

    public FeedItemUpdateReq(FeedItem i) {
        type = i.getType();
        id = i.getId();
        author_name = i.getAuthor_name();
        author_contact = i.getAuthor_contact();
        author_dob = i.getAuthor_dob();
        author_status = i.getAuthor_status();
        author_gender = i.getAuthor_gender();
        profile_url = i.getProfile_url();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getAuthor_contact() {
        return author_contact;
    }

    public void setAuthor_contact(String author_contact) {
        this.author_contact = author_contact;
    }

    public String getAuthor_dob() {
        return author_dob;
    }

    public void setAuthor_dob(String author_dob) {
        this.author_dob = author_dob;
    }

    public String getAuthor_status() {
        return author_status;
    }

    public void setAuthor_status(String author_status) {
        this.author_status = author_status;
    }

    public String getAuthor_gender() {
        return author_gender;
    }

    public void setAuthor_gender(String author_gender) {
        this.author_gender = author_gender;
    }

    public String getProfile_url() {
        return profile_url;
    }

    public void setProfile_url(String profile_url) {
        this.profile_url = profile_url;
    }

    @Override
    public String toString() {
        return "FeedItemUpdateReq{" +
                "type='" + type + '\'' +
                ", id=" + id +
                ", author_name='" + author_name + '\'' +
                ", author_contact='" + author_contact + '\'' +
                ", author_dob='" + author_dob + '\'' +
                ", author_status='" + author_status + '\'' +
                ", author_gender='" + author_gender + '\'' +
                ", profile_url='" + profile_url + '\'' +
                '}';
    }
}
