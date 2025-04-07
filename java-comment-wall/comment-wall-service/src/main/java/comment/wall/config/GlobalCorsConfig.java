package comment.wall.config;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class GlobalCorsConfig {

  /**
   * 允许跨域调用的过滤器
   */
  //@Bean
  //public CorsFilter corsFilter() {
  //  CorsConfiguration config = new CorsConfiguration();
  //  //允许白名单域名进行跨域调用
  //  config.addAllowedOrigin("*");
  //  //允许跨越发送cookie
  //  config.setAllowCredentials(true);
  //  //放行全部原始头信息
  //  config.addAllowedHeader("*");
  //  //允许所有请求方法跨域调用
  //  config.addAllowedMethod("*");
  //  UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
  //  source.registerCorsConfiguration("/**", config);
  //  return new CorsFilter(source);
  //}
  
  @Bean
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 允许所有路径
                .allowedOrigins("http://localhost:5173") // 明确列出允许的 origins
                .allowedMethods("GET", "POST", "PUT", "DELETE") // 允许的 HTTP 方法
                .allowedHeaders("*") // 允许的请求头
                .allowCredentials(true); // 允许携带凭证
      }
    };
  }
}