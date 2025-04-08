package comment.wall.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class UserLoginDTO implements Serializable {
	@Schema(description = "用户名")
	private String name;
	@Schema(description = "密码")
	private String password;
}
