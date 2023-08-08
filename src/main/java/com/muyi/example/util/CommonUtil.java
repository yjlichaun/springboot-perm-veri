package com.muyi.example.util;

import com.muyi.example.vo.UserVo;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 历川
 * @version 1.0
 * @description 工具类
 * @date 2023/8/7 18:51
 */
public class CommonUtil {
    
    private static final Integer DEFAULT_PAGE_SIZE = 5;
    
    public static <T> Map<Integer,List<T>> pagination(Integer pageSize,List<T> list,Integer pageNum) {
        if (pageSize == null || pageSize == 0) {
            pageSize = DEFAULT_PAGE_SIZE;
        }
        Map<Integer,List<T>> result = new HashMap<Integer,List<T>>();
        Map<Integer, List<T>> res = getListAsMap(pageSize, list);
        for(Map.Entry<Integer,List<T>> entry : res.entrySet()) {
            if (entry.getKey().equals(pageNum)){
                result.put(pageNum,entry.getValue());
                break;
            }
        }
        return result;
    }
    
    public static <T> Map<Integer,List<T>> pagination(List<T> list,Integer pageNum){
        return pagination(0,list,pageNum);
    }
    
    public static <T> Integer getPageNum(List<T> list,Integer pageSize) {
        Integer cnt = 0;
        Integer pageCnt = 1;
        if (pageSize == null || pageSize == 0) {
            pageSize = DEFAULT_PAGE_SIZE;
        }
        for (T t : list) {
            if (cnt.equals(pageSize)) {
                cnt = 0;
                pageCnt++;
            }
            cnt++;
        }
        if(cnt != 0) {
            pageCnt++;
        }
        return pageCnt;
    }
    
    public static <T> Integer getPageNum(List<T> list){
        return getPageNum(list,0);
    }
    public static <T> Map<Integer,List<T>> getListAsMap(Integer pageSize,List<T> list) {
        Integer pageNum = 0;
        if (pageSize == null || pageSize == 0) {
            pageNum = getPageNum(list);
            pageSize = DEFAULT_PAGE_SIZE;
        }else {
            pageNum = getPageNum(list,pageSize);
        }
        Map<Integer,List<T>> res = new HashMap<Integer,List<T>>();
        List<T> tmp = new ArrayList<T>();
        Integer cnt = 0;
        Integer pageCnt = 0;
        for (T t : list) {
            if (cnt.equals(pageSize)) {
                res.put(pageCnt,new ArrayList<>(tmp));
                tmp.clear();
                cnt = 0;
                pageCnt++;
            }
            tmp.add(t);
            cnt++;
        }
        if(cnt != 0) {
            pageCnt++;
            res.put(pageCnt,new ArrayList<>(tmp));
        }
        return res;
    }
}
