package com.example.beyondcurrency.models;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class UserModel {

    private int userId;
    private String firstName;
    private String lastName;
    private String password;
    private String phone;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dob;
    private String email;
    private String language1;
    private String language2;
    private Date signUpDate;
    private String website;
    private String imageUrl;
    private String skill1;
    private int category1Id;
    private String skill2;
    private int category2Id;
    private String skill3;
    private int category3Id;
    private int trustScore;
    private boolean newNotification;
    private String title;
    private String about;
    private int work_done;

    public UserModel() {
    }

    public UserModel(int userId, String firstName, String lastName, String password, String phone, Date dob, String email, String language1, String language2, Date signUpDate, String website, String imageUrl, String skill1, int category1Id, String skill2, int category2Id, String skill3, int category3Id, int trustScore, boolean newNotification, String title, String about, int work_done) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.phone = phone;
        this.dob = dob;
        this.email = email;
        this.language1 = language1;
        this.language2 = language2;
        this.signUpDate = signUpDate;
        this.website = website;
        this.imageUrl = imageUrl;
        this.skill1 = skill1;
        this.category1Id = category1Id;
        this.skill2 = skill2;
        this.category2Id = category2Id;
        this.skill3 = skill3;
        this.category3Id = category3Id;
        this.trustScore = trustScore;
        this.newNotification = newNotification;
        this.title = title;
        this.about = about;
        this.work_done = work_done;
    }

    //for login
    public UserModel(String password, String email) {
        this.password = password;
        this.email = email;
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

    public int getCategory1Id() {
        return category1Id;
    }

    public void setCategory1Id(int category1Id) {
        this.category1Id = category1Id;
    }

    public int getCategory2Id() {
        return category2Id;
    }

    public void setCategory2Id(int category2Id) {
        this.category2Id = category2Id;
    }

    public int getCategory3Id() {
        return category3Id;
    }

    public void setCategory3Id(int category3Id) {
        this.category3Id = category3Id;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isNewNotification() {
        return newNotification;
    }

    public void setNewNotification(boolean newNotification) {
        this.newNotification = newNotification;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLanguage1() {
        return language1;
    }

    public void setLanguage1(String language1) {
        this.language1 = language1;
    }

    public String getLanguage2() {
        return language2;
    }

    public void setLanguage2(String language2) {
        this.language2 = language2;
    }

    public Date getSignUpDate() {
        return signUpDate;
    }

    public void setSignUpDate(Date signUpDate) {
        this.signUpDate = signUpDate;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

    public int getTrustScore() {
        return trustScore;
    }

    public void setTrustScore(int trustScore) {
        this.trustScore = trustScore;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public int getWork_done() {
        return work_done;
    }

    public void setWork_done(int work_done) {
        this.work_done = work_done;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", dob=" + dob +
                ", email='" + email + '\'' +
                ", language1='" + language1 + '\'' +
                ", language2='" + language2 + '\'' +
                ", signUpDate=" + signUpDate +
                ", website='" + website + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", skill1='" + skill1 + '\'' +
                ", category1Id=" + category1Id +
                ", skill2='" + skill2 + '\'' +
                ", category2Id=" + category2Id +
                ", skill3='" + skill3 + '\'' +
                ", category3Id=" + category3Id +
                ", trustScore='" + trustScore + '\'' +
                ", newNotification=" + newNotification +
                ", title='" + title + '\'' +
                ", about='" + about + '\'' +
                ", work_done=" + work_done +
                '}';
    }
}
