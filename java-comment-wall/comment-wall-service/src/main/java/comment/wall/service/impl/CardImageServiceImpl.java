package comment.wall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import comment.wall.constant.CardConstant;
import comment.wall.dto.CardImageDTO;
import comment.wall.dto.CardPageQueryDTO;
import comment.wall.exception.CardErrorException;
import comment.wall.mapper.CardImageMapper;
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

@Service
public class CardImageServiceImpl extends ServiceImpl<CardImageMapper, CardImage> implements ICardImageService {
	@Autowired
	private CardImageMapper cardImageMapper;
	@Autowired
	private ICommentService commentService;
	/**
	 * 根据id获取留言卡片信息
	 * @param cardId
	 * @return
	 */
	@Override
	public CardImageVO getCardById(String cardId) {
		CardImageVO card = cardImageMapper.queryCardById(cardId);
		if (card == null) {
			throw new CardErrorException(CardConstant.CARD_MESSAGE_NOT_EXIST);
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
		// TODO: 修改前验证当前卡片是否属于当前用户
		CardImage cardImage = BeanUtil.copyProperties(card, CardImage.class);
		return updateById(cardImage);
	}
	
	/**
	 * 创建留言卡片信息
	 * @param card
	 */
	@Override
	public void createCard(CardImageDTO card) {
		// TODO: 验证当前是否是当前用户
		
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
		commentService.remove(
				new LambdaQueryWrapper<Comment>()
						.eq(Comment::getCategory, category)
						.eq(Comment::getCardId, cardId)
		);
		// 删除卡片
		return removeById(cardId);
	}
}
