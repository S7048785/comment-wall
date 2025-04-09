import request from "@/utils/request";
import { msgLabel } from "@/utils/data";
import { type MsgCard, type ImgCard } from "@/types/interface/card";

/**
 *  分页查询留言卡片列表
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
      labelId: label,
    },
  });
};

// 根据id查询留言卡片
export const getCardMsgByIdAPI = (id: number) => {
  return request({
    url: `/card-message/${id}`,
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
      content: card.content,
      color: card.color,
      labelId: msgLabel.indexOf(card.label),
    },
  });
};

// 修改留言卡片
export const updateCardMsgAPI = (card: MsgCard) => {
  return request({
    url: "/card-message",
    method: "put",
    data: {
      id: card.id,
      content: card.content,
      color: card.color,
      labelId: msgLabel.indexOf(card.label),
    },
  });
};

// 删除留言卡片
export const deleteCardMsgAPI = (id: number) => {
  return request({
    url: `/card-message/${id}`,
    method: "delete",
  });
};

// 分页查询图片卡片列表
export const getCardImgListAPI = (
  page?: number,
  size?: number,
  label?: number
) => {
  return request({
    url: "/card-image/page",
    params: {
      page,
      size,
      label,
    },
  });
};
