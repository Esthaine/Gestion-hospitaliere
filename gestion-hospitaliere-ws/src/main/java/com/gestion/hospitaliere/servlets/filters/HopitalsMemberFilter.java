package com.gestion.hospitaliere.servlets.filters;

import com.gestion.hospitaliere.model.UserDto;
import com.gestion.hospitaliere.utils.AppConst;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = {"/hopital/*"})
public class HopitalsMemberFilter implements Filter {

    private ServletContext context;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        context = filterConfig.getServletContext();
        this.context.log("HopitalsMemberFilter init ---> Filter context");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);
        if (session == null) {
            this.context.log("User non connecter");
            response.sendRedirect(request.getContextPath() + "/authentication?action=patients");
        }else {
            if (session.getAttribute("authenticated") == null) {
                response.sendRedirect(request.getContextPath() + "/authentication?action=patients");
            }

            UserDto user = (UserDto) session.getAttribute("authenticated");
            if (user.getRoleDto()!=null) {
                if (
                        user.getRoleDto().get(0).getRoleName().equals(AppConst.MEDECIN) ||
                        user.getRoleDto().get(0).getRoleName().equals(AppConst.INFIRMIER) ||
                        user.getRoleDto().get(0).getRoleName().equals(AppConst.ADMIN) ||
                        user.getRoleDto().get(0).getRoleName().equals(AppConst.LABORATOIRE ) ||
                        user.getRoleDto().get(0).getRoleName().equals(AppConst.PHARMACIE)
                ) {
                    if (request.getRequestURI().equals("/")) {
                        response.sendRedirect(request.getContextPath() + "/hopital/dashboard");
                    }
                    filterChain.doFilter(request, response);
                } else {
                    if (user.getRoleDto().get(0).getRoleName().equals(AppConst.PATIENT)) {
                        response.sendRedirect(request.getContextPath() + "/");
                    }
                }
            }
        }

    }

    @Override
    public void destroy() {
    }
}
