package comment.wall.handler;

import comment.wall.exception.BaseException;
import comment.wall.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler
	public Result exceptionHandler(BaseException ex){
		log.error("异常信息：{}", ex.getMessage());
		return Result.error(ex.getMessage());
	}
}
