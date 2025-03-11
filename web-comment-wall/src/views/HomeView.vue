<script setup lang="ts">
import { api1 } from "@/api/test";
const send = async () => {
  try {
    const res = await api1(); // 这里假设api1是一个函数，如果是Promise则直接使用await
    console.log(res);
  } catch (error) {
    console.error("请求出错:", error);
  }
};
</script>

<template>
  <div class="home-view">
    <!-- <button @click="send">点击发送请求</button> -->
  </div>
</template>

<style lang="less">
button {
  background-color: @primary-color;
}
.home-view {
  height: 1600px;
  // background-color: black;
}
</style>
