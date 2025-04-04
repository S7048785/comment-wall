package comment.wall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import comment.wall.mapper.UserMapper;
import comment.wall.po.User;
import comment.wall.service.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
}
