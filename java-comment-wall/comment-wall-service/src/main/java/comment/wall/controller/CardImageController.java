package comment.wall.controller;

import comment.wall.constant.CardConstant;
import comment.wall.dto.CardImageDTO;
import comment.wall.constant.FieldConstant;
import comment.wall.dto.CardPageQueryDTO;
import comment.wall.result.PageResult;
import comment.wall.result.Result;
import comment.wall.service.ICardImageService;
import comment.wall.vo.CardImageVO;
import comment.wall.vo.CardMessageVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@Slf4j
@Tag(name = "图片卡片相关接口")
@RequestMapping("/card-image")
@RestController
@RequiredArgsConstructor
public class CardImageController {
	private final ICardImageService cardImageService;
	
	/**
	 * 根据id获取卡片信息
	 */
	@Operation(summary = "根据id获取留言卡片信息")
	@GetMapping("/{id}")
	public CardImageVO getCardImage(@PathVariable("id") String cardId) {
		return cardImageService.getCardById(cardId);
	}
	
	/**
	 * 分页查询留言卡片信息
	 */
	@Operation(summary = "分页查询留言卡片信息")
	@GetMapping("/page")
	public PageResult<CardImageVO> getCardImagePage(CardPageQueryDTO cardPageQueryDTO) {
		log.info("分页查询留言卡片信息:{}", cardPageQueryDTO);
		return new PageResult<>(cardImageService.getCardImagePage(cardPageQueryDTO), cardPageQueryDTO.getPage(), cardPageQueryDTO.getPageSize());
	}
	
	/**
	 * 修改留言卡片信息
	 */
	@Operation(summary = "修改留言卡片信息")
	@PutMapping
	public Result<CardImageVO> updateCardMessage(@RequestBody CardImageDTO card) {
		if (!cardImageService.updateCard(card)) {
			// 修改失败
			return Result.error(CardConstant.CARD_MESSAGE_NOT_EXIST);
		}
		return Result.success();
	}
	
	/**
	 * 创建留言卡片信息
	 */
	@Operation(summary = "创建留言卡片信息")
	@PostMapping
	public Result createCardMessage(@RequestBody CardImageDTO card) {
		cardImageService.createCard(card);
		return Result.success();
	}
	
	/**
	 * 删除留言卡片信息
	 * @param cardId
	 * @return
	 */
	@Operation(summary = "删除留言卡片信息")
	@DeleteMapping("/{id}")
	public Result<CardImageVO> deleteCardMessage(@PathVariable("id") Long cardId) {
		if (!cardImageService.deleteCard(FieldConstant.CATEGORY_IMAGE, cardId)) {
			// 删除失败
			return Result.error(CardConstant.CARD_MESSAGE_NOT_EXIST);
		}
		return Result.success();
	}
}
