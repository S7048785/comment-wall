import { defineStore } from "pinia";
import { ref, reactive } from "vue";
import { ElMessage } from "element-plus";
import { getCardMsgListAPI } from "@/api/card";
import { type MsgCard, type ImgCard } from "@/types/interface/card";
import { cardColorList, msgLabel, imgLabel } from "@/utils/data";

export const useCardStore = defineStore("card", () => {
  /* state */
  const cardMsgList = ref<MsgCard[]>([]);
  // 分页查询
  const page = ref(1);
  const size = ref(15);
  const isNone = ref(false);
  async function getCartMsgList(label?: number) {
    if (isNone.value) {
      return;
    }
    const res: any = await getCardMsgListAPI(page.value, size.value, label);
    cardMsgList.value.push(...res.records);
    page.value += 1;
    if (res.total < size.value) {
      isNone.value = true;
    }
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
    cardColorList,
    cardMsgList,
    currentMsgCard,
    currentImgCard,
    setCurrentMsgCard,
    setCurrentImgCard,
    getCartMsgList,
  };
});
