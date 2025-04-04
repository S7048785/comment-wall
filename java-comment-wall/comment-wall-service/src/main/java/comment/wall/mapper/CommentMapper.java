package comment.wall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import comment.wall.po.Comment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
}
