package cn.wbnull.springcloudzuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 服务过滤
 *
 * @author dukunbiao(null)  2019-04-30
 * https://github.com/dkbnull/SpringCloudDemo
 */
@Slf4j
@Component
public class GlobalFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest servletRequest = context.getRequest();

        String requestUri = servletRequest.getRequestURI();
        if (requestUri.contains("v2/api-docs")
                || requestUri.contains("swagger-resources")
                || requestUri.contains("swagger-ui")
                || requestUri.contains("webjars")) {
            return null;
        }

        Object token = servletRequest.getHeader("token");
        if (token == null) {
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(401);
            try {
                context.getResponse().getWriter().write("error: token is null");
            } catch (IOException e) {
                log.error("write response error", e);
            }

            return null;
        }

        return null;
    }
}