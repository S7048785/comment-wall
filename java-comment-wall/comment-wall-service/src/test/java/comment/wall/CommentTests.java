package comment.wall;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import comment.wall.dto.CommentDTO;
import comment.wall.dto.CommentPageQueryDTO;
import comment.wall.po.Comment;
import comment.wall.service.ICommentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CommentTests {
	
	@Autowired
	private ICommentService commentService;
	
	@Test
	public void testGetComment() {
		List<Comment> list = commentService.lambdaQuery().eq(Comment::getCardId, 2).list();
		if (CollUtil.isNotEmpty(list)) {
			System.out.println(list.get(0));
		}
	}
}
