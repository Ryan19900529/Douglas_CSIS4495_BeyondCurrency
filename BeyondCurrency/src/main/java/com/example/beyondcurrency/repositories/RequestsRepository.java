package com.example.beyondcurrency.repositories;

import com.example.beyondcurrency.models.ServiceMapper;
import com.example.beyondcurrency.models.ServiceModel;
import com.example.beyondcurrency.models.UserMapper;
import com.example.beyondcurrency.models.UserModel;
import jakarta.annotation.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RequestsRepository {
    @Resource
    DataSource dataSource;
    @Resource
    JdbcTemplate jdbcTemplate;

    public List<ServiceModel> getAllRequests() {
        List<ServiceModel> results = jdbcTemplate.query("SELECT * FROM services", new ServiceMapper());

        return results;
    }

    public List<ServiceModel> getRequestsByCategoryId(int id) {
        List<ServiceModel> results = jdbcTemplate.query("SELECT * FROM services WHERE category_id = ?", new ServiceMapper(), id);

        return results;
    }

    public List<UserModel> getUsersByCategoryId(int id) {
        List<UserModel> results1 = jdbcTemplate.query("SELECT * FROM users WHERE category_1_id = ?", new UserMapper(false), id);
        List<UserModel> results2 = jdbcTemplate.query("SELECT * FROM users WHERE category_2_id = ?", new UserMapper(false), id);
        List<UserModel> results3 = jdbcTemplate.query("SELECT * FROM users WHERE category_3_id = ?", new UserMapper(false), id);
        List<UserModel> results = new ArrayList<>();
        results.addAll(results1);
        results.addAll(results2);
        results.addAll(results3);

        return results;
    }

}
