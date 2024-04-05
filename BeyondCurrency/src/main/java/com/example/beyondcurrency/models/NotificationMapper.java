package com.example.beyondcurrency.models;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NotificationMapper implements RowMapper<NotificationModel> {
    @Override
    public NotificationModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        NotificationModel notification = new NotificationModel();
        notification.setNotificationId(rs.getInt("notification_id"));
        notification.setUserId(rs.getInt("user_id"));
        notification.setSenderId(rs.getInt("sender_id"));
        notification.setSenderImg(rs.getString("sender_img"));
        notification.setServiceId(rs.getInt("service_id"));
        notification.setContent(rs.getString("content"));
        notification.setShowNotification(rs.getBoolean("show_notification"));
        notification.setNewNotification(rs.getBoolean("new_notification"));
        return notification;
    }
}
