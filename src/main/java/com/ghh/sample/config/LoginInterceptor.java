package com.ghh.sample.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            response.sendError(401);
//            if (handler instanceof HandlerMethod) {
//                HandlerMethod hm = (HandlerMethod)handler;
//                ResponseBody rp = hm.getMethodAnnotation(ResponseBody.class);
//                if (rp != null) {
//                    response.getWriter().append("{code:1, message:'not login'}");
//                } else {
//                    response.sendRedirect("/login");
//                }
//            }
            return false;
        }
        return true;
    }
}
