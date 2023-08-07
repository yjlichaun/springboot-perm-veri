package com.muyi.example.config.exception;

import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.util.SaResult;
import com.alibaba.fastjson.JSONObject;
import com.muyi.example.util.R;
import com.muyi.example.util.constants.ErrorEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 历川
 * @version 1.0
 * @description 统一异常拦截
 * @date 2023/8/7 12:23
 */
@RestControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    
    @ExceptionHandler(value = Exception.class)
    public R defaultErrorHandler(HttpServletRequest req, Exception e) {
        String errorPosition = "";
        //如果错误信息存在
        if (e.getStackTrace().length > 0) {
            StackTraceElement element = e.getStackTrace()[0];
            String fileName = element.getFileName() == null ? "未找到错误文件" : element.getFileName();
            int lineNumber = element.getLineNumber();
            errorPosition = fileName + ":" + lineNumber;
        }
        JSONObject errorObject = new JSONObject();
        errorObject.put("errorLocation",e + "      错误位置 :" + errorPosition);
        logger.error("异常",e);
        return R.error(errorObject, ErrorEnum.E_400.getErrorCode(), ErrorEnum.E_400.getErrorMsg());
    }
    /**
     * GET/POST请求方法错误的拦截器
     * 因为开发时可能比较常见,而且发生在进入controller之前,上面的拦截器拦截不到这个错误
     * 所以定义了这个拦截器
     */
//    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
//    public R httpRequestMethodHandler() {
//        return R.error(new JSONObject(),ErrorEnum.E_500.getErrorCode(),ErrorEnum.E_500.getErrorMsg());
//    }

    @ExceptionHandler({NotPermissionException.class})
    public SaResult handleException(Exception e) {
        e.printStackTrace();
        return SaResult.error(e.getMessage());
    }
}

