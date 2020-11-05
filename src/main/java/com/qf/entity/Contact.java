package com.qf.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contact {
    private Integer tid;
    private String tname;
    private String temail;
    private String ttitle;
    private String temg;
    private Date tdate;

}
