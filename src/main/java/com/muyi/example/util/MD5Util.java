package com.muyi.example.util;

import java.security.MessageDigest;

/**
 * @author 历川
 * @version 1.0
 * @description MD5加密工具类
 * @date 2023/8/7 10:47
 */
public class MD5Util {
    /**
     * 功能描述:
     * 〈将字符串转成byte数组〉
     *
     * @params : [s]
     * @return : byte[]
     * @author : cwl
     * @date : 2019/10/18 11:47
     */
    private static byte[] md5(String s) {
        MessageDigest algorithm;
        try {
            algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(s.getBytes("UTF-8"));
            byte[] messageDigest = algorithm.digest();
            return messageDigest;
        } catch (Exception e) {
            //此处建议转日志记录
            System.out.println("MD5 Error..."+e.getMessage());
        }
        return null;
    }
    
    /**
     * 功能描述:
     * 〈将Byte[]转成16进制〉
     *
     * @params : [hash]
     * @return : java.lang.String
     * @author : cwl
     * @date : 2019/10/18 11:47
     */
    private static final String toHex(byte hash[]) {
        if (hash == null) {
            return null;
        }
        StringBuffer buf = new StringBuffer(hash.length * 2);
        int i;
        
        for (i = 0; i < hash.length; i++) {
            if ((hash[i] & 0xff) < 0x10) {
                buf.append("0");
            }
            buf.append(Long.toString(hash[i] & 0xff, 16));
        }
        return buf.toString();
    }
    
    /**
     * 功能描述:
     * 〈实现MD5加密〉
     *
     * @params : [s]
     * @return : java.lang.String
     * @author : cwl
     * @date : 2019/10/18 11:47
     */
    public static String hash(String s) {
        try {
            return new String(toHex(md5(s)).getBytes("UTF-8"), "UTF-8");
        } catch (Exception e) {
            System.out.println("not supported charset...{}"+e.getMessage());
            return s;
        }
    }
}
