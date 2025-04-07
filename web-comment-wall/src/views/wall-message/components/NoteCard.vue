<template>
  <div
    class="node-card"
    :class="{ 'is-active': isActive }"
    :style="{ width: `${width}px`, background: card.color }"
  >
    <div class="top">
      <span class="date">{{ card.date }}</span>
      <span class="label">{{ card.label }}</span>
    </div>
    <div class="body">
      {{ card.content }}
    </div>
    <div class="foot">
      <div class="foot-left">
        <div class="like" @click="$emit('like', card.id)" @mouseup.stop>
          <span class="like-icon">
            <Heart v-model="card.liked" />
            <!-- <svg
              class="icon"
              aria-hidden="true"
              :style="{ color: card.liked ? 'red' : '' }"
            >
              <use :xlink:href="`#icon-aixin${card.liked ? '1' : ''}`"></use>
            </svg> -->
          </span>
          <span class="like-count">{{ card.likeCount }}</span>
        </div>
        <div class="comment" @click="commentActive" @mouseup.stop>
          <span class="comment-icon">
            <svg class="icon" aria-hidden="true">
              <use xlink:href="#icon-pinglun"></use>
            </svg>
          </span>
          <span class="comment-count">{{ card.commentCount }}</span>
        </div>
      </div>
      <div class="foot-right" ref="asd">
        <div class="user">{{ card.username }}</div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { onMounted } from "vue";
import { type MsgCard } from "@/types/interface/card";
import emitter from "@/utils/emitter";

const {
  width = 288,
  card,
  isActive = false,
} = defineProps<{
  width?: number;
  card: MsgCard;
  isActive?: boolean;
}>();

defineEmits<{
  like: [cardId: string];
  comment: [void];
}>();
// const dateStr = computed(() => {
//   return `${card.date.getFullYear()}.${("0" + (card.date.getMonth() + 1)).slice(
//     -2
//   )}.${("0" + card.date.getDate()).slice(-2)}`;
// });

// 评论功能
function commentActive() {
  // 触发聚焦
  emitter.emit("commentFocus", card);
}

onMounted(() => {
  // console.log("mounted");
  // console.log(card);
});
</script>

<style lang="less" scoped>
// @import url(/src/assets/font/iconfont.css);
@font-face {
  font-family: fa;
  src: url(/src/assets/font/huangkaihuaLawyerfont-2.ttf);
}
.node-card {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  min-height: 100px;
  padding: 20px;
  background-color: @card-color-2;
  transition: all 0.3s ease;
  &:hover {
    // transform: translateY(-5px);
    box-shadow: 0px 0px 12px rgba(0, 0, 0, 0.12);
  }

  .top {
    display: flex;
    justify-content: space-between;
    margin-bottom: 10px;
    span {
      font-size: @size-14;
      color: #888;
    }
  }
  .body {
    // height: 100%;
    font-size: 16px;
    text-align: center;
    letter-spacing: 2px;
    // overflow-x: hidden;
    white-space: pre-wrap !important;
    font-family: Arial, Helvetica, sans-serif;
  }
  .foot {
    display: flex;
    margin-top: 10px;
    justify-content: space-between;
    .foot-left {
      display: flex;
      flex: 1;
      justify-content: flex-start;
      div {
        &.like {
          display: flex;
          margin-right: 10px;
          .heart-container {
            width: 17px;
            height: 17px;
          }
          &:hover {
            color: red;
            cursor: pointer;
          }
        }
        &.comment {
          display: flex;
        }
        &.comment:hover {
          cursor: pointer;
          color: white;
        }
      }
      span[class$="count"] {
        margin-left: 2px;
        font-size: 14px;
      }
      span[class$="icon"] {
        font-size: 12px;
        margin-right: 5px;
      }
    }
    .foot-right {
      display: flex;
      flex: 1;
      justify-content: flex-end;
    }
    .foot-right {
      .user {
        font-family: "华文仿宋", "Courier New", Courier, monospace;
      }
    }
  }
}
.is-active {
  box-shadow: 0px 0px 12px rgba(0, 0, 0, 0.12);
}
</style>
