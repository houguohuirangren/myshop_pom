package com.qf.entity;

import lombok.*;

import java.io.Serializable;

/**
 * @Author LWW
 * @Time Created by Enzo Cotter on 2018/11/19.
 * @Version 1.0
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Goods implements Serializable {
    private Integer id;

    private String title;

    private String ginfo;


    private Integer gcount;


    private Integer tid=1;

    private Double allprice;

    private Double price;

    private String gimage;


}
