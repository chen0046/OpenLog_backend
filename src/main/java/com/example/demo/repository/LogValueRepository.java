package com.example.demo.repository;

import com.example.demo.model.LogValue;
import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LogValueRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int save(LogValue logValue) {
        return jdbcTemplate.update("INSERT INTO openlog.logvalue (Kulhydrat, Insulin, Blodsukker) VALUES(?,?,?)",
                new Object[]{logValue.getKulhydrat(),logValue.getBlodsukker(),logValue.getInsulin()});
    }
}
