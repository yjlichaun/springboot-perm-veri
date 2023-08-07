package com.muyi.example.entity;

import lombok.Data;

/**
 * @author 历川
 * @version 1.0
 * @description
 * @date 2023/8/7 10:58
 */

@Data
public class Article extends BaseEntity {
    
    /**
     * 文章id
     */
    private Integer id;
    
    /**
     * 用户
     */
    private String content;
}
