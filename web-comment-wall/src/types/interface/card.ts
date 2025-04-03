import { Label, imgLabel } from "@/types/enum/label";
export interface Card {
  id: string;
  date?: string;
  label: string;
  username?: string;
  liked: boolean;
  likeCount: number;
  commentCount: number;
}

export interface MsgCard extends Card {
  color?: string;
  message: string;
  type: "msg";
}

export interface ImgCard extends Card {
  url: string;
  type: "img";
}

// 卡片详情
export type cardInfo = {};
