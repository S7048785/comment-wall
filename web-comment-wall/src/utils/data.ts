import { Label, imgLabel } from "@/types/enum/label";
import { type imgCard } from "@/types/interface/card";
import { type userComment } from "@/types/interface/user";
// 卡片标签列表
export const cardLabelList: Label[] = [
  Label.ALL,
  Label.TARGET,
  Label.IDEAL,
  Label.PAST,
  Label.FUTURE,
  Label.LOVE,
  Label.LOVELETTER,
  Label.FRIEND,
  Label.SECRET,
  Label.ABSTRUCT,
  Label.OTHER,
];
// 图像标签列表
export const imgLabelList: imgLabel[] = [
  imgLabel.ALL,
  imgLabel.ANIME,
  imgLabel.DESIGN,
  imgLabel.ENTERTAINMENT,
  imgLabel.OVERALL,
  imgLabel.NATURAL,
  imgLabel.SCENIC,
  imgLabel.PERSON,
  imgLabel.MECHANICAL,
  imgLabel.CULTURE,
];
// 卡片颜色列表
export const cardColorList = [
  "#fcafa24d",
  "#c7f58c79",
  "#bfefff",
  "#88c1f4c1",
  "#f7a4d3e1",
  "#fff6bba3",
  "#adffde89",
  "#f2dffad0",
];

export const cardNormal = {
  id: "",
  date: "2025.04.01",
  username: "",
  message: "",
  liked: false,
  likeCount: 0,
  commentCount: 0,
  color: cardColorList[0],
  label: cardLabelList[1],
};

// 评论列表
export const userComments: userComment[] = [
  {
    id: "101",
    img: "https://picsum.photos/40/40",
    name: "jvantuyl",
    date: "2021-01-01 11:19",
    content:
      "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed euismod, nunc ut aliquam tincidunt, nunc nisl aliquet nisl,eu aliquam nisl nunc eu nisl.",
  },
  {
    id: "102",
    img: "https://picsum.photos/40/40",
    name: "BrianTheCoder",
    date: "2021-01-01 11:19",
    content:
      "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed euismod, nunc ut aliquam tincidunt, nunc nisl aliquet nisl,eu aliquamnisl nunc eu nisl.",
  },
  {
    id: "103",
    img: "https://picsum.photos/40/40",
    name: "John Doe",
    date: "2021-01-01 11:19",
    content:
      "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed euismod, nunc ut aliquam tincidunt, nunc nisl aliquet nisl,eu aliquam nisl nunc eu nisl.",
  },
  {
    id: "104",
    img: "https://picsum.photos/40/40",
    name: "Jane Doe",
    date: "2021-01-01 11:19",
    content:
      "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed euismod, nunc ut aliquam tincidunt, nunc nisl aliquet nisl,eu aliquam nisl nunc eu nisl.",
  },
  {
    id: "105",
    img: "https://picsum.photos/40/40",
    name: "jvantuyl",
    date: "2021-01-01 11:19",
    content:
      "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed euismod, nunc ut aliquam tincidunt, nunc nisl aliquet nisl,eu aliquam nisl nunc eu nisl.",
  },
  {
    id: "106",
    img: "https://picsum.photos/40/40",
    name: "BrianTheCoder",
    date: "2021-01-01 11:19",
    content:
      "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed euismod, nunc ut aliquam tincidunt, nunc nisl aliquet nisl,eu aliquamnisl nunc eu nisl.",
  },
  {
    id: "107",
    img: "https://picsum.photos/40/40",
    name: "John Doe",
    date: "2021-01-01 11:19",
    content:
      "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed euismod, nunc ut aliquam tincidunt, nunc nisl aliquet nisl,eu aliquam nisl nunc eu nisl.",
  },
  {
    id: "108",
    img: "https://picsum.photos/40/40",
    name: "Jane Doe",
    date: "2021-01-01 11:19",
    content:
      "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed euismod, nunc ut aliquam tincidunt, nunc nisl aliquet nisl,eu aliquam nisl nunc eu nisl.",
  },
];

export const imgList: imgCard[] = [
  {
    id: "101",
    label: imgLabel.ANIME,
    likeCount: 0,
    src: "https://picsum.photos/300/400",
  },
  {
    id: "102",
    label: imgLabel.DESIGN,
    likeCount: 0,
    src: "https://picsum.photos/300/350",
  },
  {
    id: "103",
    label: imgLabel.ENTERTAINMENT,
    likeCount: 0,
    src: "https://picsum.photos/300/380",
  },
  {
    id: "104",
    label: imgLabel.OVERALL,
    likeCount: 0,
    src: "https://picsum.photos/300/400",
  },
  {
    id: "105",
    label: imgLabel.NATURAL,
    likeCount: 0,
    src: "https://picsum.photos/300/320",
  },
  {
    id: "106",
    label: imgLabel.SCENIC,
    likeCount: 0,
    src: "https://picsum.photos/300/340",
  },
  {
    id: "107",
    label: imgLabel.PERSON,
    likeCount: 0,
    src: "https://picsum.photos/300/300",
  },
  {
    id: "108",
    label: imgLabel.MECHANICAL,
    likeCount: 0,
    src: "https://picsum.photos/300/350",
  },
  {
    id: "109",
    label: imgLabel.CULTURE,
    likeCount: 0,
    src: "https://picsum.photos/300/380",
  },
  {
    id: "110",
    label: imgLabel.ANIME,
    likeCount: 0,
    src: "https://picsum.photos/300/400",
  },
];
