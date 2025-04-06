package comment.wall.po;

import com.baomidou.mybatisplus.annotation.IdType;
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
@Builder
@TableName("card_message")
public class CardMessage {
    @TableId(type = IdType.AUTO)
    private Long id; // 主键ID，自增

    @TableField("user_id")
    private Long userId; // 用户ID

    @TableField("color")
    private String color; // 卡片颜色

    @TableField("content")
    private String content; // 卡片内容

    @TableField("label_id")
    private Integer labelId; // 标签ID

    @TableField("like_count")
    private Integer likeCount; // 点赞数

    @TableField("comment_count")
    private Integer commentCount; // 评论数

    @TableField("create_time")
    private LocalDateTime createTime; // 创建时间
    
    @TableField("update_time")
    private LocalDateTime updateTime; // 更新时间
}