package comment.wall.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CardPageQueryDTO implements Serializable {
	//页码
	private int page = 1;
	
	//每页记录数
	private int pageSize = 15;
	
	// 标签
	private int labelId;
	
	// 分类 1留言 2图片
	private int category;
}
