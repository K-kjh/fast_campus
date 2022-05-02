package com.example.filter.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.LogRecord;

@Slf4j
@Component
@WebFilter(urlPatterns = "/api/user/*") // 원하는 url 패턴
public class GlobalFilter implements Filter {




    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
         /*   출력값을 보고 싶어 읽어버리면 값이 사라지기 떄문에 copy
             ContentCaching ~~ Wrapper 으로 받아줄 필요가있다.*/
        //전처리
        ContentCachingRequestWrapper  httpServletRequest =
                    new ContentCachingRequestWrapper((HttpServletRequest) request);
        ContentCachingResponseWrapper httpServletResponse =
                    new ContentCachingResponseWrapper((HttpServletResponse) response);

        String url = httpServletRequest.getRequestURI();
         /*
        BufferedReader br = httpServletRequest.getReader();
         br.lines().forEach(line ->{
           log.info("line : "+  line);
        });
       */
        chain.doFilter(httpServletRequest,httpServletResponse);
        //후 처리
        /* httpServletResponse.copyBodyToResponse(); 해줘야 값이 안 날라간다.   */

        //default : utf8
        String reqcontent = new String(httpServletRequest.getContentAsByteArray());
      //  log.info("url  : {} , reqcontent : {} "+ url,reqcontent);

        String resContent = new String(httpServletResponse.getContentAsByteArray());
        int httpStatus = httpServletResponse.getStatus();

        httpServletResponse.copyBodyToResponse();
      //  log.info("response status : {} , reponseBody : {} "+ httpStatus,resContent);


    }
}
