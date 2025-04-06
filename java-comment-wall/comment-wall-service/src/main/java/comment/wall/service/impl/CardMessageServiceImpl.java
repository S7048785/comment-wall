package comment.wall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import comment.wall.constant.LabelConstant;
import comment.wall.constant.UserConstant;
import comment.wall.constant.CardConstant;
import comment.wall.dto.CardMessageDTO;
import comment.wall.dto.CardPageQueryDTO;
import comment.wall.exception.CardErrorException;
import comment.wall.exception.LabelErrorException;
import comment.wall.exception.UserErrorException;
import comment.wall.mapper.CardMessageMapper;
import comment.wall.po.CardMessage;
import comment.wall.po.Label;
import comment.wall.po.User;
import comment.wall.service.ICardMessageService;
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
	
	/**
	 * 根据cardId查询
	 * @param cardId
	 * @return
	 */
	@Override
	public CardMessageVO getCardById(String cardId) {
		CardMessageVO card = cardMessageMapper.getByCardId(cardId);
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
	public List<CardMessageVO> getCardMessagePage(CardPageQueryDTO cardPageQueryDTO) {
		// 手写分页
		Integer page = cardPageQueryDTO.getPage();
		Integer pageSize = cardPageQueryDTO.getPageSize();
		return cardMessageMapper.pageQueryCard((page - 1) * pageSize, pageSize, cardPageQueryDTO.getLabelId());
	}
	
	/**
	 * 修改卡片
	 * @param card
	 */
	@Override
	public Boolean updateCard(CardMessageDTO card) {
		// TODO: 修改前验证当前卡片是否属于当前用户
		CardMessage cardMessage = BeanUtil.copyProperties(card, CardMessage.class);
		cardMessage.setUpdateTime(LocalDateTime.now());
		return updateById(cardMessage);
	}
	
	/**
	 * 创建卡片
	 * @param card
	 * @return
	 */
	@Override
	public void createCard(CardMessageDTO card) {
		// TODO: 验证当前是否是当前用户
		
		// TODO: 查询标签是否存在
		
		CardMessage build = CardMessage.builder()
				                    .userId(card.getUserId())
				                    .content(card.getContent())
				                    .color(card.getColor())
				                    .labelId(card.getLabelId())
				                    .likeCount(0)
				                    .commentCount(0)
				                    .createTime(LocalDateTime.now())
				                    .updateTime(LocalDateTime.now())
				                    .build();
		save(build);
	}
}
