import { defineStore } from "pinia";
import { ref, reactive } from "vue";

export const useCategoryStore = defineStore("category", () => {
  // state
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
  // getters

  // actions

  return {
    labelList,
  };
});
