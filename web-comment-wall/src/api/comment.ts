import request from "@/utils/request";

// 获取卡片评论
export const getCommentAPI = ({
  page,
  size,
  cardId,
  category,
}: {
  page: number;
  size: number;
  cardId: number;
  category: 1 | 2;
}) => {
  return request({
    url: "/comment/page",
    params: {
      page,
      size,
      cardId,
      category,
    },
  });
};

// 添加卡片评论
export const addCommentAPI = (
  cardId: string,
  content: string,
  category: 1 | 2
) => {
  return request({
    url: "/comment",
    method: "POST",
    data: {
      cardId,
      content,
      category,
    },
  });
};
