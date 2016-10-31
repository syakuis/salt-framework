package org.saltframework.util.http;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 통신 요청에서 자주 사용되는 유틸리티
 *
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http ://syaku.tistory.com
 * @since 2016. 10. 7.
 */
public final class RequestSalt {
	private RequestSalt() {}

	/**
	 * 현재 경로를 얻는 다.
	 *
	 * @param request request
	 * @return servlet url
	 */
	public static String getPathQueryString(HttpServletRequest request) {
		StringBuilder builder = new StringBuilder(request.getServletPath());
		String queryString = request.getQueryString();
		if (queryString != null && !"".equals(queryString)) {
			builder.append('?');
		}
		builder.append(queryString);
		return builder.toString();
	}

	/**
	 * ajax 방식 여부를 얻는 다.
	 *
	 * @param request request
	 * @return
	 */
	public static boolean isAjax(HttpServletRequest request) {
		String accept = request.getHeader("accept");
		String ajax = request.getHeader("X-Requested-With");

		return ( StringUtils.indexOf(accept, "json") > -1 && StringUtils.isNotEmpty(ajax));
	}

	/**
	 * 페이지 요청이 html 인지를 얻는 다.
	 *
	 * @param request request
	 * @return
	 */
	public static boolean isHtml(HttpServletRequest request) {
		String accept = request.getHeader("accept");

		return ( StringUtils.indexOf(accept, "text/html") > -1);
	}
}
