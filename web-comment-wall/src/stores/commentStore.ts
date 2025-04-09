import { ref } from "vue";
import { defineStore } from "pinia";
import { type CardComment } from "@/types/interface/comment";
import { getCommentAPI, addCommentAPI } from "@/api/comment";
import type { ImgCard, MsgCard } from "@/types/interface/card";

export const useCommentStore = defineStore("comment", () => {
  const commentList = ref<CardComment[]>([]);
  // 分页条件
  const page = ref(1);
  const size = ref(5);
  const isNone = ref(false);
  const currentCardId = ref<string>();

  /**
   * 获取评论
   * @param cardId 卡片id
   * @param category 分类 1留言 2图片
   * @param refresh 是否刷新
   * @returns
   */
  const getCommentList = async (
    cardId: string,
    category: 1 | 2,
    refresh: boolean = false
  ) => {
    // 刷新评论
    if (refresh) {
      page.value = 1;
      isNone.value = false;
      commentList.value = [];
    }
    // 停止更新评论
    if (isNone.value) {
      return;
    } else {
      const res: any = await getCommentAPI({
        page: page.value,
        size: size.value,
        cardId: parseInt(cardId),
        category,
      });
      commentList.value.push(...res.records);
      page.value++;
      if (parseInt(res.total) < size.value) {
        isNone.value = true;
      }
    }
  };

  // 发送评论
  const addComment = async (
    cardId: string,
    content: string,
    category: 1 | 2
  ) => {
    const res: any = await addCommentAPI(cardId, content, category);
    commentList.value.unshift(res.data);
  };

  return { commentList, size, isNone, getCommentList, addComment };
});
