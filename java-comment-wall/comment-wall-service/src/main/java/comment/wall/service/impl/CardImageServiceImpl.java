package comment.wall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import comment.wall.mapper.CardImageMapper;
import comment.wall.po.CardImage;
import comment.wall.service.ICardImageService;
import org.springframework.stereotype.Service;

@Service
public class CardImageServiceImpl extends ServiceImpl<CardImageMapper, CardImage> implements ICardImageService {
}
