package io.github.zhengchalei.config;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 用户转换 Request
 *
 * @author 郑查磊
 * @date 2022年4月8日00:30:19
 */
@Order(Integer.MIN_VALUE)
@Component
public class BodyCacheFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        BodyCacheHttpServletRequestWrapper bladeRequest = new BodyCacheHttpServletRequestWrapper((HttpServletRequest) servletRequest);
        filterChain.doFilter(bladeRequest, servletResponse);
    }

}
