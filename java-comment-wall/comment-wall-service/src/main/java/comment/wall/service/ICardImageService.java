package comment.wall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import comment.wall.dto.CardImageDTO;
import comment.wall.dto.CardMessageDTO;
import comment.wall.dto.CardPageQueryDTO;
import comment.wall.po.CardImage;
import comment.wall.vo.CardImageVO;
import comment.wall.vo.CardMessageVO;

import java.util.List;

public interface ICardImageService extends IService<CardImage> {
	CardImageVO getCardById(String cardId);
	
	List<CardImageVO> getCardImagePage(CardPageQueryDTO cardPageQueryDTO);
	
	
	Boolean updateCard(CardImageDTO card);
	
	void createCard(CardImageDTO card);
	
	Boolean deleteCard(Integer category, Long cardId);
}
