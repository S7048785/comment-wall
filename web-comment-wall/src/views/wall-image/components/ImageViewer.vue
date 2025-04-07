<template>
  <div class="image-viewer">
    <div class="image-container" v-click-outside="exitView">
      <!-- <div > -->
      <img :src="card.url" alt="" @mouseup="modalOpen(false)" />

      <!-- </div> -->
    </div>
    <div class="btn-group" @mouseup.stop>
      <el-button-group>
        <el-button size="default" @click="$emit('like', card)">
          <Heart v-model="card.liked" />
        </el-button>
        <el-button size="default" @click="modalOpen(!isModalOpen)">
          <svg class="icon comment" aria-hidden="true">
            <use xlink:href="#icon-pinglun"></use>
          </svg>
        </el-button>
      </el-button-group>
    </div>
    <div>
      <CommentModal
        :card="delayCard"
        v-show="isModalOpen"
        v-click-outside="() => modalOpen(false)"
      />
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted, onUnmounted } from "vue";
import { type ImgCard } from "@/types/interface/card";
const { card } = defineProps<{
  card: ImgCard;
}>();
const emit = defineEmits<{
  like: [card: ImgCard];
  viewToggle: [];
}>();

// 延迟传递卡片数据 （只有打开弹窗时才传递）
const delayCard = ref<ImgCard>(card);
const isModalOpen = ref(false);

/**
 * 切换弹窗状态
 * @param state 卡片显示状态
 */
function modalOpen(state: boolean) {
  isModalOpen.value = state;
  if (state) {
    delayCard.value = { ...card };
  }
}

/**
 * 退出预览视图
 */
function exitView() {
  if (!isModalOpen.value) {
    emit("viewToggle");
  }
}
function ESCExitView(event: KeyboardEvent) {
  if (event.key === "Escape") {
    if (isModalOpen.value) {
      modalOpen(false);
    } else {
      emit("viewToggle");
    }
  }
}
onMounted(() => {
  document.addEventListener("keydown", ESCExitView);
});
onUnmounted(() => {
  document.removeEventListener("keydown", ESCExitView);
});
</script>

<style lang="less" scoped>
.image-viewer {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  z-index: 101;
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
      .heart-container {
        width: 20px;
        height: 20px;
      }
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
