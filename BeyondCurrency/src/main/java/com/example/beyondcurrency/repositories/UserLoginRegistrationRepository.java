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

        int[] categoryIds = getCategoryId(newUser);

        System.out.println(newUser.toString());
        System.out.println(categoryIds[0]); // Print the first element
        System.out.println(categoryIds[1]); // Print the second element
        System.out.println(categoryIds[2]);
        if(newUser.getImageUrl()==null){
            //user didn't upload image
            result = jdbcTemplate.update(
                    "INSERT INTO users (first_name, last_name, password, phone, date_of_birth, email, language_1, language_2, website_url, skill_1, category_1_id, skill_2, category_2_id, skill_3, category_3_id) " +
                            "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
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
                    categoryIds[0],
                    (newUser.getSkill2() != null) ? newUser.getSkill2() : null,
                    (newUser.getSkill2() != null) ? categoryIds[1] : 0,
                    (newUser.getSkill3() != null) ? newUser.getSkill3() : null,
                    (newUser.getSkill3() != null) ? categoryIds[2] : 0
            );
        } else {
            result = jdbcTemplate.update(
                    "INSERT INTO users (first_name, last_name, password, phone, date_of_birth, email, language_1, language_2, website_url, image_url, skill_1, category_1_id, skill_2, category_2_id, skill_3, category_3_id) " +
                            "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
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
                    categoryIds[0],
                    (newUser.getSkill2() != null) ? newUser.getSkill2() : null,
                    (newUser.getSkill2() != null) ? categoryIds[1] : 0,
                    (newUser.getSkill3() != null) ? newUser.getSkill3() : null,
                    (newUser.getSkill3() != null) ? categoryIds[2] : 0
            );
        }

        return result;
    }

    public int[] getCategoryId(UserModel userModel){

        int skill1_id = 0;
        int skill2_id = 0;
        int skill3_id = 0;

        if (userModel.getSkill1().equals("General Furniture Assembly")) {
            skill1_id = 1;
        } else if (userModel.getSkill1().equals("Electrical Appliances Assembly")) {
            skill1_id = 2;
        } else if (userModel.getSkill1().equals("General Mounting")) {
            skill1_id = 3;
        } else if (userModel.getSkill1().equals("TV Mounting")) {
            skill1_id = 4;
        } else if (userModel.getSkill1().equals("Help Moving")) {
            skill1_id = 5;
        } else if (userModel.getSkill1().equals("Trash & Furniture Removal")) {
            skill1_id = 6;
        } else if (userModel.getSkill1().equals("Heavy Lifting & Loading")) {
            skill1_id = 7;
        } else if (userModel.getSkill1().equals("Kitchen Cleaning")) {
            skill1_id = 8;
        } else if (userModel.getSkill1().equals("Bathroom Cleaning")) {
            skill1_id = 9;
        } else if (userModel.getSkill1().equals("Yard Work")) {
            skill1_id = 10;
        } else if (userModel.getSkill1().equals("Lawn Care")) {
            skill1_id = 11;
        } else if (userModel.getSkill1().equals("Snow Removal")) {
            skill1_id = 12;
        } else if (userModel.getSkill1().equals("Electrical Help")) {
            skill1_id = 13;
        } else if (userModel.getSkill1().equals("Plumbing Help")) {
            skill1_id = 14;
        } else if (userModel.getSkill1().equals("Minor Home Repairs")) {
            skill1_id = 15;
        } else if (userModel.getSkill1().equals("Light Carpentry")) {
            skill1_id = 16;
        } else if (userModel.getSkill1().equals("Indoor Painting")) {
            skill1_id = 17;
        } else if (userModel.getSkill1().equals("Outdoor Painting")) {
            skill1_id = 18;
        };

        if (userModel.getSkill2() != null) {
            if (userModel.getSkill2().equals("General Furniture Assembly")) {
                skill2_id = 1;
            } else if (userModel.getSkill2().equals("Electrical Appliances Assembly")) {
                skill2_id = 2;
            } else if (userModel.getSkill2().equals("General Mounting")) {
                skill2_id = 3;
            } else if (userModel.getSkill2().equals("TV Mounting")) {
                skill2_id = 4;
            } else if (userModel.getSkill2().equals("Help Moving")) {
                skill2_id = 5;
            } else if (userModel.getSkill2().equals("Trash & Furniture Removal")) {
                skill2_id = 6;
            } else if (userModel.getSkill2().equals("Heavy Lifting & Loading")) {
                skill2_id = 7;
            } else if (userModel.getSkill2().equals("Kitchen Cleaning")) {
                skill2_id = 8;
            } else if (userModel.getSkill2().equals("Bathroom Cleaning")) {
                skill2_id = 9;
            } else if (userModel.getSkill2().equals("Yard Work")) {
                skill2_id = 10;
            } else if (userModel.getSkill2().equals("Lawn Care")) {
                skill2_id = 11;
            } else if (userModel.getSkill2().equals("Snow Removal")) {
                skill2_id = 12;
            } else if (userModel.getSkill2().equals("Electrical Help")){
                skill2_id = 13;
            } else if (userModel.getSkill2().equals("Plumbing Help")) {
                skill2_id = 14;
            } else if (userModel.getSkill2().equals("Minor Home Repairs")) {
                skill2_id = 15;
            } else if (userModel.getSkill2().equals("Light Carpentry")) {
                skill2_id = 16;
            } else if (userModel.getSkill2().equals("Indoor Painting")) {
                skill2_id = 17;
            } else if (userModel.getSkill2().equals("Outdoor Painting")) {
                skill2_id = 18;
            };
        }

        if (userModel.getSkill3() != null) {
            if (userModel.getSkill3().equals("General Furniture Assembly")) {
                skill3_id = 1;
            } else if (userModel.getSkill3().equals("Electrical Appliances Assembly")) {
                skill3_id = 2;
            } else if (userModel.getSkill3().equals("General Mounting")) {
                skill3_id = 3;
            } else if (userModel.getSkill3().equals("TV Mounting")) {
                skill3_id = 4;
            } else if (userModel.getSkill3().equals("Help Moving")) {
                skill3_id = 5;
            } else if (userModel.getSkill3().equals("Trash & Furniture Removal")) {
                skill3_id = 6;
            } else if (userModel.getSkill3().equals("Heavy Lifting & Loading")) {
                skill3_id = 7;
            } else if (userModel.getSkill3().equals("Kitchen Cleaning")){
                skill3_id = 8;
            } else if (userModel.getSkill3().equals("Bathroom Cleaning")) {
                skill3_id = 9;
            } else if (userModel.getSkill3().equals("Yard Work")) {
                skill3_id = 10;
            } else if (userModel.getSkill3().equals("Lawn Care")) {
                skill3_id = 11;
            } else if (userModel.getSkill3().equals("Snow Removal")) {
                skill3_id = 12;
            } else if (userModel.getSkill3().equals("Electrical Help")) {
                skill3_id = 13;
            } else if (userModel.getSkill3().equals("Plumbing Help")) {
                skill3_id = 14;
            } else if (userModel.getSkill3().equals("Minor Home Repairs")) {
                skill3_id = 15;
            } else if (userModel.getSkill3().equals("Light Carpentry")) {
                skill3_id = 16;
            } else if (userModel.getSkill3().equals("Indoor Painting")) {
                skill3_id = 17;
            } else if (userModel.getSkill3().equals("Outdoor Painting")) {
                skill3_id = 18;
            };
        }

        int[] categoryIds = {skill1_id, skill2_id,skill3_id};

        return categoryIds;
    }
}
