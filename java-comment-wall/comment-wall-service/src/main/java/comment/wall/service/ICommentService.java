package comment.wall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import comment.wall.dto.CommentDTO;
import comment.wall.dto.CommentPageQueryDTO;
import comment.wall.po.Comment;
import comment.wall.result.PageResult;
import comment.wall.vo.CommentVO;

public interface ICommentService extends IService<Comment> {
	PageResult<CommentVO> getComment(CommentPageQueryDTO commentPageQueryDTO);
	
	Boolean deleteComment(Integer category, Long id);
	
	void createComment(CommentDTO commentDTO);
}
