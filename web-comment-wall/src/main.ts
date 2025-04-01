// import './assets/main.css'
// import '@/style/commons.less'
import "@/assets/font/iconfont.ts";

import { createApp } from "vue";
import { createPinia } from "pinia";
import ElementPlus from "element-plus";
import "element-plus/dist/index.css";
import { clickOutside } from "@/directive";

import App from "./App.vue";
import router from "./router";

const app = createApp(App);

app.use(createPinia());
app.use(router);
app.use(ElementPlus);

// 绑定自定义指令
app.use(clickOutside);

app.mount("#app");
