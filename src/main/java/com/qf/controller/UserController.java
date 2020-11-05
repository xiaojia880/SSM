package com.qf.controller;

import com.qf.entity.User;
import com.qf.entity.WorkHome;
import com.qf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserService service;

    @RequestMapping("/toLogin")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("/toIndex")
    public String toIndex() {
        return "index";
    }

    @RequestMapping("/find")
    public String tofindpsd() {
        return "findpsd";
    }

    @RequestMapping("/toInfo")
    public String toinfo() {
        return "info";
    }

    @RequestMapping("/getpassword")
    public String get(String email, Map map) {

        User getpsd = service.getpsd(email);
        if (getpsd == null) {
            return "ppppp";
        }
        map.put("pppp", getpsd);
        return "pppp";
    }

    @RequestMapping(value = "/loginCheck", method = RequestMethod.POST)
    public String login(String email, String password, String remember, HttpServletRequest request, HttpServletResponse response) {
        if (remember != null) {
            Cookie cookie = new Cookie("user", email + ":" + password);
            cookie.setMaxAge(60 * 60);
            cookie.setPath("/");
            response.addCookie(cookie);
        } else {
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                if ("user".equals(cookie.getName())) {
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                }
            }
        }
        List<User> users = service.getAll();
        boolean falg = true;
        for (User us : users) {
            if (email.equals(us.getUemail())) {
                if (password.equals(us.getUpassword())) {
                    System.out.println("登录成功");
                    HttpSession session = request.getSession();
                    session.setAttribute("email", email);
                    return "forward:/getAll";
                } else {
                    System.out.println("用户名或者密码错误");
                    return "passwordError";
                }
            } else if ("970458973@qq.com".equals(email) && "12345".equals(password)) {
                System.err.println("-------------------------------------------管理通过");
                HttpSession session = request.getSession();
                session.setAttribute("youxiang", "970458973@qq.com");
                return "houtaiIndex";
            }
            falg = false;
        }
        if (!falg) {
        }
        return null;
    }

    @RequestMapping(value = "/add")
    public String register(User user, HttpServletRequest request) {

        List<User> users = service.getAll();
        boolean flag = true;
        for (User us : users) {
            if (user.getUemail().equals(us.getUemail())) {
                flag = false;
            }
        }
        if (!flag) {
            System.out.println("该用户已存在");
            return "registerError";
        }

        if (flag) {

            String upassword = request.getParameter("upassword");
            System.out.println(upassword);
            if (upassword.length() < 5) {
                System.out.println("密码长度至少为5位");
                return "xhError";
            }
            String uphone = request.getParameter("uphone");
            System.out.println(uphone);
            if (uphone.length() != 11) {
                System.out.println("电话长度为11位");
                return "ysError";
            }
            user.setUtu("avatar.png");
            Integer i = service.addOne(user);
            if (i > 0) {
                System.out.println("注册成功");
                return "login";
            }
        }
        return null;
    }

    @RequestMapping("/selectOne")
    public String getWorkHomeList(Map map, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        User user = service.getOne(email);
        System.err.println(user);
        List<User> users = service.getWorkHomeList(user.getUid());
        List<WorkHome> homes = new ArrayList<>();
        map.put("users", users);
        for (User user1 : users) {
            for (int i = user1.getWorkHome().size() - 1; i >= 0; i--) {
                homes.add(user1.getWorkHome().get(i));
            }
        }
        System.err.println(homes);
        map.put("homes", homes);
        return "info";
    }

    @RequestMapping("/toUpdate")
    public String toUpdate(String email, Map map) {
        User user = service.getOne(email);
        map.put("user", user);
        return "toupdate";
    }

    @RequestMapping("/update")
    public String update(User user, HttpServletRequest request) {
        System.out.println(user);
        user.setUtu(originalFilename);
        service.update(user);
        return "forward:/selectOne";
    }

    /*上传消息的功能*/
    String originalFilename = null;

    @RequestMapping("/upload")
    public String getFile(@RequestParam("file") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            /*获取上传的文件名*/
            originalFilename = file.getOriginalFilename();
            System.out.println("文件名:" + originalFilename);
            /*获取文件的大小*/
            long size = file.getSize();
            System.out.println("文件大小:" + size);
            File f = new File("C:/Users/97045/IdeaProjects/SSM/src/main/webapp/tu");
            if (!f.exists()) {
                f.mkdirs();
            }
            /*保存地址*/
            /*父路径下得到子文件*/
            file.transferTo(new File(f, originalFilename));
            System.out.println("文件上传成功");
        } else {
            System.out.println("文件上传失败");
        }
        return "forward:/update";

    }

    @RequestMapping("toUser")
    public String touser(Map map) {
        List<User> users = service.getAll();
        map.put("users", users);

        return "user";
    }

    @RequestMapping("deleteuser")
    public String deleteuser(HttpServletRequest request) {
        Integer uid = Integer.parseInt(request.getParameter("uid"));
        service.delete(uid);
        return "redirect:/toUser";
    }
}
