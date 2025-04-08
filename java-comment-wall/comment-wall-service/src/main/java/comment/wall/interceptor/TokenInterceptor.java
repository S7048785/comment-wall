package comment.wall.interceptor;

import comment.wall.context.BaseContext;
import comment.wall.properties.JwtProperties;
import comment.wall.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// 如果是OPTIONS请求，直接放行
		if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
			response.setStatus(HttpServletResponse.SC_OK);
			return true;
		}
		// 获取token
		String token = request.getHeader("Authorization");
		// 校验令牌
		try {
			// 解析token
			Claims claims = JwtUtils.parseJWT(token);
			// 拿到用户id
			String id = claims.get("id").toString();
			Long userId = Long.valueOf(id);
			// 存入ThreadLocal
			BaseContext.setCurrentId(userId);
			// 放行
			return true;
		} catch(Exception e) {
			// 校验失败
			// 判断是否是 无需token的请求
			String requestURI = request.getRequestURI();
			if (requestURI.contains("card-message/page") || requestURI.contains("card-img/page") || requestURI.contains("comment/page")) {
				return true;
			}
			// 返回401状态码
			log.error("token校验失败:{}", e.getMessage());
			response.setStatus(401);
			return false;
		}
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
}
