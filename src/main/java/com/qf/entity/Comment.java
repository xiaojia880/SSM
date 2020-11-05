package com.qf.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    //用户id
    private Integer uid;
    //评论内容
    private String commenttext;
    //评论时间
    private Date commentdate;
    //动态id
    private Integer wid;

    private List<User> users;

    public Comment(Integer uid, String commenttext, Date commentdate, Integer wid) {
        this.uid = uid;
        this.commenttext = commenttext;
        this.commentdate = commentdate;
        this.wid = wid;
    }
}
