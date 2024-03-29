package com.example.beyondcurrency.models;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class ServiceMapper implements RowMapper<ServiceModel> {
    @Override
    public ServiceModel mapRow(ResultSet rs, int rowNum) throws SQLException {

        int serviceId = rs.getInt("service_id");
        String serviceTitle = rs.getString("service_title");
        String description = rs.getString("description");
        String imageUrl = rs.getString("image_url");
        int categoryId = rs.getInt("category_id");
        String status = rs.getString("status");
        Date createDate = rs.getDate("create_date");
        Date deadline = rs.getDate("deadline");
        int posterId = rs.getInt("poster_id");
        int takerId = rs.getInt("taker_id");
        String exchangeService = rs.getString("exchange_service");
        String commentToTaker = rs.getString("comment_to_taker");
        String commentToPoster = rs.getString("comment_to_poster");
        int rateToTaker = rs.getInt("rate_to_taker");
        int rateToPoster = rs.getInt("rate_to_poster");

        ServiceModel service = new ServiceModel(serviceId, serviceTitle, description, imageUrl, categoryId, status, createDate, deadline, posterId, takerId, exchangeService, commentToTaker, commentToPoster, rateToTaker, rateToPoster);

        return service;
    }
}
