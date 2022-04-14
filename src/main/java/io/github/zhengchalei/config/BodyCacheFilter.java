package io.github.zhengchalei.config;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 构建一个自定义的 HttpServletRequestWrapper, 放在最前面一个过滤器, 后面拿到的都是 HttpServletRequestWrapper 子类的 HttpServletRequestWrapper
 * 如果强转为其他的子类会 出现 ClassCastException, 所以后面的 HttpServletRequestWrapper 需要继承 BodyCacheHttpServletRequestWrapper 而不是  HttpServletRequestWrapper
 * @author 郑查磊
 */
@Order(Integer.MIN_VALUE)
public class BodyCacheFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        BodyCacheHttpServletRequestWrapper requestWrapper = new BodyCacheHttpServletRequestWrapper((HttpServletRequest) servletRequest);
        filterChain.doFilter(requestWrapper, servletResponse);
    }

    @Override
    public void destroy() {

    }

}
