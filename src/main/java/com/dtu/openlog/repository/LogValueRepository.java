package com.dtu.openlog.repository;

import com.dtu.openlog.dto.LogValue;
import com.dtu.openlog.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public class LogValueRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int save(LogValue logValue) {
        return jdbcTemplate.update("INSERT INTO openlog.logvalue (Kulhydrat, Insulin, Blodsukker) VALUES(?,?,?)",
                new Object[]{logValue.getKulhydrat(), logValue.getBlodsukker(), logValue.getInsulin()});
    }

    public int deleteAll() {
        return jdbcTemplate.update("DELETE from Logvalue");
    }

    public int deleteById(int logValue) {
        return jdbcTemplate.update("DELETE FROM openlog.logvalue WHERE UserID =?", logValue);
    }



}
