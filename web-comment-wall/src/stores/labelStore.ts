import { defineStore } from "pinia";
import { ref } from "vue";
import { getLabelAPI } from "@/api/label";
export const useLabelStore = defineStore("label", () => {
  const currentLabel = ref("全部");
  const labelList = ref();
  // 获取标签列表
  const getLabelList = async (category: 1 | 2) => {
    const res: any = await getLabelAPI(category);
    labelList.value = res.data;
  };

  return { currentLabel, labelList, getLabelList };
});
