<template>
  <div class="new-card">
    <div class="colors">
      <div class="color-li">
        <el-radio-group v-model="currentColor" size="large">
          <el-radio-button
            v-for="(color, index) in cardColorList"
            :key="color"
            :style="{ backgroundColor: color }"
            :value="color"
            size="small"
          />
        </el-radio-group>
      </div>
      <!-- <p
        class="color-li"
        v-for="(color, index) in cardColorList"
        :key="index"
        :style="{ backgroundColor: color }"
      ></p> -->
    </div>
    <div class="card-main">
      <el-input
        type="textarea"
        v-model="message"
        placeholder="留言..."
        class="message"
        resize="none"
        show-word-limit
        :rows="8"
        maxlength="120"
        input-style="height: 100%"
      />
      <el-input placeholder="签名" class="name" v-model="name" maxlength="10" />
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive, toRef } from "vue";
import { cardColorList, cardLabelList } from "@/utils/data";
const currentColor = ref(cardColorList[0]);

const message = ref("");
const name = ref("");
const label = ref(cardLabelList[0]);
</script>

<style lang="less" scoped>
.new-card {
  padding: 0 20px;
  .colors {
    .color-li {
      display: flex;
      justify-content: center;
      margin-block: 10px;
      .el-radio-button {
        margin: 2px;

        :deep(.el-radio-button__inner) {
          width: 100%;
          background: none;
          height: 24px;
          border: 1px solid transparent;
          // box-shadow: 0 0 0 0px #fff inset;
        }
        :deep(.el-radio-button__inner, .el-radio-button:first-child) {
          border-radius: 0px;
        }
        // &.is-active {
        //   :deep(.el-radio-button__inner) {
        //     color: none;
        //     font-weight: 600;
        //     box-shadow: none;
        //     border: 1px solid #ccc;
        //     // border-radius: 5px;
        //   }
        // }
      }
    }
    .color-selected {
      border: 1px solid rgba(59, 115, 240, 1);
    }
  }
  .card-main {
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    height: 240px;
    width: 100%;
    background: rgba(252, 175, 162, 0.31);
    padding: 12px;
    box-sizing: border-box;
    .message {
      flex: 6;
    }
    .name {
      // flex: 1;
    }
    :deep(.el-textarea__inner) {
      box-shadow: none;
      background-color: transparent;
    }
    :deep(.el-input__wrapper) {
      background-color: transparent;
      // box-shadow: none;
      box-shadow: 0 0 0 1px #fff inset;
    }
  }
}
</style>
