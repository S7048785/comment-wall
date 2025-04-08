package comment.wall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import comment.wall.constant.FieldConstant;
import comment.wall.constant.LabelConstant;
import comment.wall.constant.UserConstant;
import comment.wall.constant.CardConstant;
import comment.wall.context.BaseContext;
import comment.wall.dto.CardMessageDTO;
import comment.wall.dto.CardPageQueryDTO;
import comment.wall.exception.CardErrorException;
import comment.wall.exception.LabelErrorException;
import comment.wall.exception.UserErrorException;
import comment.wall.mapper.CardMessageMapper;
import comment.wall.po.CardMessage;
import comment.wall.po.Comment;
import comment.wall.po.Label;
import comment.wall.po.User;
import comment.wall.service.ICardMessageService;
import comment.wall.service.ICommentService;
import comment.wall.service.ILabelService;
import comment.wall.service.IUserService;
import comment.wall.vo.CardMessageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CardMessageServiceImpl extends ServiceImpl<CardMessageMapper, CardMessage> implements ICardMessageService {
	
	@Autowired
	private CardMessageMapper cardMessageMapper;
	@Autowired
	private ICommentService commentService;
	/**
	 * 根据cardId查询
	 * @param cardId
	 * @return
	 */
	@Override
	public CardMessageVO getCardById(Long cardId) {
		CardMessageVO card = cardMessageMapper.getByCardId(cardId);
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
	public List<CardMessageVO> getCardMessagePage(CardPageQueryDTO cardPageQueryDTO) {
		// 手写分页
		Integer page = cardPageQueryDTO.getPage();
		Integer pageSize = cardPageQueryDTO.getPageSize();
		
		return cardMessageMapper.pageQueryCard((page - 1) * pageSize, pageSize, cardPageQueryDTO.getLabelId(), BaseContext.getCurrentId());
	}
	
	/**
	 * 修改卡片
	 * @param card
	 */
	@Override
	public Boolean updateCard(CardMessageDTO card) {
		Long currentId = BaseContext.getCurrentId();
		CardMessage card1 = getById(card.getId());
		if (card1 == null)
			throw new CardErrorException(CardConstant.CARD_NOT_EXIST);
		// 判断卡片id和用户id是否一致
		if (card1.getId().equals(card.getId()) && card1.getUserId().equals(currentId)) {
			CardMessage cardMessage = BeanUtil.copyProperties(card, CardMessage.class);
			cardMessage.setUpdateTime(LocalDateTime.now());
			return updateById(cardMessage);
		}
		return false;
	}
	
	/**
	 * 创建卡片
	 * @param card
	 * @return
	 */
	@Override
	public CardMessageVO createCard(CardMessageDTO card) {
		// TODO: 查询标签是否存在
		
		// 查询被标记为deleted的数据
		List<CardMessage> list = lambdaQuery().eq(CardMessage::getDeleted, true).list();
		if (CollUtil.isNotEmpty(list)) {
			CardMessage cardMessage = list.get(0);
			// DTO转为PO 更新 被标记为deleted的数据
			CardMessage cardMessage1 = BeanUtil.copyProperties(card, CardMessage.class);
			// 更新id和DTO没有的字段
			cardMessage1.setId(cardMessage.getId());
			cardMessage1.setUserId(BaseContext.getCurrentId());
			cardMessage1.setLikeCount(0);
			cardMessage1.setCommentCount(0);
			cardMessage1.setCreateTime(LocalDateTime.now());
			cardMessage1.setUpdateTime(LocalDateTime.now());
			cardMessage1.setDeleted(false);
			updateById(cardMessage1);
			return getCardById(cardMessage1.getId());
		}
		
		CardMessage build = CardMessage.builder()
				                    .userId(BaseContext.getCurrentId())
				                    .content(card.getContent())
				                    .color(card.getColor())
				                    .labelId(card.getLabelId())
				                    .likeCount(0)
				                    .commentCount(0)
				                    .createTime(LocalDateTime.now())
				                    .updateTime(LocalDateTime.now())
				                    .build();
		save(build);
		// 转为VO 并返回
		return getCardById(build.getId());
	}
	
	/**
	 * 删除卡片
	 */
	public Boolean deleteCard(Long cardId) {
		Long currentId = BaseContext.getCurrentId();
		CardMessage card = getById(cardId);
		if (card == null)
			return false;
		// 判断用户id是否一致
		if (card.getUserId().equals(currentId)) {
			card.setDeleted(true);
			
			// 删除评论
			commentService.remove(
					new LambdaQueryWrapper<Comment>()
							.eq(Comment::getCardId, cardId)
							.eq(Comment::getCategory, FieldConstant.CATEGORY_MESSAGE)
			);
			return updateById(card);
		}
		return false;
	}
}
