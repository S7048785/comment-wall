<script setup lang="ts">
import { RouterView } from "vue-router";
import { api1 } from "@/api/test";
import emitter from "@/utils/emitter";
import { useCardStore } from "@/stores/cardStore";
// 初始化留言卡片
useCardStore().getCartMsgList();
</script>

<template>
  <div class="home-view">
    <FixedBar />
    <!-- <TopBar /> -->
    <router-view />
    <FootBar />
  </div>
</template>

<style lang="less" scoped>
button {
  background-color: @primary-color;
}
.home-view {
  // height: 800px;
  margin-top: 58px;
  background-color: #eee;
}
</style>
