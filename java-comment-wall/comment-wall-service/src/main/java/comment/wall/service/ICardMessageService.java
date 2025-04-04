package comment.wall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import comment.wall.po.CardMessage;
import comment.wall.vo.CardMessageVO;

public interface ICardMessageService extends IService<CardMessage> {
	
	CardMessageVO getCardById(String cardId);
}
