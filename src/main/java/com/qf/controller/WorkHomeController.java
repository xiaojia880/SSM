package com.qf.controller;

import com.qf.entity.*;
import com.qf.service.UserService;
import com.qf.service.WorkHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class WorkHomeController {
    @Autowired
    private WorkHomeService service;
    @Autowired
    private UserService userService;

    @RequestMapping("towrite")
    public String towrite() {
        return "write";
    }

    @RequestMapping("toMydesk")
    public String toMyDesk(Map map) {
        System.err.println("------------------------------------------------------");
        Integer count = service.count();
        Integer countPi = service.countPi();
        map.put("count", count);
        map.put("countPi", countPi);
        return "Mydesk";
    }

//    @RequestMapping("toWenzhang")
//    public String towenzhanggg(Integer wid,Map map){
//
//        List<Comment> list = service.getall();
//        map.put("list",list);
//        Integer deletep = service.deletep(wid);
//        return "wenzhang";
//    }

    @RequestMapping("toWenzhang")
    public String towenzhang(Map map) {
        List<WorkHome> all = service.getAll();
        map.put("all", all);
        return "wenzhang";
    }

    @RequestMapping("toTongji")
    public String totongji() {
        return "tongji";
    }

    @RequestMapping("/getOneByWid/{id}")
    public String getOneByWid(@PathVariable("id") Integer id, Map map) {
        WorkHome alls = service.getOneByWid(id);
        List<WorkHome> lists = service.getAll();
        List<WorkHome> comment = service.comment(id);
        List<xianshi> xs = new ArrayList<>();
        String s = null;
        String str = "yyyy-MM-dd hh:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str);

        //动态内容
        boolean flag = true;
        for (int i = comment.size() - 1; i >= 0; i--) {
            xianshi xx = new xianshi();
            for (int j = 0; j < (comment.size() - 1); j++) {
                if (comment.get(j).getWtitle().equals(comment.get(j + 1).getWtitle())) {
                    if (!flag) {
                        continue;
                    }
                    xx.setWtitle(comment.get(i).getWtitle());
                    flag = false;

                }
            }

            String s1 = simpleDateFormat.format(comment.get(i).getWdate());
            xx.setWdate(s1);

            List<Comment> mmet = comment.get(i).getComments();
            for (Comment c : mmet) {
                s = c.getCommenttext();
                xx.setCommenttext(s);
                String date2 = simpleDateFormat.format(c.getCommentdate());
                xx.setCommentdate(date2);
                List<User> n = c.getUsers();
                for (User u : n) {
                    xx.setName(u.getUname());
                }
            }
            User user = service.findutu(xx.getName());
            xx.setUtu(user.getUtu());
            xs.add(xx);
        }

        System.err.println("----------------------------------" + xs);
        System.err.println("----------------------------------" + alls);
        System.err.println("----------------------------------" + lists);
        map.put("cot", xs);
        map.put("alls", alls);
        map.put("lists", lists);
        return "single";
    }

    @RequestMapping("/getOneByTitle")
    public String getOneByWname(HttpServletRequest request, Map map) {
        String text = request.getParameter("text");
        if (text == null || "".equals(text)) {
            return "searchError";
        }
        List<WorkHome> oneByWid = service.getOneByTitle(text);
        map.put("alls", oneByWid);
        return "blog";
    }

    @RequestMapping("/insert")
    public String add(WorkHome workHome, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        User user = userService.getOne(email);
        workHome.setUname(user.getUname());
        workHome.setUid(user.getUid());
        Date date = new Date();
        workHome.setWdate(date);
        System.out.println(workHome);
        Integer add = service.add(workHome);
        System.out.println(add);
        return "redirect:/getAll";
    }

//    @RequestMapping("/updates")
//    public void update() {
//        WorkHome workHome = new WorkHome();
//        Date date = new Date();
//        workHome.setWtitle("110");
//        workHome.setWcontent("asdsadsa");
//        workHome.setUname("hh");
//        workHome.setWid(3);
//        Integer update = service.update(workHome);
//        System.out.println(update);
//    }

    @RequestMapping("/todelete")
    public String delete(HttpServletRequest request) {
        Integer wid = Integer.parseInt(request.getParameter("wid"));
        System.err.println("-------------------------------" + wid);
        service.xiugaipinglunren(wid);
        service.delete(wid);
        return "redirect:/selectOne";

    }

    @RequestMapping("/getAll")
    public String getList(PageBean pageBean, Map map) {
        int pageIndex = 1;//当前页数
        int pageSize = 4;//每页显示的数量
        int count = service.count();//总数量
        System.out.println(count);
        int pageMax = (count + pageSize - 1) / pageSize;//总页数
        if (!(pageBean.getPageIndex() == null || "".equals(pageBean.getPageIndex()))) {
            pageIndex = pageBean.getPageIndex();
        }
        if (pageIndex <= 0) {
            pageIndex = 1;
        }
        if (pageIndex > pageMax) {
            pageIndex = pageMax;
        }
        List<WorkHome> list = service.getList((pageIndex - 1) * pageSize, pageSize);
        map.put("alls", list);
        map.put("pagebean", new PageBean(pageIndex, pageMax));

        return "blog";
    }

    @RequestMapping("/addmsg")
    public String addmsg(Contact contact) {
        Date date = new Date();
        contact.setTdate(date);
        service.addMsg(contact);
        return "b";
    }

    @RequestMapping("/todeletewenzhang")
    public String todeletewenzhang(HttpServletRequest request) {
        Integer wid = Integer.parseInt(request.getParameter("wid"));
        System.err.println("-------------------------------" + wid);
        service.xiugaipinglunren(wid);
        service.delete(wid);
        return "redirect:/toWenzhang";
    }

    @RequestMapping("/getWenzhangByTitle")
    public String getWenzhangByTitle(HttpServletRequest request, Map map) {
        String text = request.getParameter("text");
        System.err.println("------------------------------" + text);
        if (text == null || "".equals(text)) {
            return "searchError";
        }
        List<WorkHome> oneByWid = service.getOneByTitle(text);
        map.put("all", oneByWid);
        return "wenzhang";
    }
}
