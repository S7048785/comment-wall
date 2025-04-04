package comment.wall.controller;

import comment.wall.dto.CardPageQueryDTO;
import comment.wall.po.CardMessage;
import comment.wall.result.Result;
import comment.wall.service.ICardMessageService;
import comment.wall.service.IUserService;
import comment.wall.vo.CardMessageVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RequestMapping("/card-message")
@RestController
@RequiredArgsConstructor
public class CardMessageController {
	
	private final ICardMessageService cardMessageService;
	
	/**
	 * 根据id获取留言卡片信息
	 * @return
	 */
	@GetMapping("{id}")
	public Result<CardMessageVO> getCardMessage(String cardId) {
		return Result.success(cardMessageService.getCardById(cardId));
	}
	
	/**
	 * 分页查询留言卡片信息
	 */
	@GetMapping("/page")
	public Result<CardMessageVO> getCardMessagePage(CardPageQueryDTO cardPageQueryDTO) {
		return null;
	}
	
	/**
	 * 修改留言卡片信息
	 */
	@PutMapping("{id}")
	public Result<CardMessageVO> updateCardMessage() {
		return null;
	}
	
	
	/**
	 * 创建留言卡片信息
	 */
	
	/**
	 * 删除留言卡片信息
	 */
}
