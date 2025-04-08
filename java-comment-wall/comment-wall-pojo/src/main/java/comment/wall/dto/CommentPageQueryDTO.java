package comment.wall.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
public class CommentPageQueryDTO implements Serializable {
	@Schema(description = "页码")
	private Integer page = 1;
	@Schema(description = "每页记录数")
	private Integer pageSize = 5;
	@Schema(description = "卡片id")
	private Long cardId;
	@Schema(description = "分类")
	private Integer category;
}
