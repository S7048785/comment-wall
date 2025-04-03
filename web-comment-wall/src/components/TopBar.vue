<template>
  <div class="top-bar" @mousedown.stop>
    <div class="logo">
      <img src="@/assets/logo.png" class="logo-img" />
      <p class="logo-name">留言墙</p>
    </div>
    <div class="menu">
      <el-menu
        mode="horizontal"
        router
        :ellipsis="false"
        :default-active="route.path"
      >
        <el-menu-item index="/wall-message">留言墙</el-menu-item>
        <el-menu-item index="/wall-image">照片墙</el-menu-item>
      </el-menu>
    </div>
    <div class="user">
      <div class="user-head"></div>
      <div class="user-name"></div>
      <div class="user-menu">
        <div class="login">登录</div>
        <div class="register">注册</div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive } from "vue";
import { useRoute } from "vue-router";
import { useRouter } from "vue-router";
import { ElMessage } from "element-plus";
import { ro } from "element-plus/es/locales.mjs";
const route = useRoute();
const router = useRouter();
const btn_click = ref(true);
const btn_toggle = () => {
  console.log(route.path);

  btn_click.value = !btn_click.value;
  router.push(btn_click.value ? "/wall-message" : "/wall-image");
};
const send = () =>
  ElMessage({
    message: "发送成功",
    type: "success",
    duration: 1000,
  });
</script>

<style lang="less" scoped>
.top-bar {
  padding: 10px 30px;
  width: 100%;
  height: 58px;
  background-color: rgba(255, 255, 255);
  box-shadow: 0px 0px 5px 0px #ccc;
  display: flex;
  justify-content: space-between;
  align-items: center;
  box-sizing: border-box;
  .logo {
    display: flex;
    align-items: center;
    height: 100%;
    flex: 1;
    .logo-img {
      display: inline;
      width: 50px;
      height: 50px;
      margin-right: 10px;
    }
    .logo-name {
      display: inline-block;
      font-size: @size-18;
      font-weight: bold;
    }
  }
  .menu {
    width: 200px;
    flex: 1;
    .el-menu-item {
      flex: 1;
    }
    .el-menu--horizontal {
      justify-content: space-around;
    }
    .el-button {
      width: 95px;
      height: 38px;
      border-radius: 25px;
      color: black;
      font-size: @size-16;
      font-weight: 400;
      &.btn-click {
        background-color: rgb(0, 123, 255);
        color: white;
      }
    }
  }
  .user {
    flex: 1;
    width: 200px;
    display: flex;
    justify-content: flex-end;
    .user-head {
      border-radius: 50%;
      width: 38px;
      height: 38px;
      margin-right: 20px;
      background-image: linear-gradient(180deg, #7be7ff 2%, #1e85e2);
    }
    .user-name {
    }
    .user-menu {
      display: flex;
      width: 92px;
      align-items: center;
      justify-content: space-around;

      .login {
      }
      .register {
      }
    }
  }
}
</style>
