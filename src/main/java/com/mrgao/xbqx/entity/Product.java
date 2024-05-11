package com.mrgao.xbqx.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Mr.Gao
 * @date 2024/4/9 16:04
 * @apiNote:
 */
@Data
public class Product implements Serializable {

    private Long id;

    private String pid;

    private Integer storkCount;

    private String prdName;
}
