import request from "@/utils/request"
import axios from "axios"
export const api1 = () => {
  return request({
    url: '/',
    method: "GET"
  })
}

export const githubUserInfoAPI = (userIds: string) => {
  return axios({
    url: `https://api.github.com/users?since=${userIds}`,
    method: "GET"
  })
}