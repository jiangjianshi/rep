package com.css.sysbase.dao;

import org.springframework.stereotype.Repository;

import com.css.utils.hibernate.GeneralDao;

@Repository(value="commonManager")
public class CommonManager extends GeneralDao {
    private static final long serialVersionUID = -5007246085129055012L;
    public CommonManager() {
        super();
    }
}
