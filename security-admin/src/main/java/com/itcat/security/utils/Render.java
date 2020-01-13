package com.itcat.security.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.itcat.common.beans.resp.BaseResponse;
import com.itcat.common.constants.RespCode;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Render {

    public static void respJson(RespCode code, HttpServletResponse httpServletResponse) {
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setCharacterEncoding("utf-8");
        PrintWriter writer = null;
        try {
            writer = httpServletResponse.getWriter();
            writer.write(JSON.toJSONString(new BaseResponse<>().code(code), SerializerFeature.WriteMapNullValue));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
    }

    public static void respJson(int code, String message, HttpServletResponse httpServletResponse) {
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setCharacterEncoding("utf-8");
        PrintWriter writer = null;
        try {
            writer = httpServletResponse.getWriter();
            writer.write(JSON.toJSONString(new BaseResponse<>().setMessage(message).setCode(code), SerializerFeature.WriteMapNullValue));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
    }
}
