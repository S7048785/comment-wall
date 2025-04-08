package comment.wall.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("user")
public class User {
    @TableId("id")
    private Long id; // 主键ID

    @TableField("name")
    private String name; // 用户名

    @TableField("password")
    private String password; // 密码
	
	@TableField("avatar_url")
	private String avatarUrl;

    @TableField("create_date")
    private LocalDateTime createDate; // 创建时间
}