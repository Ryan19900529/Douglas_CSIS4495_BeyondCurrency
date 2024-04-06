package com.example.beyondcurrency.models;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class UserCardModel {

    private int userId;
    private String firstName;
    private String lastName;
    private String imageUrl;
    private int trustScore;
    private int work_done;
    private boolean isSavedTalent;

    public UserCardModel(int userId, String firstName, String lastName, String imageUrl, int trustScore, int work_done, boolean isSavedTalent) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.imageUrl = imageUrl;
        this.trustScore = trustScore;
        this.work_done = work_done;
        this.isSavedTalent = isSavedTalent;
    }

    @Override
    public String toString() {
        return "UserCardModel{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", trustScore=" + trustScore +
                ", work_done=" + work_done +
                ", isSavedTalent=" + isSavedTalent +
                '}';
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getTrustScore() {
        return trustScore;
    }

    public void setTrustScore(int trustScore) {
        this.trustScore = trustScore;
    }

    public int getWork_done() {
        return work_done;
    }

    public void setWork_done(int work_done) {
        this.work_done = work_done;
    }

    public boolean isSavedTalent() {
        return isSavedTalent;
    }

    public void setSavedTalent(boolean savedTalent) {
        isSavedTalent = savedTalent;
    }
}
