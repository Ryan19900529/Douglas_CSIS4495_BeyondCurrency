package com.example.beyondcurrency.repositories;

import com.example.beyondcurrency.models.*;
import jakarta.annotation.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ApplicationsRepository {
    @Resource
    JdbcTemplate jdbcTemplate;


    public long addNewApplication(ApplicationModel application) {
        long result = 0;

        result = jdbcTemplate.update(
                "INSERT INTO applications (applicant_id, service_id, poster_id) VALUES (?, ?, ?)",
                application.getApplicantId(),
                application.getServiceId(),
                application.getPosterId()
        );

        return result;
    }

    public List<ApplicationModel> getAllApplications() {
        List<ApplicationModel> results = jdbcTemplate.query("SELECT * FROM applications", new ApplicationMapper());

        return results;
    }

    public void updateApplicationStatus(int applicationId, int serviceId, String newStatus) {
        jdbcTemplate.update("UPDATE applications SET status = ? WHERE application_id = ? AND service_id = ?", newStatus, applicationId,serviceId);


    }

    public ApplicationModel getApplicationByApplicationId(int id) {
        List<ApplicationModel> results = jdbcTemplate.query("SELECT * FROM applications WHERE application_id = ?", new ApplicationMapper(), id);

        if(results.size() > 0) {
            return results.get(0);
        } else {
            return null;
        }
    }
}
