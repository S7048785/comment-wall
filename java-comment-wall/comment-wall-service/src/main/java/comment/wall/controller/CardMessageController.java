package comment.wall.controller;

import comment.wall.constant.CardConstant;
import comment.wall.dto.CardMessageDTO;
import comment.wall.dto.CardPageQueryDTO;
import comment.wall.po.CardMessage;
import comment.wall.result.PageResult;
import comment.wall.result.Result;
import comment.wall.service.ICardMessageService;
import comment.wall.service.IUserService;
import comment.wall.vo.CardMessageVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


@Slf4j
@Tag(name = "留言卡片相关接口")
@RestController
@RequestMapping("/card-message")
@RequiredArgsConstructor
public class CardMessageController {
	
	private final ICardMessageService cardMessageService;
	
	/**
	 * 根据id获取留言卡片信息
	 * @return
	 */
	@Operation(summary = "根据id获取留言卡片信息")
	@GetMapping("{id}")
	public Result<CardMessageVO> getCardMessage(@PathVariable("id") Long cardId) {
		return Result.success(cardMessageService.getCardById(cardId));
	}
	
	/**
	 * 分页查询留言卡片信息
	 */
	@Operation(summary = "分页查询留言卡片信息")
	@GetMapping("/page")
	public PageResult<CardMessageVO> getCardMessagePage(CardPageQueryDTO cardPageQueryDTO) {
		log.info("分页查询留言卡片信息:{}", cardPageQueryDTO);
		return new PageResult<>(cardMessageService.getCardMessagePage(cardPageQueryDTO), cardPageQueryDTO.getPage(), cardPageQueryDTO.getPageSize());
	}
	
	/**
	 * 修改留言卡片信息
	 */
	@Operation(summary = "修改留言卡片信息")
	@PutMapping
	public Result<CardMessageVO> updateCardMessage(@RequestBody CardMessageDTO card) {
		if (!cardMessageService.updateCard(card)) {
			// 修改失败
			return Result.error(CardConstant.CARD_NOT_EXIST);
		}
		return Result.success();
	}
	
	/**
	 * 创建留言卡片信息
	 */
	@Operation(summary = "创建留言卡片信息")
	@PostMapping
	public Result<CardMessageVO> createCardMessage(@RequestBody CardMessageDTO card) {
		return Result.success(cardMessageService.createCard(card));
	}
	
	/**
	 * 删除留言卡片信息
	 */
	@Operation(summary = "删除留言卡片信息")
	@DeleteMapping("/{id}")
	public Result<CardMessageVO> deleteCardMessage(@PathVariable("id") Long cardId) {
		Boolean b = cardMessageService.deleteCard(cardId);
		if (!b) {
			// 删除失败
			return Result.error(CardConstant.CARD_NOT_EXIST);
		}
		return Result.success();
	}
}
