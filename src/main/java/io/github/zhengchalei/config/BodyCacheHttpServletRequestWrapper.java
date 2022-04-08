package io.github.zhengchalei.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * BodyCacheHttpServletRequestWrapper
 *
 * @author 郑查磊
 * @date 2022年4月8日00:30:27
 */
@Primary
@Configuration
public class BodyCacheHttpServletRequestWrapper extends HttpServletRequestWrapper {

    private byte[] bodyBytes = null;

    public BodyCacheHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        // 第一次缓存
        if (bodyBytes == null) bodyBytes = getBytes(super.getInputStream());
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bodyBytes);
        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }

            @Override
            public int read() {
                return byteArrayInputStream.read();
            }
        };
    }

    private byte[] getBytes(InputStream in) throws IOException {
        ByteArrayOutputStream byteOs = new ByteArrayOutputStream();
        int data;
        while ((data = in.read()) != -1) {
            byteOs.write(data);
        }
        return byteOs.toByteArray();
    }
}
