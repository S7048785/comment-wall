package comment.wall;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.json.JSONUtil;
import comment.wall.constant.RedisConstant;
import comment.wall.service.ILabelService;
import comment.wall.vo.LabelVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Slf4j
@SpringBootTest
public class RedisTests {
	
	@Autowired
	StringRedisTemplate redisTemplate;
	@Autowired
	ILabelService labelService;
	@Test
	public void testRedis() {
		List<LabelVO> labelList = labelService.getLabelList(1);
		//String jsonStr = JSONUtil.toJsonStr(labelList);
		//redisTemplate.opsForValue().set("label:1", jsonStr);
		//String s = redisTemplate.opsForValue().get("label:1");
		//List<LabelVO> bean = JSONUtil.toBean(s, new TypeReference<>() {
		//}, true);
		//log.info("{}",bean);
		
		
		//redisTemplate.opsForHash().putAll(RedisConstant.REDIS_KEY_PREFIX_LABEL + 1, );
	}
}
