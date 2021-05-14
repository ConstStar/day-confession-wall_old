package cn.xiaoxiaoge.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*自定义拦截器 用于给每个都加上跨域的头*/
public class Cors implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //变成http的
        HttpServletResponse resp = (HttpServletResponse) response;
        // 添加参数，允许任意domain访问
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        //禁用缓存，确保网页信息是最新数据
        resp.setHeader("Pragma","No-cache");
        resp.setHeader("Cache-Control","no-cache");
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, HEAD, DELETE, PUT");
        resp.setHeader("Access-Control-Max-Age", "3600");
        resp.setHeader("Access-Control-Allow-Headers",
                "X-Requested-With, Content-Type, Authorization, Accept, Origin, User-Agent, Content-Range, Content-Disposition, Content-Description");

        resp.setDateHeader("Expires", -10);
        chain.doFilter(request, resp);
    }
    public void init(FilterConfig filterConfig) {}


    public void destroy() {}
}