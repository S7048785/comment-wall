import { defineStore } from "pinia";
import { ref, reactive } from "vue";
import { loginAPI } from "@/api/login";

export const useUserStore = defineStore("user", () => {
  const isLogin = ref(false);
  const userInfo = reactive({
    username: "",
    avatar: "",
  });
  // 登录
  const login = async (data: { username: string; password: string }) => {
    const res: any = await loginAPI(data);
    if (res.token) {
      isLogin.value = true;
      userInfo.username = res.data.username;
      userInfo.avatar = res.data.avatar;
      // 存储token
      localStorage.setItem("token", res.token);
    }
  };
  return { isLogin, userInfo };
});
