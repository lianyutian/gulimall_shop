package com.atguigu.gulimall.shop.shiro;

import com.alibaba.fastjson.JSON;
import com.atguigu.gulimall.shop.common.exception.GuliException;
import com.atguigu.gulimall.shop.common.exception.ResponseCode;
import com.atguigu.gulimall.shop.constants.Constant;
import com.atguigu.gulimall.shop.utils.BaseResponseCode;
import com.atguigu.gulimall.shop.utils.DataResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 自定义shiro过滤器
 *
 * @author lm
 * @since 2020/10/22 20:51
 */
@Slf4j
public class CustomAccessControlFilter extends AccessControlFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        log.info("request 接口地址：{}", request.getRequestURI());
        log.info("request 请求方式：{}", request.getMethod());
        // 获取请求token
        String token = request.getHeader(Constant.ACCESS_TOKEN);
        try {
            if (StringUtils.isEmpty(token)) {
                throw new GuliException(ResponseCode.TOKEN_NOT_NULL);
            }
            CustomToken customToken = new CustomToken(token);
            // 认证
            getSubject(servletRequest, servletResponse).login(customToken);
        } catch (GuliException exception) {
            customResponse(servletResponse, exception.getCode(), exception.getMsg());
            return false;
        } catch (AuthenticationException exception) {
            if (exception.getCause() instanceof GuliException) {
                GuliException guliException = (GuliException) exception.getCause();
                customResponse(servletResponse, guliException.getCode(), guliException.getMsg());
            } else {
                // 认证失败
                customResponse(servletResponse, ResponseCode.TOKEN_ERROR.getCode(), ResponseCode.TOKEN_ERROR.getMsg());
            }
        } catch (Exception exception) {
            customResponse(servletResponse, ResponseCode.SYSTEM_ERROR.getCode(), ResponseCode.SYSTEM_ERROR.getMsg());
            return false;
        }
        return false;
    }

    /**
     * 自定义响应前端
     *
     * @param response 响应体
     * @param code 状态码
     * @param msg 响应信息
     */
    private void customResponse(ServletResponse response, int code, String msg) {
        DataResult result = new DataResult(code, msg);
        //response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.setCharacterEncoding("UTF-8");
        String jsonString = JSON.toJSONString(result);
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(jsonString.getBytes("UTF-8"));
            outputStream.flush();
        } catch (IOException exception) {
            log.error("customResponse error:{}", exception);
        }
    }
}
