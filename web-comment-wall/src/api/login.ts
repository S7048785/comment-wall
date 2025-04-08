import request from "@/utils/request";

export const loginAPI = (data: { username: string; password: string }) => {
  return request({
    url: "/login",
    method: "post",
    data,
  });
};
