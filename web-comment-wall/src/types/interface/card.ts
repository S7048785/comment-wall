import { Label, imgLabel } from "@/types/enum/label";
export type Card = {
  id: string;
  date: string;
  color?: string;
  label: Label;
  message: string;
  username: string;
  liked: boolean;
  likeCount: number;
  commentCount: number;
};

export type imgCard = {
  id: string;
  label: imgLabel;
  src: string;
  likeCount: number;
};

// 卡片详情
export type cardInfo = {};
