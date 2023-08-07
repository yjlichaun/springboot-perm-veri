package com.muyi.example.util;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.ToString;

/**
 * @author 历川
 * @version 1.0
 * @description 日志类
 * @date 2023/8/7 14:13
 */
@Data
@ToString
public class Log {
    
    public String url;
    
    public Long took;
    
    public Object userId;
    
    public JSONObject req;
    
    public String res;
}
