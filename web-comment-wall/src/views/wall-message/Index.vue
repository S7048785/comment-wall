<template>
  <div class="wall-message">
    <div class="title">留言墙</div>
    <div class="slogan">很多事情值得记录，当然也值得回味</div>
    <div class="label-list">
      <el-radio-group v-model="currentLabel" size="large">
        <el-radio-button
          v-for="item in labelStore.labelList"
          :key="item.id"
          :value="item.name"
          :label="item.name"
          @click="cardStore.getCartMsgList(item.id, true)"
        />
      </el-radio-group>
    </div>
    <div class="card" v-infinite-scroll="cardStore.getCartMsgList">
      <div class="container">
        <NodeCard
          class="card-item"
          v-for="(item, index) in cardStore.cardMsgList"
          :key="item.id"
          :card="item"
          :isActive="isModalOpen && targetCard.id === item.id"
          @like="likeActive(item)"
          @comment="modalToggle(item)"
          @mouseup.left.stop="modalToggle(item)"
        />
      </div>
      <div
        class="loading"
        infinite-scroll-distance="200"
        v-loading="cardStore.isLoading"
        element-loading-background="#eee"
        element-loading-text="Loading..."
      ></div>
    </div>
    <div
      class="add"
      ref="add"
      v-show="!isModalOpen"
      @click="modalToggle({} as MsgCard)"
    >
      <svg class="icon" aria-hidden="true">
        <use xlink:href="#icon-xinzeng"></use>
      </svg>
    </div>
    <!-- <transition name="modal-fade"> -->
    <CommentModal
      v-model="isModalOpen"
      :top="8"
      :card="targetCard"
      @comment="CommentActive"
      v-click-outside="modalToggle"
    />
    <!-- </transition> -->
  </div>
</template>

<script setup lang="ts">
import NodeCard from "@/views/wall-message/components/NoteCard.vue";
import { ref, reactive, onMounted, onUnmounted } from "vue";
import { type MsgCard } from "@/types/interface/card";
import emitter from "@/utils/emitter";
import { msgLabel, cardColorList } from "@/utils/data";
import { useCardStore } from "@/stores/cardStore";
import { useLabelStore } from "@/stores/labelStore";

const labelStore = useLabelStore();
labelStore.getLabelList(1);
const cardStore = useCardStore();

const currentLabel = ref(msgLabel[0]);
let targetCard: any = ref<MsgCard>({
  ...(cardStore.currentMsgCard as MsgCard),
});
// 卡片列表
const cardList = cardStore.cardMsgList;

// 点赞
function likeActive(card: MsgCard) {
  card.liked = !card.liked;
  card.likeCount += card.liked === true ? 1 : -1;
  // TODO: 发送点赞请求
}

// 评论成功
function CommentActive(cardId: string) {
  cardList.forEach((item) => {
    if (item.id === cardId) {
      item.commentCount += 1;
    }
  });
}

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
function modalToggle(card?: MsgCard) {
  // 为null时表示点击了卡片以外的区域，所以关闭弹窗
  if (card == null || targetCard.value.id === card.id) {
    isModalOpen.value = false;
    targetCard.value.id = "";
    return;
  }
  // 打开当前卡片弹窗
  isModalOpen.value = true;
  if (Object.keys(card).length === 0) {
    // 若card为空对象，表示为新增卡片，则使用默认数据
    targetCard.value = cardStore.currentMsgCard;
  } else {
    targetCard.value = { ...card };
  }
  return;
}
function ESCExitView(event: KeyboardEvent) {
  if (event.key === "Escape") {
    if (isModalOpen.value) {
      modalToggle();
    }
  }
}
onMounted(async () => {
  window.addEventListener("keydown", ESCExitView);
  window.addEventListener("scroll", noteHeight);
  // 绑定弹窗切换事件
  emitter.on("modal-toggle", modalToggle);
});
onUnmounted(() => {
  window.removeEventListener("keydown", ESCExitView);
  window.removeEventListener("scroll", noteHeight);
  emitter.off("modal-toggle");
});
</script>

<style lang="less" scoped>
.wall-message {
  // Your styles here
  // min-height: 700px;
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
        // transition: none;
        border: 1px solid transparent;
        background: none;
        color: @gray-1;
        // border-radius: 25px;
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
    // width: 90%;
    // flex-wrap: wrap;
    // height: 400px;
    margin-block: 20px;
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    .loading {
      height: 400px;

      &.el-loading-parent--relative {
      }
    }

    .container {
      width: 80%;
      // min-height: 400px;
      column-count: 4;
      column-gap: 20px;

      .card-item {
        break-inside: avoid;
        margin-bottom: 10px;
      }
    }

    @media (max-width: 1385px) {
      .container {
        column-count: 3;
      }
    }

    @media (max-width: 1045px) {
      .container {
        column-count: 2;
      }
    }

    @media (max-width: 700px) {
      .container {
        display: flex;
        flex-direction: column;
        align-items: center;
      }
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
}
</style>
