package comment.wall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import comment.wall.constant.LabelConstant;
import comment.wall.constant.UserConstant;
import comment.wall.constant.CardConstant;
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

@Service
public class CardMessageServiceImpl extends ServiceImpl<CardMessageMapper, CardMessage> implements ICardMessageService {
	
	@Autowired
	private IUserService userService;
	@Autowired
	private ILabelService labelService;
	
	@Override
	public CardMessageVO getCardById(String cardId) {
		CardMessage card = getById(cardId);
		// 标签不存在
		if (card == null) {
			throw new CardErrorException(CardConstant.CARD_MESSAGE_NOT_EXIST);
		}
		// 根据标签id查询标签名
		Label label = labelService.getById(card.getLabelId());
		// 标签不存在 返回业务异常
		if (label == null) {
			throw new LabelErrorException(LabelConstant.LABEL_NOT_EXIST);
		}
		
		// 根据用户id查询用户名
		User user = userService.getById(card.getUserId());
		// 用户不存在 返回业务异常
		if (user == null) {
			throw new UserErrorException(UserConstant.USER_NOT_EXIST);
		}
		
		CardMessageVO cardMessageVO = BeanUtil.copyProperties(card, CardMessageVO.class);
		cardMessageVO
				.setUserName(user.getName())
				.setLabel(label.getName());
		return cardMessageVO;
	}
}
