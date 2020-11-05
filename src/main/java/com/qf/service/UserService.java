package com.qf.service;

import com.qf.entity.User;

import java.util.List;

public interface UserService {
    /*查一个*/
    User getOne(String email);

    /*查所有*/
    List<User> getAll();

    /*添加*/
    Integer addOne(User user);

    /*删除一个*/
    void delete(Integer id);

    /*修改用户信息*/
    Integer update(User user);

    //找回密码
    User getpsd(String email);

    List<User> getWorkHomeList(Integer uid);
}