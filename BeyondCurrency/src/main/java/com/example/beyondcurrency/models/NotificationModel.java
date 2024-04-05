package com.example.beyondcurrency.models;

public class NotificationModel {

    private int notificationId;
    private int userId;
    private int senderId;
    private String senderImg;
    private int serviceId;
    private String content;
    private boolean showNotification;
    private boolean newNotification;

    public NotificationModel() {
    }


    public NotificationModel(int notificationId, int userId, int senderId, String senderImg, int serviceId, String content, boolean showNotification, boolean newNotification) {
        this.notificationId = notificationId;
        this.userId = userId;
        this.senderId = senderId;
        this.senderImg = senderImg;
        this.serviceId = serviceId;
        this.content = content;
        this.showNotification = showNotification;
        this.newNotification = newNotification;
    }

    public String getSenderImg() {
        return senderImg;
    }

    public void setSenderImg(String senderImg) {
        this.senderImg = senderImg;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isShowNotification() {
        return showNotification;
    }

    public void setShowNotification(boolean showNotification) {
        this.showNotification = showNotification;
    }

    public boolean isNewNotification() {
        return newNotification;
    }

    public void setNewNotification(boolean newNotification) {
        this.newNotification = newNotification;
    }

    @Override
    public String toString() {
        return "NotificationModel{" +
                "notificationId=" + notificationId +
                ", userId=" + userId +
                ", senderId=" + senderId +
                ", senderImg='" + senderImg + '\'' +
                ", serviceId=" + serviceId +
                ", content='" + content + '\'' +
                ", showNotification=" + showNotification +
                ", newNotification=" + newNotification +
                '}';
    }
}
