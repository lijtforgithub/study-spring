package com.ljt.study.transaction.service.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.ljt.study.entity.User;
import com.ljt.study.transaction.service.JTAService;
import com.ljt.study.transaction.service.UserService;

/**
 * @author LiJingTang
 * @version 2015年11月5日 下午7:44:06
 */
public class JTAServiceImpl implements JTAService {
	
	private JdbcTemplate JdbcTemplate1;
	private JdbcTemplate JdbcTemplate2;

	@Override
	public void saveUser(final User user) {
		if (null == user) {
			throw new NullPointerException("User 对象为空");
		}
		
		this.JdbcTemplate1.update(UserService.SQL_INSERT, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, user.getName());
				ps.setString(2, user.getPassword());
			}
		});
		
		this.JdbcTemplate2.update(UserService.SQL_INSERT, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, user.getName());
				ps.setString(2, user.getPassword());
			}
		});
	}

	public void setJdbcTemplate1(JdbcTemplate jdbcTemplate1) {
		JdbcTemplate1 = jdbcTemplate1;
	}

	public void setJdbcTemplate2(JdbcTemplate jdbcTemplate2) {
		JdbcTemplate2 = jdbcTemplate2;
	}

}
