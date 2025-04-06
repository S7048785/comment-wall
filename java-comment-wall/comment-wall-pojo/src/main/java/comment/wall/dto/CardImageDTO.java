package comment.wall.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Schema
public class CardImageDTO {
	@Schema(description = "id")
	private Long id;
	@Schema(description = "用户id")
	private Long userId;
	@Schema(description = "图片url")
	private String url;
	@Schema(description = "标签id")
	private Integer labelId;
}
