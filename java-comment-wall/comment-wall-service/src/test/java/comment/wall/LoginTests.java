package comment.wall;


import comment.wall.dto.UserLoginDTO;
import comment.wall.result.Result;
import comment.wall.service.IUserService;
import comment.wall.vo.UserLoginVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class LoginTests {
	@Autowired
	private IUserService userService;
	
	@Test
	public void login() {
		UserLoginDTO builder = UserLoginDTO.builder()
				                       .name("abc")
				                       .password("123").build();
		System.out.println(userService.login(builder));
		builder.setPassword("123456");
		System.out.println((userService.login(builder)));
		builder.setName("abc1");
		System.out.println((userService.login(builder)));
	}
}
