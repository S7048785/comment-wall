<template>
  <div class="comment-modal" v-show="isModalOpen" @click.stop="">
    <div class="modal-head">
      <p class="modal-name">写评论</p>
      <svg class="icon" aria-hidden="true" @click="close">
        <use xlink:href="#icon-guanbi"></use>
      </svg>
    </div>
    <NewCard />
  </div>
</template>

<script setup lang="ts">
import { ref, reactive } from "vue";
import NewCard from "@/components/NewCard.vue";
import emitter from "@/utils/emitter";

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
}
</style>
