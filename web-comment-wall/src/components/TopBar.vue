<template>
  <div class="top-bar">
    <div class="logo">
      <img src="@/assets/logo.png" class="logo-img" />
      <p class="logo-name">留言墙</p>
    </div>
    <div class="menu">
      <el-button type="primary" size="default" @click="send"
        >点击按钮</el-button
      >
    </div>
    <div class="user">
      <div class="user-head">用户头</div>
      <div class="user-name"></div>
      <div class="user-menu"></div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive } from "vue";
import { ElMessage } from "element-plus";
const send = () =>
  ElMessage({
    message: "发送成功",
    type: "success",
    duration: 1000,
  });
</script>

<style lang="less" scoped>
.top-bar {
  padding: 10px 20px;
  position: fixed;
  top: 0;
  left: 0;
  z-index: 999;
  width: 100%;
  background-color: rgba(255, 255, 255, 0.8);
  box-shadow: 0px 0px 5px 0px #ccc;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.logo {
  display: flex;
  align-items: center;
  height: 100%;
  .logo-img {
    display: inline;
    width: 50px;
    height: 100%;
    margin-right: 10px;
  }
  .logo-name {
    display: inline-block;
    font-size: @size-18;
    font-weight: bold;
  }
}
</style>
