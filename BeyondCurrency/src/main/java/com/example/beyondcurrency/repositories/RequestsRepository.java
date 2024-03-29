package com.example.beyondcurrency.repositories;

import com.example.beyondcurrency.models.ServiceMapper;
import com.example.beyondcurrency.models.ServiceModel;
import com.example.beyondcurrency.models.UserMapper;
import com.example.beyondcurrency.models.UserModel;
import jakarta.annotation.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
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

    public List<ServiceModel> getRequestsById(int id) {
        List<ServiceModel> results = jdbcTemplate.query("SELECT * FROM services WHERE category_id = ?", new ServiceMapper(), id);

        return results;
    }

}
