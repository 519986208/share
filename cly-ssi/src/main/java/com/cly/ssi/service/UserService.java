package com.cly.ssi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cly.ssi.dao.IUserDao;
import com.cly.ssi.entity.User;

@Service
public class UserService {

	@Autowired
	private IUserDao iUserDao;

	@Transactional
	public void save(User user) {
		iUserDao.save(user);
	}

	public User selectById(int id) {
		return iUserDao.selectById(id);
	}

}
