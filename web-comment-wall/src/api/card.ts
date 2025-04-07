import request from "@/utils/request";
import { type MsgCard, type ImgCard } from "@/types/interface/card";

/**
 *  分页查询留言卡片列表
 * @param page 页码
 * @param size 每页数量
 * @param label 标签
 * @returns
 */
export const getCardMsgListAPI = (
  page?: number,
  size?: number,
  label?: number
) => {
  return request({
    url: "/card-message/page",
    params: {
      page,
      size,
      label,
    },
  });
};

/**
 * 新增留言卡片
 */
export const addCardMsgAPI = (card: MsgCard) => {
  return request({
    url: "/card-message",
    method: "post",
    data: {
      userId: card.username,
      content: card.content,
      color: card.color,
      label: card.label,
    },
  });
};
