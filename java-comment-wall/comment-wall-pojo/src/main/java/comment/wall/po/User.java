package comment.wall.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("user")
public class User {
    @TableField("id")
    private Integer id; // 主键ID

    @TableField("name")
    private String name; // 用户名

    @TableField("password")
    private String password; // 密码
	
	@TableField("avatar_url")
	private String avatarUrl;

    @TableField("create_time")
    private LocalDateTime createTime; // 创建时间
}