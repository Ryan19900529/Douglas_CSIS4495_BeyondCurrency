package com.example.beyondcurrency.models;

import java.util.Date;

public class ServiceModel {
    private int serviceId;
    private String serviceTitle;
    private String description;
    private String imageUrl;
    private int categoryId;
    private Date createDate;
    private String status;
    private Date deadline;
    private String exchangeService;
    private int posterId;
    private int takerId;
    private String commentToTaker;
    private String commentToPoster;
    private String rateToTaker;
    private String rateToPoster;

    public ServiceModel() {
    }

}
