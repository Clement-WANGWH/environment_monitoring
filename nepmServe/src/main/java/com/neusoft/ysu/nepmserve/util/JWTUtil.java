package com.neusoft.ysu.nepmserve.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.HashMap;

public class JWTUtil {
    /**
     * 密钥要自己保管好
     */
    private static String SECRET = "!Q@W#E$R";

    public  static   String buildToken(HashMap<String, String> map) throws UnsupportedEncodingException {
        JWTCreator.Builder builder = JWT.create();
        //payload
        map.forEach((k,v)->{
            builder.withClaim(k,v);
        });
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE, 3); //默认3天过期
        builder.withExpiresAt(  instance.getTime()  );//指定令牌的过期时间
        return builder.sign(Algorithm.HMAC256(SECRET));

    }


    /**
     * 验证token 合法性
     */
    public static DecodedJWT verify(String token) throws UnsupportedEncodingException {
        //如果有任何验证异常，此处都会抛出异常
        return JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
    }



    public static void main(String[] args) throws UnsupportedEncodingException {
//        HashMap<String ,String> map =new HashMap<>();
//        map.put("logname" , "admin");
//
//        String token =  buildToken(map);
//        System.out.println(token);
        String token ="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJsb2duYW1lIjoiYWRtaW4iLCJleHAiOjE2OTMwMjA0NDF9.MdiuiIm_1IZ-fO7_ZFU1AkA5f7Se6ouRxGwuhg1G--g";
        DecodedJWT  decodedJWT =   verify(token);
    }


}
