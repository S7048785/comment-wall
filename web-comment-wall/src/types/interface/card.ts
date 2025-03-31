import { Label } from "@/types/enum/label";
export type Card = {
  id: string;
  date: Date;
  color?: string;
  label: Label;
  message: string;
  username: string;
  liked: boolean;
  likeCount: number;
  commentCount: number;
};

// 卡片详情
export type cardInfo = {};
