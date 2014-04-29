package org.yangchigi.support;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharsetFilter implements Filter {
	private static final String DEFAULT_ENCODING = "UTF-8";
	private String encoding;	

	public void init(FilterConfig config) throws ServletException {
		encoding = config.getInitParameter("requestEncoding");
	}
	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain next) throws IOException, ServletException {
		if (null == request.getCharacterEncoding())
			request.setCharacterEncoding(encoding);
		
		response.setContentType(String.format("text/html; charset=%s", DEFAULT_ENCODING));
		response.setCharacterEncoding(DEFAULT_ENCODING);
		
		next.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}
}