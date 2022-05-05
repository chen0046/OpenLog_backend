package com.dtu.openlog.repository;

import com.dtu.openlog.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int save(User user) {
        return jdbcTemplate.update("INSERT INTO openlog.user (UserName,Email,`Password`) VALUES(?,?,?)",
                new Object[]{user.getUserName(), user.getEmail(), user.getPassword()});
    }

    public int deleteAll() {
        return jdbcTemplate.update("DELETE openlog.user from `user`");
    }

    public int deleteById(int user) {
        return jdbcTemplate.update("DELETE FROM openlog.user WHERE UserID =?", user);
    }

    public User findByUserNameAndPwd(String userName, String pwd) {
        try {
            User user = jdbcTemplate.queryForObject("SELECT * from openlog.user WHERE UserName=? AND Password = ?",
                    BeanPropertyRowMapper.newInstance(User.class), userName, pwd);
            return user;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    public int update(User user) {
        return jdbcTemplate.update("UPDATE openlog.user SET UserName= ?,Password=?,Email = ?  WHERE UserID=?",
                new Object[]{user.getUserName(), user.getPassword(), user.getEmail(), user.getUserID()});
    }
    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * from openlog.user", BeanPropertyRowMapper.newInstance(User.class));
    }

    public User findById(long userID) {
        try {
            User user = jdbcTemplate.queryForObject("SELECT * FROM user WHERE userID=?",
                    BeanPropertyRowMapper.newInstance(User.class), userID);
            return user;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

}
