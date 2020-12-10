package com.mungwincore.transcore.interceptors;

import com.mungwincore.transcore.utils.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

public class TransInterceptor extends HandlerInterceptorAdapter {
    private static Logger logger = LoggerFactory.getLogger(TransInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpUtils.setLang(getLang(request));
        return true;
    }

    private String getLang(HttpServletRequest request) {
        Enumeration<?> e = request.getHeaderNames();
        if (e != null) {
            while (e.hasMoreElements()) {
                String curr = (String) e.nextElement();
                if (curr.equals("lang")) {
                    String lang = request.getHeader(curr);
                    logger.info("[Current Lang]: {}", lang);
                    return lang;
                }
            }
        }
        logger.info("[Current Lang]: en");
        return "en";
    }
}
