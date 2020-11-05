package com.qf.service.impl;

import com.qf.dao.UserDao;
import com.qf.entity.User;
import com.qf.entity.WorkHome;
import com.qf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao dao;

    @Override
    public User getOne(String email) {
        return dao.getOne(email);
    }

    @Override
    public List<User> getAll() {
        List<User> a = dao.getAll();
        return a;
    }

    @Override
    public Integer addOne(User user) {

        return dao.addOne(user);
    }

    @Override
    public void delete(Integer id) {
        dao.delete(id);

    }

    @Override
    public Integer update(User user) {
        return dao.update(user);
    }

    @Override
    public User getpsd(String email) {
        return dao.getPsdByEmail(email);
    }

    @Override
    public List<User> getWorkHomeList(Integer uid) {
        return dao.getWorkHomeList(uid);
    }
}
