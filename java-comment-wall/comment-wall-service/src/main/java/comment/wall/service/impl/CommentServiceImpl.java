package comment.wall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import comment.wall.constant.CommentConstant;
import comment.wall.dto.CommentDTO;
import comment.wall.dto.CommentPageQueryDTO;
import comment.wall.exception.CommentErrorException;
import comment.wall.mapper.CommentMapper;
import comment.wall.po.Comment;
import comment.wall.result.PageResult;
import comment.wall.service.ICommentService;
import comment.wall.vo.CommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {
	
	@Autowired
	private CommentMapper commentMapper;
	/**
	 * 根据卡片id获取评论
	 * @return
	 */
	@Override
	public PageResult<CommentVO> getComment(CommentPageQueryDTO commentPageQueryDTO) {
		Integer page = commentPageQueryDTO.getPage();
		Integer pageSize = commentPageQueryDTO.getPageSize();
		Integer category = commentPageQueryDTO.getCategory();
		Long cardId = commentPageQueryDTO.getCardId();
		List<CommentVO> commentVOList = commentMapper.pageQueryComment((page - 1) * pageSize, pageSize, category, cardId);
		return new PageResult<>(commentVOList, page, pageSize);
	}
	
	/**
	 * 删除评论
	 */
	@Override
	public Boolean deleteComment(Integer category, Long id) {
		// TODO: 判断当前评论是否属于当前用户
		
		// 标记为已删除
		return update(
				new LambdaUpdateWrapper<Comment>()
						.eq(Comment::getId, id)
						.eq(Comment::getCategory, category)
						.set(Comment::getDeleted, true)
		);
		//return remove(
		//		new LambdaQueryWrapper<Comment>()
		//				.eq(Comment::getId, id)
		//				.eq(Comment::getCategory, category)
		//);
	}
	
	/**
	 * 创建评论
	 * @param commentDTO
	 */
	@Override
	public void createComment(CommentDTO commentDTO) {
		// TODO: 校验当前userId是否是当前用户
		
		if (StrUtil.isBlank(commentDTO.getContent())) {
			throw new CommentErrorException(CommentConstant.COMMENT_MESSAGE);
		}
		
		// 查询被标记为deleted的评论
		List<Comment> list = lambdaQuery().eq(Comment::getCardId, 2).list();
		if (CollUtil.isNotEmpty(list)) {
			Comment comment = list.get(0);
			Comment comment1 = BeanUtil.copyProperties(commentDTO, Comment.class);
			// 更新字段
			comment1.setId(comment.getId());
			comment1.setCreateTime(LocalDateTime.now());
			comment1.setDeleted(false);
			updateById(comment1);
			return;
		}
		// 如果没有，则创建新评论
		Comment build = Comment.builder()
				                .cardId(commentDTO.getCardId())
				                .userId(commentDTO.getUserId())
				                .content(commentDTO.getContent())
				                .category(commentDTO.getCategory())
				                .createTime(LocalDateTime.now())
				                .build();
		save(build);
	}
	
}
