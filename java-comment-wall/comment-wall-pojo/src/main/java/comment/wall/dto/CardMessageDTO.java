package comment.wall.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class CardMessageDTO {
	@Schema(description = "留言卡片id")
	private Long id;
	@Schema(description = "留言内容")
	private String content;
	@Schema(description = "卡片颜色")
	private String color;
	@Schema(description = "标签id")
	private Integer labelId;
}
