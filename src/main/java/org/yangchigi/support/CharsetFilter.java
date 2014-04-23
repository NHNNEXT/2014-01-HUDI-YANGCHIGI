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
	private String encoding = DEFAULT_ENCODING;	

	public void init(FilterConfig config) throws ServletException {
		encoding = config.getInitParameter("requestEncoding");
	}
	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain next) throws IOException, ServletException {
		// Respect the client-specified character encoding
		// (see HTTP specification section 3.4.1)
		if (null == request.getCharacterEncoding())
			request.setCharacterEncoding(encoding);

		/**
		 * Set the default response content type and encoding
		 */
		
		response.setContentType(String.format("text/html; charset=%s", DEFAULT_ENCODING));
		response.setCharacterEncoding(DEFAULT_ENCODING);
		
		next.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}
}