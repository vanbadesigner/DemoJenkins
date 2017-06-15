/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vanba.stock.filter;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author FRAMGIA\nguyen.van.ba
 */
@WebFilter(filterName = "SecurityFilter", urlPatterns = {"/*"})
public class SecurityFilter implements Filter {

    private static final boolean debug = true;

    private FilterConfig filterConfig = null;

    public SecurityFilter() {
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        if (httpRequest.getRequestURI().contains("/login.do")) {
            chain.doFilter(request, response);
            return;
        }
        
        HttpSession session = httpRequest.getSession(false);
        String tocken = session == null ? null : (String) session.getAttribute("AppUserTocken");
        
        if (httpRequest.getRequestURI().contains("/home.do")) {
            if (tocken != null) {
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/main.do");
            } else {
                chain.doFilter(request, response);
            }
        } else {
            if (tocken != null) {
                chain.doFilter(request, response);
            } else {
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/home.do");
            }

        }
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("SecurityFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("SecurityFilter()");
        }
        StringBuffer sb = new StringBuffer("SecurityFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

}
