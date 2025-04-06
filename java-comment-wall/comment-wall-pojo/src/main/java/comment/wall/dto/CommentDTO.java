package comment.wall.dto;

import lombok.Data;

import java.io.Serializable;
@Data
public class CommentDTO implements Serializable {
	private Long userId;
	private Long cardId;
	private String content;
	private Integer category;
}
