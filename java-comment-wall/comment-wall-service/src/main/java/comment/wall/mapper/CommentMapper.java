package comment.wall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import comment.wall.po.Comment;
import comment.wall.vo.CommentVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
	List<CommentVO> pageQueryComment(Integer page, Integer pageSize, Integer category, Long cardId);
}
