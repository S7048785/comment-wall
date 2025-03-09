import request from "@/utils/request"

export const api1 = () => {
  return request({
    url: '/',
    method: "GET"
  })
}
