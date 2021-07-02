package com.ljt.study.transaction.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;

import com.ljt.study.entity.User;
import com.ljt.study.transaction.service.UserService;

/**
 * @author LiJingTang
 * @version 2015年9月23日下午1:40:10
 */
public class DefaultUserService extends JdbcDaoSupport implements UserService {
	
	@Override
	public User getUser(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
//	@Transactional
	public void insertUser(final User user) {
		/*if (null == user) {
			throw new NullPointerException("User 对象为空");
		}
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.getJdbcTemplate().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, user.getName());
				ps.setString(2, user.getPassword());
				
				return ps;
			}
		}, keyHolder);
		
		user.setId(keyHolder.getKey().intValue());
		
		throw new UnsupportedOperationException();*/
		
		privateInsertUser(user);
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
	}
	
	/**
	 * 在使用代理的时候，@Transactional 注解应该只被应用到 public 可见度的方法上。
	 * 如果你在 protected、private 或者 package-visible 的方法上使用 @Transactional 注解，系统也不会报错， 但是这个被注解的方法将不会执行已配置的事务设置。
	 * 在代理模式下(默认的情况)，只有从代理传过来的外部方法调用才会被拦截。 这就意味着自我调用是不会触发事务的，
	 * 比如说，一个在目标对象中调用目标对象其他方法的方法是不会触发一个事务的，即使这个方法被标记为 @Transactional!
	 * 
	 * 用XML方式配置的方法会事务回滚因为他会在调用方法前后加上事务处理
	 * 
	 * 用aspectj模式也会回滚
	 * 
	 * @version 2015年9月24日 上午8:57:57
	 */
	@Transactional
	private void privateInsertUser(final User user) {
		if (null == user) {
			throw new NullPointerException("User 对象为空");
		}
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.getJdbcTemplate().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, user.getName());
				ps.setString(2, user.getPassword());
				
				return ps;
			}
		}, keyHolder);
		
		user.setId(keyHolder.getKey().intValue());
		
		throw new UnsupportedOperationException();
	}

}
