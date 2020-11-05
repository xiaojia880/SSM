package com.qf.dao;


import com.qf.entity.Comment;
import com.qf.entity.Contact;
import com.qf.entity.User;
import com.qf.entity.WorkHome;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface WorkHomeDao {
    List<WorkHome> getAll();

    WorkHome getOneByWid(Integer wid);

    List<WorkHome> getOneByTitle(String wtitle);

    Integer add(WorkHome workHome);

    Integer update(WorkHome workHome);

    Integer delete(Integer wid);

    Integer count();//列表长度

    Integer countPi();//列表长度

    @Select("select * from workhome limit #{arg0},#{arg1}")
    List<WorkHome> getList(int pageIndex, int pageSize);

    List<WorkHome> comment(Integer wid);

    Integer CommentAdd(Comment c);

    @Update("update pinglun set wid = 6 where wid = #{arg0}")
    void xiugaipinglunren(Integer wid);

    void addMsg(Contact contact);

    @Select("select utu from user where uname = #{arg0}")
    User findutu(String name);
}
