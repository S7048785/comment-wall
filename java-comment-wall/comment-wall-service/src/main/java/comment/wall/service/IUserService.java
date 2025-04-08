package comment.wall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import comment.wall.dto.UserLoginDTO;
import comment.wall.po.User;
import comment.wall.result.Result;
import comment.wall.vo.UserLoginVO;

public interface IUserService extends IService<User> {
	
	Result<UserLoginVO> login(UserLoginDTO userLoginDTO);
}
