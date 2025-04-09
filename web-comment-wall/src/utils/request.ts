import axios from "axios";
import { ElMessage } from "element-plus";
const { VITE_API_URL } = import.meta.env;
const instance = axios.create({
  baseURL: VITE_API_URL, // 修正URL拼写错误，添加了 '//'
  timeout: 5000,
  headers: {
    // 修正属性名从 'header' 到 'headers'
    "Content-Type": "application/json",
  },
});
// 响应拦截器
instance.interceptors.response.use(
  function (response) {
    // 拦截成功
    if (response.data.code === 0) {
      ElMessage.error(response.data.msg);
    }
    console.log(response.data);

    return response.data;
  },
  function (error) {
    // 响应失败

    ElMessage.error(error.message);

    return Promise.reject(error);
  }
);
// 请求拦截器
instance.interceptors.request.use(
  function (config) {
    // 请求之前做点事

    config.headers.Authorization = localStorage.getItem("token");
    return config;
  },
  function (error) {
    // 请求失败
    return error;
  }
);
export default instance;
