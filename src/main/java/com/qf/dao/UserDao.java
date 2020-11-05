package com.qf.dao;



import com.qf.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserDao {

    User getOne(String email);

    /*查所有*/
    List<User> getAll();

    /*添加*/
    Integer addOne(User user);

    /*删除一个*/
    void delete(Integer id);

    /*修改用户信息*/
    Integer update(User user);

    @Select("select upassword from user where uemail = #{arg0}")
    User getPsdByEmail(String name);

    List<User> getWorkHomeList(Integer uid);
}
