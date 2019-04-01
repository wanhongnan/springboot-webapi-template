package warp.home.core;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

@WebFilter(urlPatterns = "/api/*",filterName = "WrapperJsonResponseFilter")
public class WrapperJsonResponseFilter implements Filter {

    @Override
    public void destroy() {
    }

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException 
	{
		HttpServletResponse response = (HttpServletResponse)servletResponse;
	    WrapperResponse wrapperResponse = new WrapperResponse((HttpServletResponse)response);

	    chain.doFilter(request, wrapperResponse);
	    byte[] content = wrapperResponse.getContent();
	    if(content.length > 0) {
	    	servletResponse.reset();
		    response.setHeader("Content-Type", "application/json;charset=UTF-8");
		    String str = new String(content); 
		    JSONObject obj = JSONObject.parseObject(str);
		    boolean hasCode = false;
		    if(obj != null)
		    	hasCode = obj.containsKey("code");
		    int status = response.getStatus();
		    if(!hasCode && status == 200) {
		    	str = "{\"code\":200,\"msg\":\"ok\",\"data\":" + str + "}";
		    }
		    ServletOutputStream out = response.getOutputStream();
		    out.write(str.getBytes());
		    out.flush();
	    }
	}
}






