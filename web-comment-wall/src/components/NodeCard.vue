<template>
  <div class="node-card" :style="{ width: `${width}px` }">
    <div class="top">
      <span class="date">2025.03.17</span>
      <span class="label">其他</span>
    </div>
    <div class="body"><div>路上总有阴影，但你抬头就能看到光。</div></div>
    <div class="foot">
      <div class="foot-left">
        <div class="like" @click="likeActive">
          <span class="like-icon">
            <svg class="icon" aria-hidden="true">
              <use xlink:href="#icon-aixin"></use>
            </svg>
          </span>
          <span class="like-count">3</span>
        </div>
        <div class="comment">
          <span class="comment-icon">
            <svg class="icon" aria-hidden="true">
              <use xlink:href="#icon-pinglun"></use>
            </svg>
          </span>
          <span class="comment-count">5</span>
        </div>
      </div>
      <div class="foot-right"><div class="user">用户</div></div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive, withDefaults } from "vue";
const props = withDefaults(
  defineProps<{
    width?: number;
  }>(),
  {
    width: 288,
  }
);
function likeActive() {
  console.log("like");
}
</script>

<style lang="less" scoped>
// @import url(/src/assets/font/iconfont.css);
.node-card {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  height: 240px;
  padding: 20px;
  background-color: rgba(252, 175, 162, 0.3);
  .top {
    display: flex;
    justify-content: space-between;
    span {
      font-size: @size-14;
    }
  }
  .body {
    display: flex;
    height: 100%;
    padding-block: 20px;
    font-family: "楷体", "Courier New", Courier, monospace;
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
        &:hover {
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
  }
}
</style>
