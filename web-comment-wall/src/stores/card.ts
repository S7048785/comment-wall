import { defineStore } from "pinia";
import { ref, reactive } from "vue";
import { type Card } from "@/types/interface/card";
import { cardColorList, cardLabelList } from "@/utils/data";

export const useCardStore = defineStore("card", () => {
  /* state */

  const currentCard = ref<Card>({
    id: "",
    date: "2025.04.01",
    username: "",
    message: "",
    liked: false,
    likeCount: 0,
    commentCount: 0,
    color: cardColorList[0],
    label: cardLabelList[1],
  });
  // actions
  function setCurrentCard(card: Card) {
    currentCard.value = card;
  }

  return {
    cardColorList,
    currentCard,
    setCurrentCard,
  };
});
