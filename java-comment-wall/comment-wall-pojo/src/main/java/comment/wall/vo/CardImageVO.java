package comment.wall.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class CardImageVO {
	private Long id; // 主键ID
	
	private Long userId; // 用户ID
	
	private String userName; // 用户名
	
	private String url; // 图片URL
	
	private String label; // 标签
	
	private Integer likeCount; // 点赞数
	
	private Integer commentCount; // 评论数
}
