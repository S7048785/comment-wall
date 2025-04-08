package comment.wall.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import comment.wall.constant.UserConstant;
import comment.wall.dto.UserLoginDTO;
import comment.wall.exception.BaseException;
import comment.wall.mapper.UserMapper;
import comment.wall.po.User;
import comment.wall.properties.JwtProperties;
import comment.wall.result.Result;
import comment.wall.service.IUserService;
import comment.wall.utils.JwtUtils;
import comment.wall.vo.UserLoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
	
	/**
	 * 用户登录
	 * @param userLoginDTO
	 * @return
	 */
	@Autowired
	private JwtProperties jwtProperties;
	@Override
	public Result<UserLoginVO> login(UserLoginDTO userLoginDTO) {
		String name = userLoginDTO.getName();
		String password = userLoginDTO.getPassword();
		
		// 校验用户名和密码
		if (StrUtil.isBlank(name) || StrUtil.isBlank(password)) {
			return Result.error(UserConstant.USER_NAME_OR_PASSWORD_NOT_NULL);
		}
		//Pattern namepattern = Pattern.compile("^[a-zA-z]\\w{5,12}$");
		//Pattern passwordpattern = Pattern.compile("^\\w{5,17}$");
		//if (!namepattern.matcher(name).matches()) {
		//	return Result.error(UserConstant.LOGIN_NAME_VALIDATE);
		//}
		//if (!passwordpattern.matcher(password).matches()) {
		//	return Result.error(UserConstant.LOGIN_PASSWORD_VALIDATE);
		//}
		
		// 查询用户是否存在
		try {
			User user = getOne(new LambdaQueryWrapper<User>().eq(User::getName, name).eq(User::getPassword, password));
			if (user == null) {
				return Result.error(UserConstant.USER_NOT_EXIST_OR_PASSWORD_ERROR);
			} else {
				// 生成jwt令牌
				Map<String, Object> claims = new HashMap<>();
				claims.put("id", user.getId());
				claims.put("name", user.getName());
				String token = JwtUtils.createJWT(claims);
				UserLoginVO userLoginVO = UserLoginVO.builder()
						                          .id(user.getId())
						                          .token(token)
						                          .name(user.getName())
						                          .avatarUrl(user.getAvatarUrl()).build();
				return Result.success(userLoginVO);
			}
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}
}
