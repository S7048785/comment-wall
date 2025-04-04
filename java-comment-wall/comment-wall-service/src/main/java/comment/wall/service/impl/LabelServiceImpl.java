package comment.wall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import comment.wall.mapper.LabelMapper;
import comment.wall.po.Label;
import comment.wall.service.ILabelService;
import org.springframework.stereotype.Service;

@Service
public class LabelServiceImpl extends ServiceImpl<LabelMapper, Label> implements ILabelService {
}
