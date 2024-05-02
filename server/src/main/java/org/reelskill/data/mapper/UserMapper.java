package org.reelskill.data.mapper;

import org.reelskill.models.Role;
import org.reelskill.models.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setUserId(rs.getInt("user_id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setEmailAddress(rs.getString("email_address"));
        user.setRole(Role.valueOf(rs.getString("role")));
        return user;
    }
}
