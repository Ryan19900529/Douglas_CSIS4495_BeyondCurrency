package com.example.beyondcurrency.repositories;

import com.example.beyondcurrency.models.NotificationMapper;
import com.example.beyondcurrency.models.NotificationModel;
import jakarta.annotation.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NotificationRepository {
    @Resource
    JdbcTemplate jdbcTemplate;

    public long addNewNotification(NotificationModel notification) {
        long result = 0;

        result = jdbcTemplate.update(
                "INSERT INTO notifications (user_id, sender_id, sender_img, service_id, content) VALUES (?, ?, ?, ?, ?)",
                notification.getUserId(),
                notification.getSenderId(),
                notification.getSenderImg(),
                notification.getServiceId(),
                notification.getContent()
        );

        return result;
    }

    public List<NotificationModel> getAllNotifications() {
        List<NotificationModel> results = jdbcTemplate.query("SELECT * FROM notifications", new NotificationMapper());

        return results;
    }

    public void updateNotificationToNotShow(int id) {
        jdbcTemplate.update("UPDATE notifications SET show_notification = ? WHERE notification_id = ?", false, id);
    }

    public void updateNotificationToNotNew(int id) {
        jdbcTemplate.update("UPDATE notifications SET new_notification = ? WHERE notification_id = ?", false, id);
    }
}
