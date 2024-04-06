package com.example.beyondcurrency.repositories;

import com.example.beyondcurrency.models.NotificationMapper;
import com.example.beyondcurrency.models.NotificationModel;
import com.example.beyondcurrency.models.SavedTalentMapper;
import com.example.beyondcurrency.models.SavedTalentModel;
import jakarta.annotation.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SavedTalentRepository {
    @Resource
    JdbcTemplate jdbcTemplate;

    public long addNewSavedTalent(SavedTalentModel savedTalent) {
        long result = 0;

        result = jdbcTemplate.update(
                "INSERT INTO saved_talent (user_id, talent_id) VALUES (?, ?, ?)",
                savedTalent.getUserId(),
                savedTalent.getTalentId()
        );

        return result;
    }

    public List<SavedTalentModel> getAllSavedTalents() {
        List<SavedTalentModel> results = jdbcTemplate.query("SELECT * FROM saved_talent", new SavedTalentMapper());

        return results;
    }
}
