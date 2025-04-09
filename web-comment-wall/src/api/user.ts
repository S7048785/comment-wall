import request from "@/utils/request";

export const loginAPI = (username: string, password: string) => {
  return request({
    url: "/user/login",
    method: "post",
    data: {
      username,
      password,
    },
  });
};
