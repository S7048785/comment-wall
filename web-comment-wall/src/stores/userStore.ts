import { defineStore } from "pinia";
import { ref, reactive } from "vue";
import { loginAPI } from "@/api/user";

export const useUserStore = defineStore("user", () => {
  const isLogin = ref(false);
  const userInfo = reactive({
    id: "",
    username: "",
    avatar:
      "https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png",
  });
  // 登录
  const login = async (username: string, password: string) => {
    const res: any = await loginAPI(username, password);
    if (res.data.token) {
      isLogin.value = true;
      userInfo.id = res.data.id;
      userInfo.username = res.data.name;
      userInfo.avatar = res.data.avatarUrl;
      // 存储token
      localStorage.setItem("token", res.data.token);
    }
  };
  return { userInfo, login };
});
