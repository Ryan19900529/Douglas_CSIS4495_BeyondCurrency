package com.example.beyondcurrency.models;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SavedTalentMapper implements RowMapper<SavedTalentModel> {
    @Override
    public SavedTalentModel mapRow(ResultSet rs, int rowNum) throws SQLException {

        SavedTalentModel savedTalent = new SavedTalentModel(
                rs.getInt("saved_talent_id"),
                rs.getInt("user_id"),
                rs.getInt("talent_id")
        );


        return savedTalent;
    }
}
