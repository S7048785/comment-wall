<template>
  <div class="comment-modal" v-show="isModalOpen" @click.stop="">
    <div class="modal-head">
      <p class="modal-name">写评论</p>
      <svg class="icon" aria-hidden="true" @click="close">
        <use xlink:href="#icon-guanbi"></use>
      </svg>
    </div>
    <!-- <NewCard /> -->
    <div class="new-card">
      <div class="colors">
        <div class="color-li">
          <el-radio-group v-model="currentOption.color" size="large">
            <el-radio-button
              v-for="(color, index) in cardColorList"
              :key="color"
              :style="{ backgroundColor: color }"
              :value="color"
              size="small"
            />
          </el-radio-group>
        </div>
      </div>
      <div class="card-main" :style="{ backgroundColor: currentOption.color }">
        <el-input
          type="textarea"
          v-model="currentOption.message"
          placeholder="留言..."
          class="message"
          resize="none"
          show-word-limit
          :rows="8"
          maxlength="120"
          input-style="height: 100%"
        />
        <el-input
          placeholder="签名"
          class="name"
          v-model="currentOption.name"
          maxlength="10"
        />
      </div>
    </div>
    <div class="modal-head">
      <div class="modal-head">
        <p class="modal-name">选择标签</p>
      </div>
    </div>
    <div class="labels">
      <div class="label-li">
        <el-radio-group v-model="currentOption.label" size="large">
          <el-radio-button
            v-for="(label, index) in cardLabelList"
            :key="label"
            :value="label"
            :label="label"
          ></el-radio-button>
        </el-radio-group>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive } from "vue";
import emitter from "@/utils/emitter";
import { cardColorList, cardLabelList } from "@/utils/data";

const currentOption = reactive({
  name: "",
  message: "",
  color: cardColorList[0],
  label: cardLabelList[1],
});
const isModalOpen = ref(true);
// 关闭弹窗
function close() {
  emitter.emit("modal-toggle");
}
</script>

<style lang="less" scoped>
.comment-modal {
  width: 360px;
  height: 100%;
  position: fixed;
  right: 0px;
  top: 58px;
  z-index: 1000;
  background-color: #ffffffcc;
  box-shadow: 0px 0px 20px 0px #00000014;
  backdrop-filter: blur(10px);
  padding: 20px;
  .modal-head {
    display: flex;
    justify-content: space-between;
    .modal-name {
      font-size: 16px;
      color: @gray-1;
      font-weight: 600;
    }
    svg {
      color: green;
      cursor: pointer;
    }
  }
  .new-card {
    padding: 0 20px;
    margin-bottom: 20px;
    .colors {
      .color-li {
        margin-block: 10px;
        .el-radio-group {
          width: 100%;
          justify-content: space-evenly;
        }
        .el-radio-button {
          &.is-active {
            :deep(.el-radio-button__inner) {
              color: none;
              font-weight: 600;
              box-shadow: none;
              border: 1px solid black;
            }
          }
          :deep(.el-radio-button__inner) {
            width: 100%;
            background: none;
            height: 24px;
            border: 1px solid #ccc;
            border-radius: 10px;
            box-shadow: none;
          }
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
  .labels {
    .label-li {
      // margin-block: 10px;
      .el-radio-group {
        justify-content: center;
      }
    }
  }

  .el-radio-button {
    margin: 2px;
    border-radius: 10px;

    &.is-active {
      :deep(.el-radio-button__inner) {
        font-weight: 600;
        color: black;
        box-shadow: none;
        background: none;
      }
    }
    :deep(.el-radio-button__inner) {
      width: 100%;
      background: none;
      height: 24px;
      border: none;
      &:hover {
        color: black;
      }
    }
  }
}
</style>
