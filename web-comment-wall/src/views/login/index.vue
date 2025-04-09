<template>
  <div class="login-container">
    <form @submit.prevent="handleSubmit">
      <div class="login-form">
        <div class="head">
          <div class="back" @click="router.push('/')">
            <svg class="icon" aria-hidden="true">
              <use
                xlink:href="#icon-jiantouleft
"
              ></use>
            </svg>
            <div>返回首页</div>
          </div>
          <div class="text-center"><h1>Login</h1></div>
        </div>
        <div class="body">
          <LoginInput v-model="loginForm.username" :errorMsg="''" />
          <LoginInput
            v-model="loginForm.password"
            :errorMsg="''"
            :str="'Password'"
          />
        </div>
        <div class="foot-top">
          <el-checkbox v-model="checked1" label="记住我" size="large" />
          <el-button link> 注册账号 </el-button>
        </div>
        <div class="foot">
          <div class="foot-bottom">
            <LoginButton />
          </div>
        </div>
      </div>
    </form>
  </div>
</template>

<script setup lang="ts">
import LoginInput from "@/components/LoginInput.vue";
import { ref, reactive } from "vue";
import { useUserStore } from "@/stores/userStore";
import { ElMessage } from "element-plus";
import { useRouter } from "vue-router";
const router = useRouter();
const userStore = useUserStore();
const loginForm = reactive({
  username: "",
  password: "",
});
const checked1 = ref(false);
const isTrue = ref(false);
function validate(bool: boolean) {
  isTrue.value = isTrue.value && bool;
}
const handleSubmit = () => {
  console.log(loginForm);

  if (true) {
    // TODO: 发送登录请求
    userStore.login(loginForm.username, loginForm.password).then((res) => {
      ElMessage.success("登录成功");
      // 跳转到首页
      router.push("/");
    });
  }
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
    height: 440px;
    padding: 30px 30px;
    border-radius: 20px;
    box-shadow: 0px 0px 24px #cbdde5;
    .head {
      height: 20%;
      width: 100%;
      margin-bottom: 60px;
      // display: flex;
      .back {
        font-size: 16px;
        cursor: pointer;
        svg {
          float: left;
        }
        div {
          float: left;
        }
        &::after {
          content: "";
          display: block;
          clear: both;
        }
      }
      .text-center {
        text-align: center;
      }
    }
    .body {
      height: 50%;
      display: flex;
      flex-direction: column;
      justify-content: space-between;
    }
    .foot-top {
      width: 200px;
      display: flex;
      justify-content: space-between;
    }
    .foot {
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: space-around;
      width: 100%;
      height: 30%;
    }
  }
}
</style>
