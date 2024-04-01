package com.example.beyondcurrency.models;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ApplicationMapper implements RowMapper<ApplicationModel> {
    @Override
    public ApplicationModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        ApplicationModel application = new ApplicationModel();
        application.setApplicationId(rs.getInt("application_id"));
        application.setApplicantId(rs.getInt("applicant_id"));
        application.setServiceId(rs.getInt("service_id"));
        application.setPosterId(rs.getInt("poster_id"));
        application.setStatus(rs.getString("status"));
        return application;
    }
}
