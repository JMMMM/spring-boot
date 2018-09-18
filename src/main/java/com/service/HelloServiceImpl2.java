package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class HelloServiceImpl2 {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String hello(){
        return jdbcTemplate.queryForObject("select name from admindb.staffs where id = 16609",String.class);
    }
}
