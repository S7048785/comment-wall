package comment.wall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import comment.wall.mapper.CommentMapper;
import comment.wall.po.Comment;
import comment.wall.service.ICommentService;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {
}
