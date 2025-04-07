import axios from "axios";
const { VITE_API_URL } = import.meta.env;
const instance = axios.create({
  baseURL: VITE_API_URL, // 修正URL拼写错误，添加了 '//'
  timeout: 3000,
  headers: {
    // 修正属性名从 'header' 到 'headers'
    "Content-Type": "application/json",
  },
});
// 响应拦截器
instance.interceptors.response.use(
  function (response) {
    // 拦截成功
    if (
      response.data.records[0] &&
      typeof response.data.records[0].id === "number"
    ) {
      // 将id字段类型从number改为string
      response.data.records.forEach((item: any) => {
        item.id = item.id.toString();
      });
    }
    console.log(response.data);

    return response.data;
  },
  function (error) {
    // 响应失败
    return Promise.reject(error);
  }
);
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
);
export default instance;
