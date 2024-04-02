package com.example.beyondcurrency.models;

public class UserApplicationModel {
    private int applicationId;
    private int applicantId;
    private int serviceId;
    private int posterId;
    private String status;
    private String applicantImage;
    private String applicantFirstName;
    private String applicantLastName;
    private String posterSkill1;
    private String posterSkill2;
    private String posterSkill3;

    public UserApplicationModel() {
    }

    public UserApplicationModel(int applicationId, int applicantId, int serviceId, int posterId, String status, String applicantImage, String applicantFirstName, String applicantLastName, String posterSkill1, String posterSkill2, String posterSkill3) {
        this.applicationId = applicationId;
        this.applicantId = applicantId;
        this.serviceId = serviceId;
        this.posterId = posterId;
        this.status = status;
        this.applicantImage = applicantImage;
        this.applicantFirstName = applicantFirstName;
        this.applicantLastName = applicantLastName;
        this.posterSkill1 = posterSkill1;
        this.posterSkill2 = posterSkill2;
        this.posterSkill3 = posterSkill3;
    }

    @Override
    public String toString() {
        return "UserApplicationModel{" +
                "applicationId=" + applicationId +
                ", applicantId=" + applicantId +
                ", serviceId=" + serviceId +
                ", posterId=" + posterId +
                ", status='" + status + '\'' +
                ", applicantImage='" + applicantImage + '\'' +
                ", applicantFirstName='" + applicantFirstName + '\'' +
                ", applicantLastName='" + applicantLastName + '\'' +
                ", posterSkill1='" + posterSkill1 + '\'' +
                ", posterSkill2='" + posterSkill2 + '\'' +
                ", posterSkill3='" + posterSkill3 + '\'' +
                '}';
    }

    public String getPosterSkill1() {
        return posterSkill1;
    }

    public void setPosterSkill1(String posterSkill1) {
        this.posterSkill1 = posterSkill1;
    }

    public String getPosterSkill2() {
        return posterSkill2;
    }

    public void setPosterSkill2(String posterSkill2) {
        this.posterSkill2 = posterSkill2;
    }

    public String getPosterSkill3() {
        return posterSkill3;
    }

    public void setPosterSkill3(String posterSkill3) {
        this.posterSkill3 = posterSkill3;
    }

    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public int getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(int applicantId) {
        this.applicantId = applicantId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public int getPosterId() {
        return posterId;
    }

    public void setPosterId(int posterId) {
        this.posterId = posterId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getApplicantImage() {
        return applicantImage;
    }

    public void setApplicantImage(String applicantImage) {
        this.applicantImage = applicantImage;
    }

    public String getApplicantFirstName() {
        return applicantFirstName;
    }

    public void setApplicantFirstName(String applicantFirstName) {
        this.applicantFirstName = applicantFirstName;
    }

    public String getApplicantLastName() {
        return applicantLastName;
    }

    public void setApplicantLastName(String applicantLastName) {
        this.applicantLastName = applicantLastName;
    }
}
