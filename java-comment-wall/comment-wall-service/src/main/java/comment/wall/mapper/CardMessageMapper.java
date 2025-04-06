package comment.wall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import comment.wall.dto.CardPageQueryDTO;
import comment.wall.po.CardMessage;
import comment.wall.vo.CardMessageVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CardMessageMapper extends BaseMapper<CardMessage> {
	
	
	CardMessageVO getByCardId(String cardId);
	
	List<CardMessageVO> pageQueryCard(Integer page, Integer pageSize, Integer labelId);
}
