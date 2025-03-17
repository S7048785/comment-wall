<template>
  <div class="wall-message">
    <div class="title">留言墙</div>
    <div class="slogan">很多事情值得记录，当然也值得回味</div>
    <div class="label-list">
      <el-radio-group v-model="label" size="large">
        <el-radio-button v-for="item in labelList" :key="item" :label="item" />
      </el-radio-group>
    </div>
    <div class="cart">
      <NodeCard />
    </div>
  </div>
</template>

<script setup lang="ts">
import NodeCard from "@/components/NodeCard.vue";
import { ref, reactive } from "vue";
import { useCategoryStore } from "@/stores/category";
const labelList: string[] = useCategoryStore().labelList;
const label = ref(labelList[0]);
</script>

<style lang="less" scoped>
.wall-message {
  // Your styles here
  min-height: 700px;
  padding-top: 52px;
  .title {
    padding-top: 8px;
    padding-bottom: 8px;
    font-size: 56px;
    color: @gray-0;
    text-align: center;
    font-weight: bold;
  }
  .slogan {
    color: @gray-1;
    text-align: center;
  }
  .label-list {
    display: flex;
    justify-content: center;
    margin-top: 48px;

    .el-radio-button {
      padding: 0 5px;

      :deep(.el-radio-button__inner) {
        width: 70px;
        box-sizing: border-box;
        transition: none;
        border: 1px solid transparent;
        background: none;
        color: @gray-1;
      }
      &.is-active {
        :deep(.el-radio-button__inner) {
          color: @gray-0;
          font-weight: 600;
          box-shadow: none;
          border-radius: 25px;
          border: 2px solid #000;
        }
      }
    }
  }
}
</style>
