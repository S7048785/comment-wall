import { Label } from "@/types/enum/label";
export type Card = {
  id: string;
  date: Date;
  category: Label;
  content: string;
  username: string;
  liked: boolean;
  likeCount: number;
  commentCount: number;
};

// 卡片详情
export type cardInfo = {};
