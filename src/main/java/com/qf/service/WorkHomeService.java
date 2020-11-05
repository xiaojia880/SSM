package com.qf.service;


import com.qf.entity.Comment;
import com.qf.entity.Contact;
import com.qf.entity.User;
import com.qf.entity.WorkHome;

import java.util.List;

public interface WorkHomeService {
    List<WorkHome> getAll();

    WorkHome getOneByWid(Integer wid);

    List<WorkHome> getOneByTitle(String wtitle);

    Integer add(WorkHome workHome);

    Integer update(WorkHome workHome);

    Integer delete(Integer wid);

    Integer count();

    Integer countPi();

    List<WorkHome> getList(int pageIndex, int pageSize);

    List<WorkHome> comment(Integer wid);

    Integer CommentAdd(Comment c);

    void xiugaipinglunren(Integer wid);

    void addMsg(Contact contact);

    User findutu(String name);

}
