package comment.wall.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class CardMessageVO {
	private Long id; // 主键ID，自增
	
	private Long userId;
	
	private String username; // 用户名
	
	private String color; // 卡片颜色
	
	private String content; // 卡片内容
	
	private String label; // 标签
	
	private int likeCount; // 点赞数
	
	private int commentCount; // 评论数
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime date; // 创建时间
	
	private String type = "msg";
}
