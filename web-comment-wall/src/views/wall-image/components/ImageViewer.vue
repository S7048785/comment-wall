<template>
  <div class="image-viewer">
    <div class="image-container" v-click-outside="() => $emit('viewToggle')">
      <!-- <div > -->
      <img :src="$props.card.url" alt="" @mousedown.stop="$emit('comment')" />

      <!-- </div> -->
    </div>
    <div class="btn-group" @mousedown.stop>
      <el-button-group>
        <el-button size="default" @click="$emit('like', $props.card)">
          <Heart v-model="$props.card.liked" />
        </el-button>
        <el-button size="default" @click="$emit('comment', $props.card)">
          <svg class="icon comment" aria-hidden="true">
            <use xlink:href="#icon-pinglun"></use>
          </svg>
        </el-button>
      </el-button-group>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive } from "vue";
import { type ImgCard } from "@/types/interface/card";
import emitter from "@/utils/emitter";
const props = defineProps<{
  card: ImgCard;
}>();
</script>

<style lang="less" scoped>
.image-viewer {
  position: fixed;
  top: 6%;
  left: 0;
  width: 100%;
  height: 94%;
  z-index: 1;
  background-color: #080808a1;
  // 背景模糊
  backdrop-filter: blur(10px);
  .image-container {
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    img {
      width: 100%;
      height: 100%;
    }
  }
  .btn-group {
    position: absolute;
    bottom: 5px;
    left: 50%;
    height: 40px;
    display: flex;
    width: 30%;
    justify-content: center;
    align-items: center;
    background-color: #ffffffa6;
    border-radius: 15px;
    transform: translateX(-50%);
    .el-button-group {
      width: 60%;
      height: 100%;
      display: flex;
      justify-content: center;
      border: none;
      box-shadow: none;
    }
    button {
      // margin-right: 10px;
      height: 100%;
      flex: 1;
      background-color: transparent;
      border: none;
      // box-shadow: none;
      border-radius: 0;
      box-sizing: border-box;
      .comment {
        color: rgb(0, 142, 189);
      }
      &:hover {
        background-color: #9b9b9b;
      }
    }
  }
}
</style>
