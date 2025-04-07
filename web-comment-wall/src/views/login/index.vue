<template>
  <div class="login-container">
    <div>
      <form @submit.prevent="handleSubmit" class="login-form">
        <div class="head"><h2>Login</h2></div>
        <div class="body">
          <LoginInput
            v-model="loginForm.username"
            :errorMsg="'长度为6-12位的字母数字下划线<br>(开头必须是字母)'"
            @validate="validateUsername"
          />
          <LoginInput
            v-model="loginForm.password"
            @validate="validatePassword"
            :errorMsg="'长度为6-12位的字母数字下划线(开头必须是字母)'"
            :str="'Password'"
          />
        </div>
        <div class="foot">
          <div class="foot-top">
            <a @click.prevent href="">忘记密码</a>
            <a @click.prevent href="">注册账号</a>
          </div>
          <div class="foot-bottom"><LoginButton /></div>
        </div>
      </form>
    </div>
  </div>
</template>

<script setup lang="ts">
import LoginInput from "@/components/LoginInput.vue";
import { ref, reactive } from "vue";
const loginForm = reactive({
  username: "",
  password: "",
});
function validateUsername(isTrue: boolean) {}
function validatePassword(isTrue: boolean) {}
const handleSubmit = () => {
  console.log(loginForm);

  // TODO: 发送登录请求
};
</script>

<style lang="less" scoped>
.login-container {
  width: 100%;
  height: 100vh;
  background-image: url("/public/bg.png");
  background-size: cover; /* 覆盖整个容器 */
  background-position: center; /* 居中显示 */
  background-repeat: no-repeat; /* 不重复 */
  background-attachment: fixed; /* 固定不滚动 */
  display: flex;
  justify-content: center;
  align-items: center;

  .login-form {
    display: flex;
    justify-content: space-evenly;
    flex-direction: column;
    align-items: center;
    background-color: rgb(255, 255, 255);
    width: 400px;
    height: 540px;
    padding: 30px 0px;
    border-radius: 20px;
    box-shadow: 0px 0px 24px #cbdde5;
    .head {
      height: 20%;
      display: flex;
      align-items: center;
    }
    .body {
      height: 50%;
      display: flex;
      flex-direction: column;
      justify-content: space-around;
    }
    .foot {
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: space-around;
      width: 100%;
      height: 30%;
      .foot-top {
        width: 100%;
        display: flex;
        justify-content: space-evenly;
      }
    }
  }
}
</style>
