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
}
