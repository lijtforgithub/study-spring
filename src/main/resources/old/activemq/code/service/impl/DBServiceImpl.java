package com.ljt.study.jms.service.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.ljt.study.jms.service.DBService;

/**
 * @author LiJingTang
 * @version 2015年11月6日下午3:08:35
 */
public class DBServiceImpl extends JdbcDaoSupport implements DBService {
	
	@Override
	public void saveData(final String data) {
		this.getJdbcTemplate().update(SQL_INSERT, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, data);
			}
		});
	}

}
