package comment.wall.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult<T> implements Serializable {
	
	private long total; //总记录数
	
	private List<T> records; //当前页数据集合
	
	private int page; //当前页
	
	private int pageSize; //每页记录数
	
	
	public PageResult(List<T> cardMessagePage, int page, int pageSize) {
		this.total = cardMessagePage.size();
		this.records = cardMessagePage;
		this.page = page;
		this.pageSize = pageSize;
	}
}
