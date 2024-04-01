package com.example.beyondcurrency.repositories;

import com.example.beyondcurrency.models.ServiceMapper;
import com.example.beyondcurrency.models.ServiceModel;
import jakarta.annotation.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class PostRepository {

    @Resource
    JdbcTemplate jdbcTemplate;

    public ServiceModel getPostByServiceId(int id) {
        List<ServiceModel> results = jdbcTemplate.query("SELECT * FROM services WHERE service_id = ?", new ServiceMapper(), id);

        if(results.size() > 0) {
            return results.get(0);
        } else {
            return null;
        }
    }

    public long addNewPost(ServiceModel service) {
        long result = 0;

        result = jdbcTemplate.update(
                "INSERT INTO services (service_title, description, image_url, category_id, status, create_date, deadline, poster_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                service.getServiceTitle(),
                service.getDescription(),
                service.getImageUrl(),
                service.getCategoryId(),
                service.getStatus(),
                service.getCreateDate(),
                service.getDeadline(),
                service.getPosterId()
        );

        return result;
    }
}
