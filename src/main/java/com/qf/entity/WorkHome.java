package com.qf.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkHome {
    private Integer wid;
    private String wtitle;
    private String wcontent;
    private String uname;
    private Date wdate;
    private Integer uid;
    private List<Comment> comments;
}
