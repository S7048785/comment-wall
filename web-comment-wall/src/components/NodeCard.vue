<template>
  <div class="node-card" :style="{ width: `${width}px`, background: bgcolor }">
    <div class="top">
      <span class="date">{{ dateStr }}</span>
      <span class="label">{{ card.category }}</span>
    </div>
    <div class="body">
      {{ card.content }}
    </div>
    <div class="foot">
      <div class="foot-left">
        <div class="like" @click="likeActive">
          <span class="like-icon">
            <svg
              class="icon"
              aria-hidden="true"
              :style="{ color: card.liked ? 'red' : '' }"
            >
              <use :xlink:href="`#icon-aixin${card.liked ? '1' : ''}`"></use>
            </svg>
          </span>
          <span class="like-count">{{ card.likeCount }}</span>
        </div>
        <div class="comment" @click="commentActive">
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
import { ref, reactive, withDefaults, onMounted, computed, watch } from "vue";
import { type Card } from "@/types/interface/card";
import { Label } from "@/types/enum/label";

const { width = 288, card } = defineProps<{
  width?: number;
  bgcolor?: string;
  card: Card;
}>();

const dateStr = computed(() => {
  return `${card.date.getFullYear()}.${("0" + (card.date.getMonth() + 1)).slice(
    -2
  )}.${("0" + card.date.getDate()).slice(-2)}`;
});

// 点赞功能
function likeActive() {
  console.log("like");
  card.liked = !card.liked;
  // 发送点赞请求接口
}

// 评论功能
function commentActive() {
  console.log("comment");
}

onMounted(() => {
  console.log("mounted");
  console.log(card);
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
  min-height: 200px;
  padding: 20px;
  padding-bottom: 10px;
  background-color: @card-color-2;
  .top {
    display: flex;
    justify-content: space-between;
    span {
      font-size: @size-14;
      color: #888;
    }
  }
  .body {
    display: flex;
    // height: 100%;
    padding-top: 10px;
    text-indent: 1em;
    font-size: 16px;
    letter-spacing: 2px;
    justify-content: center;
    // overflow-x: hidden;
    white-space: wrap;
    font-family: Arial, Helvetica, sans-serif;
  }
  .foot {
    display: flex;
    justify-content: space-between;
    .foot-left {
      display: flex;
      flex: 1;
      justify-content: flex-start;
      div {
        margin-right: 10px;
        &.like:hover {
          color: red;
          cursor: pointer;
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
</style>
