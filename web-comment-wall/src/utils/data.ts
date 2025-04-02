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

export let imageCardList = [
  {
    id: "198",
    label: "动漫",
    likeCount: 0,
    liked: false,
    url: "https://fastly.picsum.photos/id/198/200/200.jpg?hmac=3UdulfE9DTCNBECOusB2ignsI8T-XhkGI-M0P2BMJm8",
  },
  {
    id: "735",
    label: "动漫",
    likeCount: 0,
    liked: false,
    url: "https://fastly.picsum.photos/id/735/200/300.jpg?hmac=1a236E3f0SNOHOLEh3dxu5_WIFvWaNKYBSZXBWpi6xE",
  },
  {
    id: "996",
    label: "动漫",
    likeCount: 0,
    liked: false,
    url: "https://fastly.picsum.photos/id/996/200/260.jpg?hmac=C8eYAnot6-DGKXfvhXRIjkICKX3iWveLAjsE7_jCFts",
  },
  {
    id: "101",
    label: "动漫",
    likeCount: 0,
    liked: false,
    url: "https://fastly.picsum.photos/id/101/200/200.jpg?hmac=8aiHS9K78DvBexQ7ZROLuLizDR22o8CcjRMUhHbZU6g",
  },
  {
    id: "255",
    label: "动漫",
    likeCount: 0,
    liked: false,
    url: "https://fastly.picsum.photos/id/255/200/200.jpg?hmac=IYQV36UT5-F1dbK_CQXF7PDfLfwcnwKijqeBCo3yMlc",
  },
  {
    id: "344",
    label: "动漫",
    likeCount: 0,
    liked: false,
    url: "https://fastly.picsum.photos/id/344/200/250.jpg?hmac=qKuoUAp4KPGHFm-3fcGUl8DoWrutxM8Q8IrERQA-upg",
  },
  {
    id: "804",
    label: "动漫",
    likeCount: 0,
    liked: false,
    url: "https://fastly.picsum.photos/id/804/200/300.jpg?hmac=iNvnrzdzAcNx5ZKyG3JWnH4EIYwl-9Lp_4WqWA4R5mo",
  },
  {
    id: "460",
    label: "动漫",
    likeCount: 0,
    liked: false,
    url: "https://fastly.picsum.photos/id/460/200/200.jpg?hmac=hL3I5G2p0p6vDGPyV9hergug-KipbUJVxqnnGIEBXg4",
  },
  {
    id: "928",
    label: "动漫",
    likeCount: 0,
    liked: false,
    url: "https://fastly.picsum.photos/id/928/200/200.jpg?hmac=5MQxbf-ANcu87ZaOn5sOEObpZ9PpJfrOImdC7yOkBlg",
  },
  {
    id: "51",
    label: "动漫",
    likeCount: 0,
    liked: false,
    url: "https://fastly.picsum.photos/id/51/200/250.jpg?hmac=0L4Jkv-zShmCkrag3HohyDLDv7ASABE9AQBHCi7xi4Y",
  },
  {
    id: "646",
    label: "动漫",
    likeCount: 0,
    liked: false,
    url: "https://fastly.picsum.photos/id/646/200/300.jpg?hmac=qCJ0bf6G6oSxx8YMMc1ZLVryK-w596XjRD3o8cXQLFc",
  },
  {
    id: "879",
    label: "动漫",
    likeCount: 0,
    liked: false,
    url: "https://fastly.picsum.photos/id/879/200/260.jpg?hmac=_mEyXd1gfvb2ddcOXkOMKsmiPJy4yye5QbCZ7FvXPrg",
  },
  {
    id: "144",
    label: "动漫",
    likeCount: 0,
    liked: false,
    url: "https://fastly.picsum.photos/id/144/200/200.jpg?hmac=3uevqKoBuYGJxqInvMh1R9bfnV2bz-Vetuv5Zwnb3mE",
  },
  {
    id: "810",
    label: "动漫",
    likeCount: 0,
    liked: false,
    url: "https://fastly.picsum.photos/id/810/200/200.jpg?hmac=h-LObTvlGPc-9CHrUTHemskWMBEOjqEWQejDQV2zvg4",
  },
  {
    id: "287",
    label: "动漫",
    likeCount: 0,
    liked: false,
    url: "https://fastly.picsum.photos/id/287/200/250.jpg?hmac=3hoahq3yMbbG3GTUB2fie7kV-sUDa0PtQfozSelR1pQ",
  },
  {
    id: "863",
    label: "动漫",
    likeCount: 0,
    liked: false,
    url: "https://fastly.picsum.photos/id/863/200/300.jpg?hmac=4kin1N4a7dzocUZXCwLWHewLobhw1Q6_e_9E3Iy3n0I",
  },
  {
    id: "334",
    label: "动漫",
    likeCount: 0,
    liked: false,
    url: "https://fastly.picsum.photos/id/334/200/260.jpg?hmac=H9darMiYCR9oa9RD4EFtw_tu1LYcxGiRTFi8XuVbhUk",
  },
];
