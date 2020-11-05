package com.qf.controller;

import com.qf.entity.Comment;
import com.qf.service.WorkHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@Controller
public class testConteoller {

    @Autowired
    private WorkHomeService service;

    @RequestMapping(value = "/commentAdd", method = RequestMethod.POST)
    public String CommentAdd(Integer uid, String commenttext, Integer wid) throws UnsupportedEncodingException {
        String[] ms = {"傻逼", "操", "妈逼"};
        for (String mm : ms) {
            //contains()判断是否含有ms中的字符
            boolean flag = commenttext.contains(mm);
            if (flag) {
                String co = "";
                for (int i = 0; i < mm.length(); i++) {
                    co += "*";
                }
                //返回一个新字符串
                commenttext = commenttext.replace(mm, co);
            }
        }

        Date date = new Date();
        Comment comment = new Comment(uid, commenttext, date, wid);
        Integer i = service.CommentAdd(comment);
        if (i > 0) {
            System.out.println("添加成功");
        } else {
            System.out.println("添加失败");
        }
        return "redirect:/getOneByWid/" + wid;
    }
}
