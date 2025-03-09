import axios from "axios";

const instance = axios.create({
  baseURL: "http://localhost:3000", // 修正URL拼写错误，添加了 '//'
  timeout: 1000,
  headers: { // 修正属性名从 'header' 到 'headers'
    "Content-Type": "application/json",
  }
})
// 响应拦截器
instance.interceptors.response.use(
  function (response) {
    // 拦截成功
    return response.data;
  },
  function (error) {
    // 响应失败
    return Promise.reject(error);
  }
)
// 请求拦截器
instance.interceptors.request.use(
  function (config) {
    // 请求之前做点事
    return config;
  },
  function (error) {
    // 请求失败
    return error;
  }
)
export default instance;