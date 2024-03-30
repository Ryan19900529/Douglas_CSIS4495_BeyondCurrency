package com.example.beyondcurrency.models;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<UserModel> {
    private final boolean forLogin;

    public UserMapper(boolean forLogin) {
        this.forLogin = forLogin;
    }

    @Override
    public UserModel mapRow(ResultSet rs, int rowNum) throws SQLException {

        UserModel user;

        if (forLogin) {
            // If mapper is for login, use constructor for login
            user = new UserModel(rs.getString("password"), rs.getString("email"));
        } else {
            // If mapper is for full user details, use default constructor
            user = new UserModel(
                    rs.getInt("user_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("password"),
                    rs.getString("phone"),
                    rs.getDate("date_of_birth"),
                    rs.getString("email"),
                    rs.getString("language_1"),
                    rs.getString("language_2"),
                    rs.getDate("sign_up_date"),
                    rs.getString("website_url"),
                    rs.getString("image_url"),
                    rs.getString("skill_1"),
                    rs.getInt("category_1_id"),
                    rs.getString("skill_2"),
                    rs.getInt("category_2_id"),
                    rs.getString("skill_3"),
                    rs.getInt("category_3_id"),
                    rs.getString("trust_score"),
                    rs.getBoolean("new_notification"),
                    rs.getString("title"),
                    rs.getString("about"),
                    rs.getInt("work_done")
            );
        }

        return user;
    }

}
