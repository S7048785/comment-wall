<template>
  <div class="comment-modal" v-show="isModalOpen" @click.stop="">
    <div class="modal-head">
      <p class="modal-name">
        写{{ currentOption.id === "" ? "留言" : "评论" }}
      </p>
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
              :disabled="currentOption.id !== ''"
              :class="{ disabled: currentOption.id !== '' }"
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
          maxlength="75"
          :readonly="currentOption.id !== ''"
          input-style="height: 100%"
        />
        <el-input
          placeholder="签名"
          class="name"
          :readonly="currentOption.id !== ''"
          v-model="currentOption.username"
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
        <el-radio-group
          v-model="currentOption.label"
          :disabled="currentOption.id !== ''"
          size="large"
        >
          <el-radio-button
            v-for="(label, index) in cardLabelList.slice(1)"
            :key="label"
            :value="label"
            :label="label"
            :disabled="currentOption.id !== ''"
          ></el-radio-button>
        </el-radio-group>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, watch } from "vue";
import emitter from "@/utils/emitter";
import { type Card } from "@/types/interface/card";
import { Label } from "@/types/enum/label";
import { cardColorList, cardLabelList } from "@/utils/data";
import { useCardStore } from "@/stores/card";
// const labelList = cardLabelList.splice(1);
const cardStore = useCardStore();
const props = defineProps<{
  card: Card;
}>();
const currentOption = ref({ ...props.card });

const isModalOpen = ref(true);

watch(
  () => props.card,
  (newVal) => {
    currentOption.value = { ...newVal };
    // console.log(newVal);
  }
);

watch(
  () => currentOption.value,
  (newVal) => {
    if (newVal.id === "") {
      cardStore.setCurrentCard({ ...newVal });
    }
  },
  {
    deep: true,
  }
);

// 关闭弹窗
function close() {
  emitter.emit("modal-toggle");
}
</script>

<style lang="less" scoped>
.comment-modal {
  width: 400px;
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
        font-size: 18px;
        height: 80%
        // flex: 1;
      }
      .name {

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
      :deep(.el-input__inner) {
        padding-right: 10px;
        text-align: right;
        font-size: 16px;
      }
    }
  }
  .labels {
    .label-li {
      margin-block: 10px;
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
        background: #ccc;
        border-radius: 15px;
      }
    }
    :deep(.el-radio-button__inner) {
      width: 100%;
      background: none;
      border: none;
      &:hover {
        color: black;
      }
    }
  }
}
</style>
