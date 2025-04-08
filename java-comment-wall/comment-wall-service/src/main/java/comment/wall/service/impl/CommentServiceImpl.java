package comment.wall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import comment.wall.constant.CommentConstant;
import comment.wall.context.BaseContext;
import comment.wall.dto.CommentDTO;
import comment.wall.dto.CommentPageQueryDTO;
import comment.wall.exception.CommentErrorException;
import comment.wall.mapper.CardImageMapper;
import comment.wall.mapper.CardMessageMapper;
import comment.wall.mapper.CommentMapper;
import comment.wall.po.CardImage;
import comment.wall.po.CardMessage;
import comment.wall.po.Comment;
import comment.wall.result.PageResult;
import comment.wall.service.ICardImageService;
import comment.wall.service.ICardMessageService;
import comment.wall.service.ICommentService;
import comment.wall.vo.CommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {
	
	@Autowired
	private CommentMapper commentMapper;
	@Autowired
	private CardMessageMapper cardMessageMapper;
	@Autowired
	private CardImageMapper cardImageMapper;
	
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
		Long currentId = BaseContext.getCurrentId();
		Comment comment = getById(id);
		if (comment == null || comment.getUserId().equals(currentId)) {
			return false;
		}
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
	@Transactional
	@Override
	public Long createComment(CommentDTO commentDTO) {
		Long currentId = BaseContext.getCurrentId();
		if (StrUtil.isBlank(commentDTO.getContent())) {
			throw new CommentErrorException(CommentConstant.COMMENT_MESSAGE);
		}
		Long commentId = null;
		// 查询被标记为deleted的评论
		List<Comment> list = lambdaQuery().eq(Comment::getDeleted, true).list();
		if (CollUtil.isNotEmpty(list)) {
			Comment comment = list.get(0);
			Comment comment1 = BeanUtil.copyProperties(commentDTO, Comment.class);
			// 更新字段
			comment1.setId(comment.getId());
			comment1.setCreateTime(LocalDateTime.now());
			comment1.setDeleted(false);
			updateById(comment1);
			commentId =  comment1.getId();
		} else {
			// 如果没有，则创建新评论
			Comment build = Comment.builder()
					                .cardId(commentDTO.getCardId())
					                .userId(currentId)
					                .content(commentDTO.getContent())
					                .category(commentDTO.getCategory())
					                .createTime(LocalDateTime.now())
					                .build();
			save(build);
			commentId = build.getId();
		}
		// 评论数+1
		if (CommentConstant.CARD_MESSAGE.equals(commentDTO.getCategory())) {
			cardMessageMapper.update(
					new LambdaUpdateWrapper<CardMessage>()
							.eq(CardMessage::getId, commentDTO.getCardId())
							.setSql("comment_count = comment_count + 1")
			);
		} else if(CommentConstant.CARD_IMAGE.equals(commentDTO.getCategory())) {
			cardImageMapper.update(
					new LambdaUpdateWrapper<CardImage>()
							.eq(CardImage::getId, commentDTO.getCardId())
							.setSql("comment_count = comment_count + 1")
			);
		}
		return commentId;
	}
	
	@Override
	public CommentVO getCommentById(Long commentId, Integer category) {
		return commentMapper.getCommentById(commentId, category);
	}
	
	
}
