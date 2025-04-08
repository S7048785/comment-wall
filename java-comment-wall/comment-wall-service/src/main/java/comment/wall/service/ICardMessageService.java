package comment.wall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import comment.wall.dto.CardMessageDTO;
import comment.wall.dto.CardPageQueryDTO;
import comment.wall.po.CardMessage;
import comment.wall.vo.CardMessageVO;

import java.util.List;

public interface ICardMessageService extends IService<CardMessage> {
	
	CardMessageVO getCardById(Long cardId);
	
	List<CardMessageVO> getCardMessagePage(CardPageQueryDTO cardPageQueryDTO);
	
	Boolean updateCard(CardMessageDTO card);
	
	CardMessageVO createCard(CardMessageDTO card);
	
	Boolean deleteCard(Long cardId);
}
