package com.example.beyondcurrency.models;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NotificationMapper implements RowMapper<NotificationModel> {
    @Override
    public NotificationModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        NotificationModel notification = new NotificationModel();
        notification.setNotificationId(rs.getInt("notification_id"));
        notification.setUserId(rs.getInt("user_is"));
        notification.setContent(rs.getString("content"));
        notification.setShowNotification(rs.getBoolean("show_notification"));
        notification.setNewNotification(rs.getBoolean("new_notification"));
        return notification;
    }
}
