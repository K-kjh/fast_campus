package com.example.filter.interceptor;

import com.example.filter.annotation.Auth;
import com.example.filter.exception.AuthException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@Slf4j
@Component/* 스프링에 의해서 관리가 되여야 하기 떄문에 삽입 */
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /** 정보 저장 손실 대비 (fact. Filter)
         *
         *  여기서도 req,res 의 body를 읽으면 손실이 된다.
         *  하지만
         *
         *   @see com.example.filter.filter.GlobalFilter#doFilter 이 메소드에서
         *   ContentCachingRequestWrapper를 만들어서
         *     chain.doFilter(httpServletRequest,httpServletResponse); 을 해주면 형 변환이 가능하다.
         *
         *  filter 단에서 servletHttpServletRequest 에서 넘어가게 되면 형변환이 불가능 하다.
        *
        */
        String url = request.getRequestURI();

        URI uri = UriComponentsBuilder.fromUriString(request.getRequestURI())
                        .query(request.getQueryString())
                        .build()
                        .toUri();

        log.info("request url : {} ",url);
        boolean hasAnnotaction = checkAnnotation(handler, Auth.class);

        if(hasAnnotaction) {
            String query = uri.getQuery();
            if( query == null || !query.equals("name=steve"))
                throw new AuthException();

            return true;
        }
        return false;
    }

    private boolean checkAnnotation(Object handler, Class clazz){
        if(handler instanceof ResourceHttpRequestHandler) return true;

        HandlerMethod handlerMethod = (HandlerMethod) handler;

        //auth annotaction 이 있을떄 true
        if(null != handlerMethod.getMethodAnnotation(clazz) ||
                null != handlerMethod.getBeanType().getAnnotation(clazz))
                     return true;

        return false;
    }
}
