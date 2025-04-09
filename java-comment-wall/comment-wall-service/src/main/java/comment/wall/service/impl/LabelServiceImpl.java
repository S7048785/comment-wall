package comment.wall.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import comment.wall.constant.RedisConstant;
import comment.wall.mapper.LabelMapper;
import comment.wall.po.Label;
import comment.wall.service.ILabelService;
import comment.wall.vo.LabelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabelServiceImpl extends ServiceImpl<LabelMapper, Label> implements ILabelService {
	@Autowired
	StringRedisTemplate RedisTemplate;
	@Override
	public List<LabelVO> getLabelList(Integer category) {
		// 查询redis
		String json = RedisTemplate.opsForValue().get(RedisConstant.REDIS_KEY_PREFIX_LABEL + category);
		if (StrUtil.isNotBlank(json)) {
			// 缓存命中  json转为VO
			return JSONUtil.toBean(json, new TypeReference<>() {}, true);
		}
		
		List<Label> list = list(new LambdaQueryWrapper<Label>().in(Label::getCategory, category, 0));
		// 封装成VO
		List<LabelVO> map = CollUtil.map(list, label -> new LabelVO(label.getId(), label.getName()), true);
		// 存入redis
		RedisTemplate.opsForValue().set(RedisConstant.REDIS_KEY_PREFIX_LABEL + category, JSONUtil.toJsonStr(map));
		return map;
	}
}
