package comment.wall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import comment.wall.po.Label;
import comment.wall.vo.LabelVO;

import java.util.List;

public interface ILabelService extends IService<Label> {
	List<LabelVO> getLabelList(Integer category);
}
