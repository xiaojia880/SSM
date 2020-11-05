package com.qf.service.impl;

import com.qf.dao.WorkHomeDao;
import com.qf.entity.Comment;
import com.qf.entity.Contact;
import com.qf.entity.User;
import com.qf.entity.WorkHome;
import com.qf.service.WorkHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkHomeServiceImpl implements WorkHomeService {
    @Autowired
    private WorkHomeDao dao;

    @Override
    public List<WorkHome> getAll() {
        return dao.getAll();
    }


    @Override
    public WorkHome getOneByWid(Integer wid) {
        return dao.getOneByWid(wid);
    }

    @Override
    public List<WorkHome> getOneByTitle(String wtitle) {
        return dao.getOneByTitle(wtitle);
    }

    @Override
    public Integer add(WorkHome workHome) {
        return dao.add(workHome);
    }

    @Override
    public Integer update(WorkHome workHome) {
        return dao.update(workHome);
    }

    @Override
    public Integer delete(Integer wid) {
        return dao.delete(wid);
    }

    @Override
    public Integer count() {
        return dao.count();
    }

    @Override
    public Integer countPi() {
        return dao.countPi();
    }

    @Override
    public List<WorkHome> getList(int pageIndex, int pageSize) {
        return dao.getList(pageIndex, pageSize);
    }

    @Override
    public List<WorkHome> comment(Integer wid) {
        return dao.comment(wid);
    }

    @Override
    public Integer CommentAdd(Comment c) {
        return dao.CommentAdd(c);
    }

    @Override
    public void xiugaipinglunren(Integer wid) {
        dao.xiugaipinglunren(wid);
    }

    @Override
    public void addMsg(Contact contact) {
        dao.addMsg(contact);
    }

    @Override
    public User findutu(String name) {
        return dao.findutu(name);
    }


}
