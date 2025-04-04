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
public class CardMessageVO {
	private int id; // 主键ID，自增
	
	private String userName; // 用户名
	
	private String color; // 卡片颜色
	
	private String content; // 卡片内容
	
	private String label; // 标签ID
	
	private int likeCount; // 点赞数
	
	private int commentCount; // 评论数
	
	private LocalDateTime createTime; // 创建时间
}
