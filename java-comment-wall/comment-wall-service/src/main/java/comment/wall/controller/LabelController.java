package comment.wall.controller;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import comment.wall.po.Label;
import comment.wall.result.Result;
import comment.wall.service.ILabelService;
import comment.wall.vo.LabelVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "标签")
@RequestMapping("/label")
@RestController
@RequiredArgsConstructor
public class LabelController {
	
	private final ILabelService labelService;
	/**
	 * 获取标签列表
	 */
	@Operation(summary = "获取标签列表")
	@GetMapping("{category}")
	public Result<List<LabelVO>> getLabelList(@PathVariable("category") Integer category) {
		List<LabelVO> map = labelService.getLabelList(category);
		return Result.success(map);
	}
}
