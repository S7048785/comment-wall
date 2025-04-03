import { defineStore } from "pinia";
import { ref, reactive } from "vue";
import { type MsgCard, type ImgCard } from "@/types/interface/card";
import { cardColorList, cardLabelList } from "@/utils/data";

export const useCardStore = defineStore("card", () => {
  /* state */

  const currentMsgCard = ref<MsgCard | ImgCard>({
    id: "",
    date: "2025.04.01",
    username: "",
    message: "",
    liked: false,
    likeCount: 0,
    commentCount: 0,
    type: "msg",
    color: cardColorList[0],
    label: cardLabelList[1],
  });
  const currentImgCard = ref<ImgCard>({
    id: "",
    date: "2025.04.01",
    type: "img",
    liked: false,
    likeCount: 0,
    commentCount: 0,
    url: "",
    label: cardLabelList[1],
  });
  // actions
  function setCurrentMsgCard(card: MsgCard) {
    currentMsgCard.value = card;
  }
  function setCurrentImgCard(card: ImgCard) {
    currentMsgCard.value = card;
  }

  return {
    cardColorList,
    currentMsgCard,
    currentImgCard,
    setCurrentMsgCard,
    setCurrentImgCard,
  };
});
