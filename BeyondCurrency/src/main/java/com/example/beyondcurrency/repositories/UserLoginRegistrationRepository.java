package com.example.beyondcurrency.repositories;

import com.example.beyondcurrency.models.UserMapper;
import com.example.beyondcurrency.models.UserModel;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserLoginRegistrationRepository {
    @Resource
    DataSource dataSource;
    @Resource
    JdbcTemplate jdbcTemplate;

    public List<UserModel> getLoginUsers() {
        List<UserModel> results = jdbcTemplate.query("SELECT * FROM users", new UserMapper(true));

        return results;
    }

    public List<UserModel> getUsers() {
        List<UserModel> results = jdbcTemplate.query("SELECT * FROM users", new UserMapper(false));

        return results;
    }

    public long addOne(UserModel newUser) {
        long result = 0;

        if(newUser.getImageUrl()==null){
            result = jdbcTemplate.update(
                    "INSERT INTO users (first_name, last_name, password, phone, date_of_birth, email, language_1, language_2, website_url, skill_1, skill_2, skill_3) " +
                            "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)",
                    newUser.getFirstName(),
                    newUser.getLastName(),
                    newUser.getPassword(),
                    newUser.getPhone(),
                    newUser.getDob(),
                    newUser.getEmail(),
                    newUser.getLanguage1(),
                    (newUser.getLanguage2() != null) ? newUser.getLanguage2() : null,
                    (newUser.getWebsite() != null) ? newUser.getWebsite() : null,
                    newUser.getSkill1(),
                    (newUser.getSkill2() != "a") ? newUser.getSkill2() : null,
                    (newUser.getSkill3() != "a") ? newUser.getSkill3() : null
            );
        } else {
            result = jdbcTemplate.update(
                    "INSERT INTO users (first_name, last_name, password, phone, date_of_birth, email, language_1, language_2, website_url, image_url, skill_1, skill_2, skill_3) " +
                            "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)",
                    newUser.getFirstName(),
                    newUser.getLastName(),
                    newUser.getPassword(),
                    newUser.getPhone(),
                    newUser.getDob(),
                    newUser.getEmail(),
                    newUser.getLanguage1(),
                    (newUser.getLanguage2() != null) ? newUser.getLanguage2() : null,
                    (newUser.getWebsite() != null) ? newUser.getWebsite() : null,
                    (newUser.getImageUrl() != null) ? newUser.getImageUrl() : null,
                    newUser.getSkill1(),
                    (newUser.getSkill2() != null) ? newUser.getSkill2() : null,
                    (newUser.getSkill3() != null) ? newUser.getSkill3() : null
            );
        }

        return result;
    }
}
