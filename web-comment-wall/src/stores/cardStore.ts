import { defineStore } from "pinia";
import { ref, reactive, computed } from "vue";
import {
  addCardMsgAPI,
  deleteCardMsgAPI,
  getCardImgListAPI,
  getCardMsgByIdAPI,
  getCardMsgListAPI,
  updateCardMsgAPI,
} from "@/api/card";
import { type MsgCard, type ImgCard } from "@/types/interface/card";
import { cardColorList, msgLabel, imgLabel } from "@/utils/data";

export const useCardStore = defineStore("card", () => {
  // 留言卡片
  const cardMsgList = ref<MsgCard[]>([]);
  // load
  const isLoading = ref(true);
  // 缓存label
  const labelCache = ref(0);
  // 分页条件
  const msgPage = ref(1);
  const msgSize = ref(15);
  const isNone = ref(false);
  // 留言卡片分页查询
  async function getCardMsgList(label: number = 0, refresh: boolean = false) {
    // 刷新留言
    if (refresh) {
      if (labelCache.value === label) {
        return;
      }
      cardMsgList.value = [];
      msgPage.value = 1;
      isNone.value = false;
    }
    if (isNone.value) {
      isLoading.value = false;
      return;
    }
    isLoading.value = true;
    const res: any = await getCardMsgListAPI(
      msgPage.value,
      msgSize.value,
      label
    );
    isLoading.value = false;
    cardMsgList.value.push(...res.records);
    msgPage.value += 1;
    if (res.total < msgSize.value) {
      isNone.value = true;
    }
    labelCache.value = label;
  }
  // id查询卡片
  async function getCardMsgById(id: number) {
    const res: any = await getCardMsgByIdAPI(id);
    currentMsgCard.value = res;
  }

  // 添加留言卡片
  async function addCardMsg(card: MsgCard) {
    const res: any = await addCardMsgAPI(card);
    console.log(res.data);

    cardMsgList.value.unshift(res.data as MsgCard);
    console.log(cardMsgList.value);
  }

  // 修改留言卡片
  async function updateCardMsg(card: MsgCard) {
    const res: any = await updateCardMsgAPI(card);
    // 修改成功
    if (res.code === 1) {
      cardMsgList.value = cardMsgList.value.map((item) => {
        if (item.id === card.id) {
          return card;
        }
        return item;
      });
    }
  }

  // 删除留言
  async function deleteCardMsg(id: number) {
    const res: any = await deleteCardMsgAPI(id);
    // 删除成功
    if (res.code === 1) {
      cardMsgList.value = cardMsgList.value.filter((item) => {
        return item.id !== id.toString();
      });
    }
  }

  // 图片卡片
  const cardImgList = ref<ImgCard[]>([]);
  // 图片卡片分页查询
  async function getCardImgList(label?: number) {
    const res: any = await getCardImgListAPI(
      msgPage.value,
      msgSize.value,
      label
    );
    cardImgList.value.push(...res.records);
  }

  const currentMsgCard = ref<MsgCard>({
    id: "",
    date: "2025.04.01",
    username: "",
    content: "",
    liked: false,
    likeCount: 0,
    commentCount: 0,
    type: "msg",
    color: cardColorList[0],
    label: msgLabel[1],
  });
  const currentImgCard = ref<ImgCard>({
    id: "",
    date: "2025.04.01",
    type: "img",
    liked: false,
    likeCount: 0,
    commentCount: 0,
    url: "",
    label: imgLabel[1],
  });
  // actions
  function setCurrentMsgCard(card: MsgCard) {
    currentMsgCard.value = card;
  }
  function setCurrentImgCard(card: ImgCard) {
    currentImgCard.value = card;
  }

  return {
    cardMsgList,
    cardImgList,
    currentMsgCard,
    currentImgCard,
    isLoading,
    msgPage,
    msgSize,
    isNone,
    setCurrentMsgCard,
    setCurrentImgCard,
    getCardMsgList,
    getCardImgList,
    getCardMsgById,
    addCardMsg,
    updateCardMsg,
    deleteCardMsg,
  };
});
