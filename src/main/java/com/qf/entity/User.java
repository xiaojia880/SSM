
package com.qf.entity;

/*
 * data  自带的getset方法  和  tostring
 * 自带的构造方法
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer uid;
    private String uname;
    private String upassword;
    private String uphone;
    private String uemail;
    private String utu;
    private List<WorkHome> workHome;


}

