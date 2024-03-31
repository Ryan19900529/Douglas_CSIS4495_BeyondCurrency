package com.example.beyondcurrency.models;

import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ServiceModel {
    private int serviceId;
    private String serviceTitle;
    private String description;
    private String imageUrl;
    private int categoryId;
    private String status;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date deadline;
    private int posterId;
    private int takerId;
    private String exchangeService;
    private String commentToTaker;
    private String commentToPoster;
    private int rateToTaker;
    private int rateToPoster;
    private String historyDate;

    public ServiceModel() {
    }

    public ServiceModel(int serviceId, String serviceTitle, String description, String imageUrl, int categoryId, String status, Date createDate, Date deadline, int posterId, int takerId, String exchangeService, String commentToTaker, String commentToPoster, int rateToTaker, int rateToPoster) {
        this.serviceId = serviceId;
        this.serviceTitle = serviceTitle;
        this.description = description;
        this.imageUrl = imageUrl;
        this.categoryId = categoryId;
        this.status = status;
        this.createDate = createDate;
        this.deadline = deadline;
        this.posterId = posterId;
        this.takerId = takerId;
        this.exchangeService = exchangeService;
        this.commentToTaker = commentToTaker;
        this.commentToPoster = commentToPoster;
        this.rateToTaker = rateToTaker;
        this.rateToPoster = rateToPoster;
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
        this.historyDate = dateFormat.format(createDate) + " - " + dateFormat.format(deadline);
    }

    public String getHistoryDate() {
        return historyDate;
    }

    public void setHistoryDate(String historyDate) {
        this.historyDate = historyDate;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceTitle() {
        return serviceTitle;
    }

    public void setServiceTitle(String serviceTitle) {
        this.serviceTitle = serviceTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public int getPosterId() {
        return posterId;
    }

    public void setPosterId(int posterId) {
        this.posterId = posterId;
    }

    public int getTakerId() {
        return takerId;
    }

    public void setTakerId(int takerId) {
        this.takerId = takerId;
    }

    public String getExchangeService() {
        return exchangeService;
    }

    public void setExchangeService(String exchangeService) {
        this.exchangeService = exchangeService;
    }

    public String getCommentToTaker() {
        return commentToTaker;
    }

    public void setCommentToTaker(String commentToTaker) {
        this.commentToTaker = commentToTaker;
    }

    public String getCommentToPoster() {
        return commentToPoster;
    }

    public void setCommentToPoster(String commentToPoster) {
        this.commentToPoster = commentToPoster;
    }

    public int getRateToTaker() {
        return rateToTaker;
    }

    public void setRateToTaker(int rateToTaker) {
        this.rateToTaker = rateToTaker;
    }

    public int getRateToPoster() {
        return rateToPoster;
    }

    public void setRateToPoster(int rateToPoster) {
        this.rateToPoster = rateToPoster;
    }

    @Override
    public String toString() {
        return "ServiceModel{" +
                "serviceId=" + serviceId +
                ", serviceTitle='" + serviceTitle + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", categoryId=" + categoryId +
                ", status='" + status + '\'' +
                ", createDate=" + createDate +
                ", deadline=" + deadline +
                ", posterId=" + posterId +
                ", takerId=" + takerId +
                ", exchangeService='" + exchangeService + '\'' +
                ", commentToTaker='" + commentToTaker + '\'' +
                ", commentToPoster='" + commentToPoster + '\'' +
                ", rateToTaker='" + rateToTaker + '\'' +
                ", rateToPoster='" + rateToPoster + '\'' +
                '}';
    }
}
