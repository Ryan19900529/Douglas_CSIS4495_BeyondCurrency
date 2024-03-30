package com.example.beyondcurrency.models;

import java.util.Date;

public class RequestCardModel {
    private String serviceTitle;
    private String imageUrl;
    private int posterId;
    private String skill1;
    private String skill2;
    private String skill3;
    private String posterFirstName;
    private String posterLastName;
    private String posterImageUrl;
    private String fullName;


    public RequestCardModel(String serviceTitle, String imageUrl, int posterId, String skill1, String skill2, String skill3, String posterFirstName, String posterLastName, String posterImageUrl) {
        this.serviceTitle = serviceTitle;
        this.imageUrl = imageUrl;
        this.posterId = posterId;
        this.skill1 = skill1;
        this.skill2 = skill2;
        this.skill3 = skill3;
        this.posterFirstName = posterFirstName;
        this.posterLastName = posterLastName;
        this.posterImageUrl = posterImageUrl;
        this.fullName = this.posterFirstName + " " + this.posterLastName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPosterImageUrl() {
        return posterImageUrl;
    }

    public void setPosterImageUrl(String posterImageUrl) {
        this.posterImageUrl = posterImageUrl;
    }

    public String getServiceTitle() {
        return serviceTitle;
    }

    public void setServiceTitle(String serviceTitle) {
        this.serviceTitle = serviceTitle;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getPosterId() {
        return posterId;
    }

    public void setPosterId(int posterId) {
        this.posterId = posterId;
    }

    public String getSkill1() {
        return skill1;
    }

    public void setSkill1(String skill1) {
        this.skill1 = skill1;
    }

    public String getSkill2() {
        return skill2;
    }

    public void setSkill2(String skill2) {
        this.skill2 = skill2;
    }

    public String getSkill3() {
        return skill3;
    }

    public void setSkill3(String skill3) {
        this.skill3 = skill3;
    }

    public String getPosterFirstName() {
        return posterFirstName;
    }

    public void setPosterFirstName(String posterFirstName) {
        this.posterFirstName = posterFirstName;
    }

    public String getPosterLastName() {
        return posterLastName;
    }

    public void setPosterLastName(String posterLastName) {
        this.posterLastName = posterLastName;
    }

    @Override
    public String toString() {
        return "RequestCardModel{" +
                "serviceTitle='" + serviceTitle + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", posterId=" + posterId +
                ", skill1='" + skill1 + '\'' +
                ", skill2='" + skill2 + '\'' +
                ", skill3='" + skill3 + '\'' +
                ", posterFirstName='" + posterFirstName + '\'' +
                ", posterLastName='" + posterLastName + '\'' +
                '}';
    }
}
