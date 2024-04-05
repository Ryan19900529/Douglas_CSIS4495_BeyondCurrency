package com.example.beyondcurrency.repositories;

import com.example.beyondcurrency.models.ServiceMapper;
import com.example.beyondcurrency.models.ServiceModel;
import jakarta.annotation.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
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

        if(service.getImageUrl()!=null){
            result = jdbcTemplate.update(
                    "INSERT INTO services (service_title, description, image_url, category_id, deadline, poster_id) VALUES (?, ?, ?, ?, ?, ?)",
                    service.getServiceTitle(),
                    service.getDescription(),
                    service.getImageUrl(),
                    service.getCategoryId(),
                    service.getDeadline(),
                    service.getPosterId()
            );
        } else {
            result = jdbcTemplate.update(
                    "INSERT INTO services (service_title, description, category_id, deadline, poster_id) VALUES (?, ?, ?, ?, ?)",
                    service.getServiceTitle(),
                    service.getDescription(),
                    service.getCategoryId(),
                    service.getDeadline(),
                    service.getPosterId()
            );
        }


        return result;
    }

    public void updateTakerToPost(ServiceModel post, int applicantId, String exchangeSkill, String status) {
        jdbcTemplate.update("UPDATE services SET taker_id = ?, exchange_service = ?, status = ? WHERE service_id = ?",applicantId, exchangeSkill, status, post.getServiceId());
    }

    public void updatePosterRateToTaker(ServiceModel post, String comment, int rate) {
        jdbcTemplate.update("UPDATE services SET comment_to_taker = ?, rate_to_taker = ? WHERE service_id = ?",comment, rate, post.getServiceId());
    }

    public void updateTakerRateToPoster(ServiceModel post, String comment, int rate) {
        jdbcTemplate.update("UPDATE services SET comment_to_poster = ?, rate_to_poster = ? WHERE service_id = ?",comment, rate, post.getServiceId());
    }

    public void updatePostStatus(ServiceModel post, String status) {
        jdbcTemplate.update("UPDATE services SET status = ? WHERE service_id = ?", status, post.getServiceId());

    }

    public void editPost (int postId, int skillSelected, String title, Date deadline, String description, String imageFile){
        if (imageFile != null) {
            jdbcTemplate.update("UPDATE services SET category_id = ?, service_title = ?, deadline = ?, description = ?, image_url = ? WHERE service_id = ?", skillSelected, title, deadline, description, imageFile, postId);
        } else {
            jdbcTemplate.update("UPDATE services SET category_id = ?, service_title = ?, deadline = ?, description = ? WHERE service_id = ?", skillSelected, title, deadline, description, postId);

        }
    }
}
