package com.example.restapi.component;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.Duration;

/**
 * Throttling 처리를 위한 필터 적용
 */
@Component
public class ThrottlingFilter implements Filter {

    // 초당 최대 접속 횟수 제한
    private Bucket createNewBucket() {
        long overdraft = 4;
        Refill refill = Refill.greedy(10, Duration.ofSeconds(1));
        Bandwidth limit = Bandwidth.classic(overdraft, refill);
        return Bucket4j.builder().addLimit(limit).build();
    }

    /**
     * url 접근시 최대 접속수 제한을 위한 필터
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpSession session = httpRequest.getSession(true);

        // appkey를 session id로 적용하여 bucket 확인
        //String appKey = SecurityUtils.getThirdPartyAppKey();
        String appkey = session.getId();
        Bucket bucket = (Bucket) session.getAttribute("throttler-" + appkey);
        if (bucket == null) {
            bucket = createNewBucket();
            session.setAttribute("throttler-" + appkey, bucket);
        }

        // tryConsume returns false immediately if no tokens available with the bucket
        if (bucket.tryConsume(1)) {
            // the limit is not exceeded
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            // limit is exceeded
            HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
            httpResponse.setContentType("text/plain");
            httpResponse.setStatus(429);
            httpResponse.getWriter().append("Too many requests");
        }
    }
}
