package comment.wall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import comment.wall.po.CardImage;
import comment.wall.vo.CardImageVO;
import comment.wall.vo.CardMessageVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CardImageMapper extends BaseMapper<CardImage> {
	CardImageVO queryCardById(String cardId);
	
	List<CardImageVO> pageQueryCard(Integer page, Integer pageSize, Integer labelId);
}
