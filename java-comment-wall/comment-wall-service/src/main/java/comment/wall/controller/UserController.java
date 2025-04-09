package comment.wall.controller;

import comment.wall.dto.UserLoginDTO;
import comment.wall.result.Result;
import comment.wall.service.IUserService;
import comment.wall.vo.UserLoginVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "用户模块")
@RequestMapping("/user")
@RestController
public class UserController {
	
	@Autowired
	private IUserService userService;
	/**
	 * 登录
	 */
	@Operation(summary = "登录")
	@PostMapping("/login")
	public Result<UserLoginVO> login(@RequestBody UserLoginDTO userLoginDTO) {
		return userService.login(userLoginDTO);
	}
	
	/**
	 * 注册
	 */
}
