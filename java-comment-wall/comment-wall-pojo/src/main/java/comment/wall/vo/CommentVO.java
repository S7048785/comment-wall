package comment.wall.vo;

import com.baomidou.mybatisplus.annotation.TableField;
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
public class CommentVO {
	private int id; // 主键ID，自增
	
	private String userName; // 用户名
	
	private String avatar; // 头像
	
	private String content; // 评论内容
	
	private LocalDateTime createTime; // 创建时间
}
