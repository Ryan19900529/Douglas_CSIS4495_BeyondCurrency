package com.example.beyondcurrency.models;

public class ApplicationModel {
    private int applicationId;
    private int applicantId;
    private int serviceId;
    private int posterId;
    private String status;

    public ApplicationModel() {
    }

    public ApplicationModel(int applicationId, int applicantId, int serviceId, int posterId, String status) {
        this.applicationId = applicationId;
        this.applicantId = applicantId;
        this.serviceId = serviceId;
        this.posterId = posterId;
        this.status = status;
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

    @Override
    public String toString() {
        return "ApplicantModel{" +
                "applicationId=" + applicationId +
                ", applicantId=" + applicantId +
                ", serviceId=" + serviceId +
                ", posterId=" + posterId +
                ", status='" + status + '\'' +
                '}';
    }
}
