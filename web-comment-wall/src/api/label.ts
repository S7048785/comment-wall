import request from "../utils/request";

export const getLabelAPI = (category: 1 | 2) => {
  return request({
    url: `/label/${category}`,
  });
};
