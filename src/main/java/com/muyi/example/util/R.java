package com.muyi.example.util;

import com.muyi.example.util.constants.Constants;
import lombok.*;

import java.io.Serializable;

/**
 * @author 历川
 * @version 1.0
 * @description 统一返回类
 * @date 2023/8/7 12:06
 */
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class R<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Getter
    @Setter
    private String code;
    
    @Getter
    @Setter
    private String msg;
    
    @Getter
    @Setter
    private T data;
    
    public static <T> R<T> ok() {
        return restResult(null, Constants.SUCCESS_CODE, null);
    }
    
    public static <T> R<T> ok(T data) {
        return restResult(data, Constants.SUCCESS_CODE, null);
    }
    
    public static <T> R<T> ok(T data, String msg) {
        return restResult(data, Constants.SUCCESS_CODE, msg);
    }
    
    public static <T> R<T> ok (String msg) {return restResult(null, Constants.SUCCESS_CODE,msg);}
    
    public static <T> R<T> failed() {
        return restResult(null, Constants.FAIL, null);
    }
    
    public static <T> R<T> failed(String msg) {
        return restResult(null, Constants.FAIL, msg);
    }
    
    public static <T> R<T> failed(T data) {
        return restResult(data, Constants.FAIL, null);
    }
    
    public static <T> R<T> failed(T data, String msg) {
        return restResult(data, Constants.FAIL, msg);
    }
    public static <T> R<T> failed(T data, String code, String msg) {
        return restResult(data, code, msg);
    }
    
    public static <T> R<T> result(T data, String code, String msg) {
        return restResult(data, code, msg);
    }
    
    public static <T> R<T> error(T data, String code, String msg) {
        return restResult(data, code, msg);
    }
    
    public static <T> R<T> restResult(T data, String code, String msg) {
        R<T> apiResult = new R<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }
    
    public Boolean isSuccess() {
        return (this.code.equals(Constants.SUCCESS_CODE));
    }
    
}
