package comment.wall.controller;

import comment.wall.dto.CommentDTO;
import comment.wall.dto.CommentPageQueryDTO;
import comment.wall.result.PageResult;
import comment.wall.result.Result;
import comment.wall.service.ICommentService;
import comment.wall.vo.CommentVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Tag(name = "评论相关接口")
@RequestMapping("/comment")
@RestController
@RequiredArgsConstructor
public class CommentController {
	private final ICommentService commentService;
	
	/**
	 * 分页查询评论信息
	 */
	@Operation(summary = "分页查询评论信息")
	@GetMapping("page")
	public PageResult<CommentVO> getCommentById(CommentPageQueryDTO commentPageQueryDTO) {
		log.info("分页查询评论信息:{}", commentPageQueryDTO);
		return commentService.getComment(commentPageQueryDTO);
	}
	
	/**
	 * 创建评论
	 */
	@Operation(summary = "创建评论")
	@PostMapping
	public Result createComment(@RequestBody CommentDTO commentDTO) {
		log.info("创建评论:{}", commentDTO);
		commentService.createComment(commentDTO);
		return Result.success();
	}
	
	/**
	 * 删除评论
	 */
	@Operation(summary = "删除评论")
	@DeleteMapping("/{category}/{id}")
	public Result<String> deleteComment(@PathVariable("category") Integer category, @PathVariable("id") Long id) {
		
		if (!commentService.deleteComment(category, id)) {
			return Result.error("删除失败");
		}
		return Result.success();
	}
	
}
