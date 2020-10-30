package com.crazyandcoder.university.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.Map;

public class LogInterceptor extends HandlerInterceptorAdapter {

    private Logger logger = LoggerFactory.getLogger(LogInterceptor.class);
    private NamedThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<Long>("StopWatch-startTimed");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("*******************业务请求开始**********************");
        try {
            long timed = System.currentTimeMillis();
            startTimeThreadLocal.set(timed);
            String uri = request.getRequestURI();
            String remoteAddr = getIpAddr(request);
            String method = request.getMethod();
            logger.info("请求路径URL：" + uri);
            logger.info("请求方式:" + method);
            logger.info("请求客户端地址：" + remoteAddr);
            Map<String, String[]> params = request.getParameterMap();
            if (!params.isEmpty()) {
                logger.info("当前请求参数如下：");
                //输出请求参数
                outputMap(request.getParameterMap());
            }
        } catch (Exception e) {
            logger.error("业务请求-拦截器异常：", e);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        long endTime = System.currentTimeMillis();
        long beginTime = startTimeThreadLocal.get();
        long consumeTime = endTime - beginTime;
        if (consumeTime > 500) {
            logger.warn(String.format("%s 请求耗时 %d 毫秒", request.getRequestURI(), consumeTime));
        }
        logger.info("*******************业务请求结束**********************");
    }

    private String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public void outputMap(Map<String, String[]> map) {
        for (Iterator<String> iterator = map.keySet().iterator(); iterator.hasNext(); ) {
            String key = iterator.next();
            String value = null;
            if (null == map.get(key)) {
                value = "";
            } else if (map.get(key) instanceof String[]) {
                String[] values = (String[]) map.get(key);
                for (int i = 0; i < values.length; i++) {
                    value = values[i] + ",";
                }
                value = value.substring(0, value.length() - 1);
            } else {
                value = map.get(key).toString();
            }
            logger.info("参数名：" + key + " 参数值：" + value);
        }
    }
}

