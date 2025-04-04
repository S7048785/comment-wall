package comment.wall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import comment.wall.po.CardMessage;
import comment.wall.vo.CardMessageVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CardMessageMapper extends BaseMapper<CardMessage> {
	
	CardMessageVO getByLabelId(String labelId);
}
