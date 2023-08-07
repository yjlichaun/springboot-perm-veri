package com.muyi.example.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author 历川
 * @version 1.0
 * @description 基础类
 * @date 2023/8/7 10:34
 */
@Data
public class BaseEntity {
    
    public String createTime;
    
    public String updateTime;
    
    public String deleteStatus;
}
