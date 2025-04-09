package comment.wall.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
public class CardPageQueryDTO implements Serializable {
	//页码
	@Schema(description = "页码")
	private Integer page = 1;
	
	//每页记录数
	@Schema(description = "每页记录数")
	private Integer pageSize = 15;
	
	// 标签
	@Schema(description = "标签")
	private Integer labelId;
	
}
