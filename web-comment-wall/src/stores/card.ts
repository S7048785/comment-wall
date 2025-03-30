import { defineStore } from "pinia";
import { ref, reactive } from "vue";

export const useCardStore = defineStore("card", () => {
  /* state */
  // 卡片标签列表
  const labelList = reactive<string[]>([
    "全部",
    "留言",
    "目标",
    "理想",
    "过去",
    "未来",
    "爱情",
    "亲情",
    "友情",
    "秘密",
    "心跳",
    "其他",
  ]);
  // 卡片颜色列表
  const cardColorList = reactive<string[]>([
    "#fcafa24d",
    "#c7f58c79",
    "#bfefff",
    "#88c1f4c1",
    "#f7a4d3e1",
    "#fff6bba3",
    "#adffde89",
    "#f2dffad0",
  ]);
  // getters

  // actions

  return {
    labelList,
    cardColorList,
  };
});
