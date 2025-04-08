package comment.wall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import comment.wall.constant.CardConstant;
import comment.wall.context.BaseContext;
import comment.wall.dto.CardImageDTO;
import comment.wall.dto.CardPageQueryDTO;
import comment.wall.exception.CardErrorException;
import comment.wall.mapper.CardImageMapper;
import comment.wall.mapper.CommentMapper;
import comment.wall.po.CardImage;
import comment.wall.po.Comment;
import comment.wall.service.ICardImageService;
import comment.wall.service.ICommentService;
import comment.wall.vo.CardImageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class CardImageServiceImpl extends ServiceImpl<CardImageMapper, CardImage> implements ICardImageService {
	@Autowired
	private CardImageMapper cardImageMapper;
	@Autowired
	private CommentMapper commentMapper;
	/**
	 * 根据id获取留言卡片信息
	 * @param cardId
	 * @return
	 */
	@Override
	public CardImageVO getCardById(String cardId) {
		CardImageVO card = cardImageMapper.queryCardById(cardId);
		if (card == null) {
			throw new CardErrorException(CardConstant.CARD_NOT_EXIST);
		}
		return card;
	}
	
	/**
	 * 分页查询留言卡片信息
	 * @param cardPageQueryDTO
	 * @return
	 */
	@Override
	public List<CardImageVO> getCardImagePage(CardPageQueryDTO cardPageQueryDTO) {
		Integer page = cardPageQueryDTO.getPage();
		Integer pageSize = cardPageQueryDTO.getPageSize();
		
		return cardImageMapper.pageQueryCard((page - 1) * pageSize, pageSize, cardPageQueryDTO.getLabelId());
	}
	
	/**
	 * 修改留言卡片信息
	 * @param card
	 * @return
	 */
	@Override
	public Boolean updateCard(CardImageDTO card) {
		Long currentId = BaseContext.getCurrentId();
		CardImage card1 = getById(card.getId());
		if (card1 == null)
			throw new CardErrorException(CardConstant.CARD_NOT_EXIST);
		// 当前卡片是否属于当前用户
		if (card1.getId().equals(card.getId()) && card1.getUserId().equals(currentId)) {
			CardImage cardImage = BeanUtil.copyProperties(card, CardImage.class);
			return updateById(cardImage);
		}
		return false;
	}
	
	/**
	 * 创建留言卡片信息
	 * @param card
	 */
	@Override
	public void createCard(CardImageDTO card) {
		// TODO: 查询标签是否存在
		
		CardImage build = CardImage.builder()
				                  .userId(card.getUserId())
				                  .url(card.getUrl())
				                  .labelId(card.getLabelId())
				                  .likeCount(0)
				                  .commentCount(0)
				                  .createTime(LocalDateTime.now())
								  .updateTime(LocalDateTime.now())
				                  .build();
		save(build);
	}
	
	/**
	 * 删除卡片信息
	 * @param cardId
	 * @return
	 */
	@Transactional
	@Override
	public Boolean deleteCard(Integer category, Long cardId) {
		// 先删除评论 再删除卡片
		commentMapper.delete(
				new LambdaQueryWrapper<Comment>()
						.eq(Comment::getCategory, category)
						.eq(Comment::getCardId, cardId)
		);
		// 删除卡片
		return removeById(cardId);
	}
}
