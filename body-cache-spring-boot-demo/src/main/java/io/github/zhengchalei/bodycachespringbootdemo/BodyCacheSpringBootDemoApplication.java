package io.github.zhengchalei.bodycachespringbootdemo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * 测试
 *
 * @author stone
 * @date 2022/04/08
 */
@RestController
@SpringBootApplication
public class BodyCacheSpringBootDemoApplication {

    private final Logger logger = LoggerFactory.getLogger(BodyCacheSpringBootDemoApplication.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping
    public void test(HttpServletRequest request) throws IOException {
        for (int i = 0; i < 2; i++) {
            Map<Object, Object> map = objectMapper.readValue(request.getInputStream(), Map.class);
            logger.info("request: {}, body: {}", request, map);
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(BodyCacheSpringBootDemoApplication.class, args);
    }

}
