<script setup lang="ts">
import { RouterView } from "vue-router";
import { api1 } from "@/api/test";
import emitter from "@/utils/emitter";
// const send = async () => {
//   try {
//     const res = await api1(); // 这里假设api1是一个函数，如果是Promise则直接使用await
//     console.log(res);
//   } catch (error) {
//     console.error("请求出错:", error);
//   }
// };
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
