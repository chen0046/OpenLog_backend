package com.dtu.openlog.repository;

import com.dtu.openlog.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int save(User user) {
        return jdbcTemplate.update("INSERT INTO openlog.user (UserName,Email,`Password`,Token) VALUES(?,?,?,?)",
                new Object[]{user.getUserName(), user.getEmail(), user.getPassword(), user.getToken()});
    }

    public int deleteAll() {
        return jdbcTemplate.update("DELETE from User");
    }

    public int deleteById(int user) {
        return jdbcTemplate.update("DELETE FROM User WHERE UserID =?", user);
    }

    public User findById(int userID) {
        try {
            User user = jdbcTemplate.queryForObject("SELECT * from openlog.user WHERE UserID=?",
                    BeanPropertyRowMapper.newInstance(User.class), userID);
            return user;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
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


    public List<User> findByTitleContaining(int userID) {
        String q = "SELECT * from openlog.user WHERE userID LIKE '%" + userID + "%'";
        return jdbcTemplate.query(q, BeanPropertyRowMapper.newInstance(User.class));
    }

    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * from openlog.user", BeanPropertyRowMapper.newInstance(User.class));
    }

    public int update(User user) {
        return jdbcTemplate.update("UPDATE openlog.user SET UserName= ?,Password=?,Email = ?  WHERE UserID=?",
                new Object[]{user.getUserName(), user.getPassword(), user.getEmail(), user.getUserID()});
    }
    /*
     public List<User> findAll(){
        return jdbcTemplate.query("select *from openlog.user", BeanPropertyRowMapper.newInstance(User.class));
    }

    public List<User> findByTitleContaining(String userName) {
        String q = "SELECT * from openlog.user WHERE userName LIKE '%" + userName + "%'";
        return jdbcTemplate.query(q, BeanPropertyRowMapper.newInstance(User.class));
    }

    public User findById(int id) {
        try {
            User user = jdbcTemplate.queryForObject("SELECT *from User WHERE UserID=?",
                    BeanPropertyRowMapper.newInstance(User.class), id);
            return user;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }
*/
/*
    public int update(User user) {
        return jdbcTemplate.update("UPDATE openlog.user SET password=?,  WHERE id=?",
                new Object[] { user.getPassword(), user.getUserID() });
    }

 */
/*
    public Tutorial findById(Long id) {
        try {
            Tutorial tutorial = jdbcTemplate.queryForObject("SELECT * FROM tutorials WHERE id=?",
                    BeanPropertyRowMapper.newInstance(Tutorial.class), id);
            return tutorial;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }
    public int deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM tutorials WHERE id=?", id);
    }

    public List<Tutorial> findAll() {
        return jdbcTemplate.query("SELECT * from tutorials", BeanPropertyRowMapper.newInstance(Tutorial.class));
    }
    public List<Tutorial> findByPublished(boolean published) {
        return jdbcTemplate.query("SELECT * from tutorials WHERE published=?",
                BeanPropertyRowMapper.newInstance(Tutorial.class), published);
    }
    public List<Tutorial> findByTitleContaining(String title) {
        String q = "SELECT * from tutorials WHERE title LIKE '%" + title + "%'";
        return jdbcTemplate.query(q, BeanPropertyRowMapper.newInstance(Tutorial.class));
    }
    public int deleteAll() {
        return jdbcTemplate.update("DELETE from tutorials");
    }
*/
}
