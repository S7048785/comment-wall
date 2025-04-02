<template>
  <div class="wall-image">
    <div class="title">图片墙</div>
    <div class="slogan">很多事情值得记录，当然也值得回味</div>
    <div class="label-list">
      <el-radio-group v-model="currentLabel" size="large">
        <el-radio-button
          v-for="item in imgLabelList"
          :key="item"
          :value="item"
          :label="item"
        />
      </el-radio-group>
    </div>
    <div class="card">
      <!-- 图像卡片-->
      <ImageCard
        class="card-item"
        v-for="(item, index) in imgList"
        :src="item.src"
        :key="item.id"
      />
      <!-- <NodeCard
        class="card-item"
        v-for="(item, index) in cardList"
        :key="item.id"
        :card="item"
        :isActive="isModalOpen && targetCard.id === item.id"
        @mousedown.stop="modalToggle(item)"
      /> -->
    </div>
    <div class="add" ref="add" v-show="!isModalOpen" @click="">
      <svg class="icon" aria-hidden="true">
        <use xlink:href="#icon-xinzeng"></use>
      </svg>
    </div>
    <!-- <transition name="modal-fade">
      <CommentModal v-show="isModalOpen" :card="targetCard" />
    </transition> -->
  </div>
</template>

<script setup lang="ts">
import { ref, reactive, onMounted, onUnmounted } from "vue";
import emitter from "@/utils/emitter";
import { imgLabelList, imgList } from "@/utils/data";
import ImageCard from "./components/ImageCard.vue";

const currentLabel = ref(imgLabelList[0]);
const add = ref<HTMLDivElement>();

// 监听滚动条，动态调整add的位置
function noteHeight() {
  // 200为底部栏高度
  if (scrollY + innerHeight + 200 >= document.body.scrollHeight) {
    (add.value as HTMLDivElement).style.bottom =
      scrollY + innerHeight + 200 - document.body.scrollHeight + 50 + "px";
  } else {
    (add.value as HTMLDivElement).style.bottom = "50px";
  }
}
// 弹窗显示状态
const isModalOpen = ref(false);

/**
 * 切换弹窗状态
 * @param card 卡片数据，若为null，表示关闭弹窗
 */
// function modalToggle(card?: Card) {
// 为null时表示点击了卡片以外的区域，所以关闭弹窗
// if (card == null || card.id == targetCard.value.id) {
//   isModalOpen.value = false;
//   targetCard.value.id = "";

// window.setTimeout(() => {
//   targetCard.value.id = "";
// }, 300);
// return;
// }
// 打开当前卡片弹窗
// isModalOpen.value = true;
// if (Object.keys(card).length === 0) {
// 若card为空对象，表示为新增卡片，则使用默认数据
// targetCard.value = useCardStore().currentCard;
// } else {
// targetCard.value = { ...card };
// }
// }
onMounted(() => {
  window.addEventListener("scroll", noteHeight);
  // 绑定弹窗切换事件
  // emitter.on("modal-toggle", modalToggle);
});
onUnmounted(() => {
  window.removeEventListener("scroll", noteHeight);
  // emitter.off("modal-toggle");
});
</script>

<style lang="less" scoped>
.wall-image {
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
    .el-radio-group {
      justify-content: center;
    }
    .el-radio-button {
      padding: 0 5px;

      :deep(.el-radio-button__inner) {
        width: 70px;
        box-sizing: border-box;
        border: 1px solid transparent;
        background: none;
        color: @gray-1;
        &:hover {
          border-color: #ccc;
          border-radius: 25px;
          color: #000;
        }
      }
      &.is-active {
        :deep(.el-radio-button__inner) {
          color: @gray-0;
          font-weight: 600;
          box-shadow: none;
          border: 1px solid;
          border-radius: 25px;
        }
      }
    }
  }
  .card {
    width: 90%;
    margin: auto;
    column-count: 4;
    // column-gap: 10px;
    // flex-wrap: wrap;
    // margin: auto;
    margin-block: 20px;
    .card-item {
      margin: 10px;
    }
  }
  .add {
    position: fixed;
    right: 50px;
    bottom: 50px;
    color: #ccc;
    border-radius: 50%;
    outline: 5px solid;
    font-size: 2em;
    height: 42px;
    cursor: pointer;
    transition: transform 0.5s ease;
    &:hover {
      background-color: #000;
      transform: rotate(180deg);
    }
  }
  .modal-fade-enter-active {
    /*from*/
    animation: ani 0.3s;
  }
  .modal-fade-leave-active {
    /*to*/
    animation: ani 0.3s reverse;
  }

  /*动画样式*/
  @keyframes ani {
    from {
      transform: translateX(100%);
    }
    to {
      transform: translateX(0);
    }
  }
}
</style>
